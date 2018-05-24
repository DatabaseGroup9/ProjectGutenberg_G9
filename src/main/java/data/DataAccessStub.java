package data;

import entity.Author;
import interfaces.IDataAccessor;
import entity.Book;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.ICity;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataAccessStub implements IDataAccessor {

    private String name = "DataAccessStub";
    
    @Override
    public List<IBook> getBooksByCityName(String cityName) throws NotFoundExceptionMapper {
        String[] notfound = new String[]{"Lyngby","America", "Europe"};
        for (int i = 0; i <notfound.length ; i++) {
            if(cityName.equalsIgnoreCase(notfound[i])){
                throw new NotFoundExceptionMapper("NotFound");
            }
        }
        Author a = new Author("1", "Hannah Lynch");
        Author a2 = new Author("1", "John S. C. Abbott");
        Author a3 = new Author("1", "Lawrence Gilman");
        Author a4 = new Author("1", "Various");
        Author a5 = new Author("1", "Benjamin Moore Norman");
        Author a6 = new Author("1", "Lewis Carroll");
        Author a7 = new Author("1", "Jane Austen");
        List<IBook> books = new ArrayList<IBook>();
        IBook book1 = new Book("Autobiography of a Child", a);
        IBook book2 = new Book("Miles Standish", a2);
        IBook book3 = new Book("Stories of Symphonic Music", a3);
        IBook book4 = new Book("Captain Billy's Whiz Bang, Vol. 2, No. 21, June, 1921 ", a4);
        IBook book5 = new Book("Rambles in Yucatan", a5);
        IBook book6 = new Book("Alice's Adventures in Wonderland", a6);
        IBook book7 = new Book("Pride and Prejudice", a7);
        if (cityName.length() > 5) {
            books.add(book1);
        } else {
            books.add(book1);
            books.add(book2);
            books.add(book3);
            books.add(book4);
            books.add(book5);
            books.add(book6);
            books.add(book7);
        }

        //keep this for exception handler
        if (books.size() == 0) {
            throw new NotFoundExceptionMapper("No Book Found");
        }

        return books;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ICity> getCitiesByBookTitle(String bookTitle) {
        System.out.println("DataAccessStub_getCitiesByBookTitle()");
        List<ICity> list = new ArrayList();
        ICity city1 = new entity.City("New York", 40.730610, -73.935242);
        ICity city2 = new entity.City("Chicago", 41.881832, -87.623177);
        ICity city3 = new entity.City("London", 51.509865, -0.118092);
        list.add(city1);
        list.add(city2);
        list.add(city3);
        return list; // not yet implemented
    }

    public List<IBook> getMentionedCitiesByAuthorName(String authorName) throws NotFoundExceptionMapper {
        String[] notFound = new String[]{"J.K. Rowling", "Oprah Winfrey", "Stieg Larsson"};
        if(Arrays.asList(notFound).contains(authorName)){
            throw new NotFoundExceptionMapper("Author not found");
        }

        List<IBook> books = new ArrayList<>();
        Author a = new Author("1", "Hannah Lynch");
        Author a2 = new Author("1", "John S. C. Abbott");
        Author a3 = new Author("1", "Lawrence Gilman");
        Author a4 = new Author("1", "Various");
        Author a5 = new Author("1", "Benjamin Moore Norman");
        Author a6 = new Author("1", "Lewis Carroll");
        Author a7 = new Author("1", "Jane Austen");
        IBook book1 = new Book("Autobiography of a Child", a);
        IBook book2 = new Book("Miles Standish", a2);
        IBook book3 = new Book("Stories of Symphonic Music", a3);
        IBook book4 = new Book("Captain Billy's Whiz Bang, Vol. 2, No. 21, June, 1921 ", a4);
        IBook book5 = new Book("Rambles in Yucatan", a5);
        IBook book6 = new Book("Alice's Adventures in Wonderland", a6);
        IBook book7 = new Book("Pride and Prejudice", a7);

        return books.subList(0, authorName.length() < 6 ? authorName.length() : 6);
    }

    @Override
    public List<IBook> getBooksByGeolocation(double lat, double lon) throws NotFoundExceptionMapper {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
