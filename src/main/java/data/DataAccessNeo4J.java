package data;

import entity.Book;
import entity.City;
import interfaces.IDataAccessor;
import interfaces.IBook;
import interfaces.ICity;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

public class DataAccessNeo4J implements IDataAccessor {

    private String name = "DataAccessNeo4J";
    private DBConnectorNeo4J dbConnectorNeo4J = new DBConnectorNeo4J();

    public DataAccessNeo4J() {
        this.name = "Neo4J";
    }

    @Override
    public List<IBook> getBooksByCityName(String cityName) {
        List<IBook> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            /**
             * todo change to prepared statements for security reasons
             */
            String query = "MATCH (b:Book)-[r:MENTIONS ]-(c:City) WHERE LOWER(c.name) = LOWER(\"" + cityName + "\") RETURN b";

            Session session = driver.session();

            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();

        } catch (Exception e) {
            //if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ICity> getCitiesByBookTitle(String bookTitle) {
        System.out.println("DataAccessNeo4j_getCitiesByBookTitle()");
        List<ICity> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            /**
             * todo change to prepared statements for security reasons
             */
            String query = "MATCH (b:Book)-[r:MENTIONS]->(c:City) WHERE LOWER(b.title) = LOWER(\"" + bookTitle + "\") RETURN c";

            Session session = driver.session();

            StatementResult result = session.run(query);

            list = getResultsCities(result);
            session.close();

        } catch (Exception e) {
            //if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    private List<IBook> getResults(StatementResult result) {

        List<IBook> list = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            IBook b = new Book();
            System.out.println(record.toString());
            System.out.println(record.get("b").toString());
            b.setId(record.get("b").get("id").asString());
            b.setTitle(record.get("b").get("title").asString());
            b.setAuthor(record.get("b").get("author").asString());
            list.add(b);
        }
        return list;
    }

    private List<ICity> getResultsCities(StatementResult result) {

        List<ICity> list = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            ICity c = new City();
            c.setName(record.get("c").get("name").asString());
            c.setLat(record.get("c").get("lat").asDouble());
            c.setLon(record.get("c").get("lon").asDouble());
            list.add(c);
        }
        return list;
    }

    public void close() {
        Driver driver = dbConnectorNeo4J.getDriver();
        driver.close();

    }
}
