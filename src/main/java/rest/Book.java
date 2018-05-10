package rest;

import com.google.gson.Gson;
import data.DataAccessFactory;
import facades.BookFacade;
import httpErrors.GenericExceptionMapper;
import httpErrors.InvalidInputExceptionMapper;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IBook;
import interfaces.IBookFacade;

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
    public String getBooks(@QueryParam("db") String db, @QueryParam("city") String city) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {

        /**
         * todo make setting come from Http Headers?
         */
        
        try {
//            IBookFacade bookFacade = new BookFacade(new DataAccessFactory(), "stub");
            IBookFacade facade = new BookFacade(new DataAccessFactory(), db);

            List<IBook> list = facade.getBooksByCityName(city);
            String json = new Gson().toJson(list);
            return json;
        } catch (Exception e) {
            throw e;
        }

    }

}
