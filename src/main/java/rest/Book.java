package rest;

import com.google.gson.Gson;
import data.DataAccessFactory;
import facades.BookFacade;
import httpErrors.GenericExceptionMapper;
import httpErrors.InvalidInputExceptionMapper;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.IBookFacade;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Cherry Rose Semeña & Emmely Lundberg
 */
@Path("book")
public class Book {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBooks(@QueryParam("db") String db, @QueryParam("city") String city, @QueryParam("author") String author) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {

        /**
         * todo make setting come from Http Headers?
         */

        try {
//            IBookFacade bookFacade = new BookFacade(new DataAccessFactory(), "stub");
            IBookFacade facade = new BookFacade(new DataAccessFactory(), db);

            List<IBook> list;


            /*  NOTE:
             * We can't have two methods with signature getBooks(String, String)
             * This way, city takes priority if provided. Bad?
             * todo define behavior when both parameters are provided.
             */
            if(city != null && city.length() != 0) {
                list = facade.getBooksByCityName(city);
            } else if(author != null && author.length() != 0) {
                list = facade.getBooksByAuthorName(author);
            } else {
                throw new NotFoundExceptionMapper("No search parameter provided [city/author].");
            }


            String json = new Gson().toJson(list);
            return json;
        } catch (Exception e) {
            throw e;
        }

    }

}
