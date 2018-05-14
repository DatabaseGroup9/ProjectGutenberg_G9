package facades;


import interfaces.IBookFacadeHelper;
import interfaces.ICityFacadeHelper;

public class CityFacadeHelper implements ICityFacadeHelper {


    public boolean checkValidBookTitleInput(String bookTitle)  {

        if(bookTitle == null || bookTitle == ""){
            return false;
        }
        if(bookTitle.length() > 500){
            return false;
        }
        return true;
    }
}
