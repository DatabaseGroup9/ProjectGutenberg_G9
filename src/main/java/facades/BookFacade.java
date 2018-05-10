package facades;

import data.DataAccessFactory;
import httpErrors.InvalidInputExceptionMapper;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IDataAccessFactory;
import interfaces.IBook;
import interfaces.IBookFacade;

import java.util.ArrayList;
import java.util.List;
import interfaces.IDataAccessor;

public class BookFacade implements IBookFacade {

    /**
     * todo change to Factory Pattern
     */
    private IDataAccessFactory dataAccessFactory;
    private String database;
    private BookFacadeHelper helper = new BookFacadeHelper();

    public BookFacade(IDataAccessFactory dataAccessFactory, String database) {
        this.dataAccessFactory = dataAccessFactory;
        this.database = database;
    }

    @Override
    public List<IBook> getBooksByCityName(String city) throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        //Remember to add the valid input checker
            city = city.trim();

            if(!helper.checkValidCityInput(city)){
                throw new InvalidInputExceptionMapper("Invalid Input");

            }


        try {
            List<IBook> books = new ArrayList<IBook>();
            if (city != null && city.length() > 0) {
                books = dataAccessFactory.getDataAccessor(this.database).getBooksByCityName(city);
                return books;
            } else {
                throw new NotFoundExceptionMapper("Invalid City");
            }
        } catch (NotFoundExceptionMapper e) {
            throw e; //possible error from the DBAccessor - No Book Found
        }
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
