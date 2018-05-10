package facades;

import httpErrors.InvalidInputExceptionMapper;
import httpErrors.NotFoundExceptionMapper;
import interfaces.IDataAccessFactory;
import org.junit.Test;
import interfaces.ICity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import interfaces.IDataAccessor;
import org.junit.Ignore;

public class CityFacadeTest {

    @Test
    public void getCitiesByBookTitle() throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        IDataAccessor iDataAcessMock = mock(IDataAccessor.class);
        List<ICity> cities = new ArrayList<ICity>();
        when(iDataAcessMock.getCitiesByBookTitle((eq("Harry Potter 2")))).thenReturn(cities);

        IDataAccessFactory iDataAcessFactoryMock = mock(IDataAccessFactory.class);
        when(iDataAcessFactoryMock.getDataAccessor((eq("stub")))).thenReturn(iDataAcessMock);

        CityFacade cityFacade = new CityFacade(iDataAcessFactoryMock,"stub");
        cityFacade.getCitiesByBookTitle("Harry Potter 2");
        assertTrue(cityFacade.getCitiesByBookTitle("Harry Potter 2").equals(cities));
    }

    @Test
    public void getCitiesByBookTitleTestDatabaseType()  throws NotFoundExceptionMapper, InvalidInputExceptionMapper{
        IDataAccessor iDataAcessMock = mock(IDataAccessor.class);
        List<ICity> cities = new ArrayList<ICity>();
        when(iDataAcessMock.getCitiesByBookTitle((eq("Harry Potter 2")))).thenReturn(cities);

        IDataAccessFactory iDataAcessFactoryMock = mock(IDataAccessFactory.class);
        when(iDataAcessFactoryMock.getDataAccessor((eq("stub")))).thenReturn(iDataAcessMock);

        CityFacade cityFacade = new CityFacade(iDataAcessFactoryMock,"stub");
 
        assertTrue(cityFacade.getDatabase().equals("stub"));
    }
    
    @Test(expected = InvalidInputExceptionMapper.class)
    public void getCitiesByBookTitle2() throws NotFoundExceptionMapper, InvalidInputExceptionMapper {
        IDataAccessor iDataAcessMock = mock(IDataAccessor.class);
        List<ICity> cities = new ArrayList<ICity>();
        when(iDataAcessMock.getCitiesByBookTitle((eq("")))).thenReturn(cities);

        IDataAccessFactory iDataAcessFactoryMock = mock(IDataAccessFactory.class);
        when(iDataAcessFactoryMock.getDataAccessor((eq("stub")))).thenReturn(iDataAcessMock);

        CityFacade cityFacade = new CityFacade(iDataAcessFactoryMock,"stub");
        assertTrue(cityFacade.getCitiesByBookTitle("").equals(cities));
    }
}