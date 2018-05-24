package data;

import entity.Author;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.ICity;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;

@RunWith(JUnitParamsRunner.class)
public class Neo4JUnitTest {

    private DataAccessNeo4J dataAccessNeo4J;

    @Before
    public void setup() {
        this.dataAccessNeo4J = new DataAccessNeo4J();
    }

    /*Valid*/
    @Test
    @FileParameters("src/test/java/test/resources/S1-validinput-neo4j.csv")
    public void getBooksByCityTest(String city, String title, String authorFullName) {
        List<IBook> books = dataAccessNeo4J.getBooksByCityName(city);
        assertThat(books.get(0).getTitle(), CoreMatchers.is(equalTo(title)));
        assertThat(books.get(0).getAuthor().getFullName(), CoreMatchers.is(equalTo(authorFullName)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S2-validinput-neo4j.csv")
    public void getCitiesByTitleTest(String title, String name, double lat, double lon) throws NotFoundExceptionMapper {
        List<ICity> cities = dataAccessNeo4J.getCitiesByBookTitle(title);
        assertThat(cities.get(0).getName(), CoreMatchers.is(equalTo(name)));
        assertThat(cities.get(0).getLat(), CoreMatchers.is(equalTo(lat)));
        assertThat(cities.get(0).getLon(), CoreMatchers.is(equalTo(lon)));
    }

    @Test
    @FileParameters("src/test/java/test/resources/S3-validinput-neo4j.csv")
    public void getCitiesMentionedInBookByAuthor(String author, String title) throws NotFoundExceptionMapper {
        author = author.replace("_", ",");
        List<IBook> books = dataAccessNeo4J.getMentionedCitiesByAuthorName(author);
        assertThat(books.get(0).getTitle(), CoreMatchers.is(equalTo(title)));
    }

}
