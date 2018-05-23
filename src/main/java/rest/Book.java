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
    @Path("city") //search by city
    public String getBooksByCity(@QueryParam("db") String db, @QueryParam("city") String city) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        try {
            IBookFacade facade = new BookFacade(new DataAccessFactory(), db);
            List<IBook> list;
            list = facade.getBooksByCityName(city);
            String json = new Gson().toJson(list);
            return json;
        } catch (Exception e) {
            System.out.println("THROW FROM REST " + e.getMessage());
            throw e;
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("author")
    public String getBooksByAuthor(@QueryParam("db") String db, @QueryParam("author") String author) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        try {
            IBookFacade facade = new BookFacade(new DataAccessFactory(), db);
            List<IBook> list;
            list = facade.getBooksByAuthorName(author);
            String json = new Gson().toJson(list);
            return json;
        } catch (Exception e) {
            throw e;
        }
    
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("geolocation")
    public String getBooksByGeolocation(@QueryParam("db") String db, @QueryParam("lat") String lat,@QueryParam("lon") String lon) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        try {
            IBookFacade facade = new BookFacade(new DataAccessFactory(), db);
            List<IBook> list;
            double lat_d = Double.parseDouble(lat);
            double lon_d = Double.parseDouble(lon);
            list = facade.getBooksByGeolocation(lat_d,lon_d);
            String json = new Gson().toJson(list);
            return json;
        } catch (Exception e) {
            throw e;
        }
    
    }



}
