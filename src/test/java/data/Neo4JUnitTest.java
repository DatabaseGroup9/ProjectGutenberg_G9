package data;

import entity.Author;
import entity.Book;
import entity.City;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.ICity;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.neo4j.driver.v1.StatementResult;

@RunWith(JUnitParamsRunner.class)
public class Neo4JUnitTest {

    private DataAccessNeo4J dataAccessNeo4J;
    long startTime = 0;
    long endTime = 0;
    long totalElapsedTime = 0;

    @Before
    public void setup() {
        this.dataAccessNeo4J = new DataAccessNeo4J();
        long startTime = System.nanoTime();
    }

    @After
    public void teardown() {
        endTime = System.nanoTime();
        totalElapsedTime = endTime - startTime;
        System.out.println("TOTAL TIME ELAPSED = " + totalElapsedTime);
    }

    /*Valid*/
    @Test
    @FileParameters("src/test/java/test/resources/S1-validinput.csv")
    public void getBooksByCityTest(String city, String bookTitle, String authorFullName) {
        System.out.println("getBooksByCityTest");
        System.out.println("INPUT : " + city);
        List<IBook> books = dataAccessNeo4J.getBooksByCityName(city);
        authorFullName = authorFullName.replace("_", ",");
        assertThat(books, Matchers.<Book>hasItem(Matchers.hasProperty("bookTitle", equalTo(bookTitle))));
        //assertThat(books.get(0).getAuthor().getFullName(), CoreMatchers.is(equalTo(authorFullName)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S2-validinput.csv")
    public void getCitiesByTitleTest(String title, String name, double lat, double lon) throws NotFoundExceptionMapper {
        System.out.println("getCitiesByTitleTest");
        System.out.println("INPUT : " + title);
        List<ICity> cities = dataAccessNeo4J.getCitiesByBookTitle(title);
        assertThat(cities, Matchers.<City>hasItem(Matchers.hasProperty("name", equalTo(name))));
        assertThat(cities, Matchers.<City>hasItem(Matchers.hasProperty("lat", equalTo(lat))));
        assertThat(cities, Matchers.<City>hasItem(Matchers.hasProperty("lon", equalTo(lon))));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S3-validinput.csv")
    public void getCitiesMentionedInBookByAuthor(String bookTitle, String authorFullName) throws NotFoundExceptionMapper {
        System.out.println("getCitiesMentionedInBookByAuthor");
        authorFullName = authorFullName.replace("_", ",");
        System.out.println("INPUT : " + authorFullName);
        List<IBook> books = dataAccessNeo4J.getMentionedCitiesByAuthorName(authorFullName);
        assertThat(books, Matchers.<Book>hasItem(Matchers.hasProperty("bookTitle", equalTo(bookTitle))));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S4-validinput.csv")
    public void getBooksByGeolocation(double lat, double lon, String bookTitle, String authorFullName) throws NotFoundExceptionMapper {
        System.out.println("getBooksByGeolocation");
        System.out.println("INPUT : " + lat + " , " + lon);
        List<IBook> books = dataAccessNeo4J.getBooksByGeolocation(lat, lon);
        authorFullName = authorFullName.replace("_", ",");
        assertThat(books, Matchers.<Book>hasItem(Matchers.hasProperty("bookTitle", equalTo(bookTitle))));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S1-validinput.csv")
    public void getUserStory1DBQuery(String city, String bookTitle, String authorFullName) {
        System.out.println("getUserStory1DBQuery");
        System.out.println("INPUT : " + city);
        StatementResult rs = dataAccessNeo4J.getUserStory1Query(city);
        authorFullName = authorFullName.replace("_", ",");
        assertThat(rs.hasNext(), is(not(false)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S2-validinput.csv")
    public void getUserStory2DBQuery(String title, String name, double lat, double lon) {
        System.out.println("getUserStory2DBQuery");
        System.out.println("INPUT : " + title);
        StatementResult rs = dataAccessNeo4J.getUserStory2Query(title);
        assertThat(rs.hasNext(), is(not(false)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S3-validinput.csv")
    public void getUserStory3DBQuery(String bookTitle, String authorFullName) {
        System.out.println("getUserStory1DBQuery");
        System.out.println("INPUT : " + authorFullName);
        authorFullName = authorFullName.replace("_", ",");
        StatementResult rs = dataAccessNeo4J.getUserStory3Query(authorFullName);
        assertThat(rs.hasNext(), is(not(false)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S4-validinput.csv")
    public void getUserStory4DBQuery(double lat, double lon, String bookTitle, String authorFullName) {
        System.out.println("getUserStory4DBQuery");
        System.out.println("INPUT : " + lat + " , " + lon);
        StatementResult rs = dataAccessNeo4J.getUserStory4Query(lat, lon);
        authorFullName = authorFullName.replace("_", ",");
        assertThat(rs.hasNext(), is(not(false)));
    }

}
