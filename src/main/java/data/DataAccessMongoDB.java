package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.elemMatch;
import entity.Book;
import entity.City;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.ICity;
import interfaces.IDataAccessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Cherry Rose Semeña
 */
public class DataAccessMongoDB implements IDataAccessor {

    private String name = "DataAccessMongoDB";
    private DBConnectorMongoDB connector = null;
    private static MongoClient con = null;

//    DB db = null;
    public DataAccessMongoDB() {
        this.connector = new DBConnectorMongoDB();
        this.con = connector.getConnection();
//        this.db = con.getDB("cjs_db");
    }

    @Override
    public List<IBook> getBooksByCityName(String cityName) throws NotFoundExceptionMapper {

        try {
            List<IBook> books = new ArrayList();
            ObjectMapper mapper = new ObjectMapper();
            MongoDatabase database = con.getDatabase("cjs_db");
            MongoCollection coll = database.getCollection("books");
            FindIterable<Document> findIterable = coll.find(in("cities.name", cityName));
            for (Document document : findIterable) {
                String jsonStr = document.toJson();
                System.out.println("THE JSON STRING IS " + jsonStr);
                IBook b = mapper.readValue(jsonStr, Book.class);
                printBook((Book) b);
                books.add(b);
            }

            if (books.size() == 0) {
                throw new NotFoundExceptionMapper("No Book Found");
            }

            return books;
        } catch (Exception e) {
            throw new NotFoundExceptionMapper(e.getMessage());
        }
    }

    //just a helper method
    public void printBook(Book b) {
        System.out.println("THE BOOK >>>" + b.toString());
        for (int i = 0; i < b.getCities().size(); i++) {
            System.out.println(i + ": " + b.getCities().get(i).toString());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ICity> getCitiesByBookTitle(String bookTitle) {
        //user story 2: get all cities by book title.
        try {
            List<ICity> cities = new ArrayList();
            ObjectMapper mapper = new ObjectMapper();
            MongoDatabase database = con.getDatabase("cjs_db");
            MongoCollection coll = database.getCollection("books");
            FindIterable<Document> findIterable = coll.find(in("books.title", bookTitle));
            for (Document document : findIterable) {
                String jsonStr = document.toJson();
                System.out.println("THE JSON STRING IS " + jsonStr);
                ICity c = mapper.readValue(jsonStr, City.class);
                //printBook((Book) b);
                cities.add(c);
            }

            if (cities.size() == 0) {
                throw new NotFoundExceptionMapper("No Book Found");
            }

            return cities;
        } catch (Exception e) {
            try {
                throw new NotFoundExceptionMapper(e.getMessage());
            } catch (NotFoundExceptionMapper ex) {
                Logger.getLogger(DataAccessMongoDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //------------------------------------------------Run Once For Testing Locally-------------------------------------------------------------------------
    private static void populateWithTestData() {
        try {
            MongoDatabase database = con.getDatabase("cjs_db");
            MongoCollection collectionOfBooks = database.getCollection("books");
            MongoCollection collectionOfCities = database.getCollection("cities");
            MongoCollection collectionOfMentions = database.getCollection("mentions");

            //cities
            List<Document> cities = new ArrayList();
            Document city1 = new Document();
            city1.put("name", "Madrid");
            city1.put("lat", 40.41678);
            city1.put("lon", -3.70379);
            cities.add(city1);
            Document city2 = new Document();
            city2.put("name", "Toledo");
            city2.put("lat", 39.86283);
            city2.put("lon", -4.02732);
            cities.add(city2);
            Document city3 = new Document();
            city3.put("name", "Wiltshire");
            city3.put("lat", 51.34920);
            city3.put("lon", -1.99271);
            cities.add(city3);

            collectionOfCities.insertMany(cities);

            //books
            List<Document> books = new ArrayList();
            Document book1 = new Document();
            book1.put("title", "The Three Musketeers");
            book1.put("author", "Alexandre Dumas");
            List<Document> b1cities = new ArrayList();
            b1cities.add(city1);
            b1cities.add(city2);
            book1.put("cities", b1cities);
            books.add(book1);

            Document book2 = new Document();
            book2.put("title", "The Black Tulip");
            book2.put("author", "Alexandre Dumas");
            List<Document> b2cities = new ArrayList();
            b2cities.add(city1);
            book2.put("cities", b2cities);
            books.add(book2);

            Document book3 = new Document();
            book3.put("title", "Pride and Prejudice");
            book3.put("author", "Jane Austen");
            List<Document> b3cities = new ArrayList();
            b3cities.add(city3);
            book3.put("cities", b3cities);
            books.add(book3);

            //mentions
            List<Document> mentions = new ArrayList();
//        I am not sure really on how should I structure the mentions
            Document m1 = new Document();
            m1.put("book", book1);
            m1.put("city", city1);
            m1.put("mentions", 56);
            mentions.add(m1);
            Document m2 = new Document();
            m2.put("book", book1);
            m2.put("city", city2);
            m2.put("mentions", 10);
            mentions.add(m2);
            Document m3 = new Document();
            m3.put("book", book2);
            m3.put("city", city1);
            m3.put("mentions", 2);
            mentions.add(m3);
            Document m4 = new Document();
            m4.put("book", book3);
            m4.put("city", city3);
            m4.put("mentions", 11);
            mentions.add(m4);

            collectionOfBooks.insertMany(books);
            collectionOfMentions.insertMany(mentions);

            System.out.println("DONE POPULATING THE DATABASE");

        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG" + e.toString());
        }

    }

//    public static void main(String[] args) {
//        DataAccessMongoDB db = new DataAccessMongoDB();
//        populateWithTestData();
//        con.close();
//    }
}
