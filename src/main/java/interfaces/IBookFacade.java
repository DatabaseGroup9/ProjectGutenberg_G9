package interfaces;

import httpErrors.InvalidInputExceptionMapper;
import httpErrors.NotFoundExceptionMapper;
import java.util.List;

public interface IBookFacade {

    List<IBook> getBooksByCityName (String city) throws NotFoundExceptionMapper, InvalidInputExceptionMapper;

}
