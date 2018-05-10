package interfaces;

import httpErrors.NotFoundExceptionMapper;
import java.util.List;

public interface IDataAccessor {
    public List<IBook> getBooksByCityName(String cityName) throws NotFoundExceptionMapper; //User Story # 1
    public List<ICity> getCitiesByBookTitle(String bookTitle); //User Story # 2
    public String getName();
}
