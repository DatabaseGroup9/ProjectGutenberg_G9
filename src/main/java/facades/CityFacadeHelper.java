package facades;


import interfaces.IBookFacadeHelper;
import interfaces.ICityFacadeHelper;

public class CityFacadeHelper implements ICityFacadeHelper {


    public boolean checkValidBookTitleInput(String bookTitle)  {

//        if(isNumeric(titel)) {
//            return false;
//        }
        if(bookTitle == null || bookTitle == ""){
            return false;
        }
        if(bookTitle.length() > 500){
            return false;
        }
        return true;
    }
//    public boolean isNumeric(String str)
//    {
//        try
//        {
//            double d = Double.parseDouble(str);
//        }
//        catch(NumberFormatException nfe)
//        {
//            return false;
//        }
//        return true;
//    }
}
