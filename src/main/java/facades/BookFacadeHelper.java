package facades;



import interfaces.IBookFacadeHelper;
import org.apache.http.util.TextUtils;

public class BookFacadeHelper implements IBookFacadeHelper {


    public boolean checkValidCityInput(String city)  {


        if(isNumeric(city)) {
            return false;
        }
        if(city == null||city.equals("null") || city.trim().isEmpty()){
            return false;
        }
        if(city.length() > 500){
            return false;
        }
        return true;
    }
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
