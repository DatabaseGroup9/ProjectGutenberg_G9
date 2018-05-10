package data;


import interfaces.IBook;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(JUnitParamsRunner.class)
public class Neo4JUnitTest {
  

  /*Valid*/
  @Test
  @FileParameters("src/test/java/test/resources/story1-validinput-neo4j.csv")

  public void getBooksByCityTest(String city,String id, String title, String author){
    DataAccessNeo4J dataAccessNeo4J = new DataAccessNeo4J();
    List<IBook> books = dataAccessNeo4J.getBooksByCityName(city);
    assertThat(books.get(0).getId(), CoreMatchers.is(equalTo(id)));
    assertThat(books.get(0).getTitle(), CoreMatchers.is(equalTo(title)));
    assertThat(books.get(0).getAuthor(), CoreMatchers.is(equalTo(author)));
  }

}
