package facades;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;




@RunWith(JUnitParamsRunner.class)
public class FacadeHelperParameterizedTest {

    @Test
    @FileParameters("src/test/java/test/resources/story1-invalidinput.csv")
    public void loadParamsFromFileWithIdentityMapperInvalid(String city) {
        FacadeHelper bookFacadeHelper = new FacadeHelper();
        assertThat(bookFacadeHelper.checkValidCityInput(city), is(equalTo(false)));

    }
    @Test
    @FileParameters("src/test/java/test/resources/story1-notfoundinput.csv")
    public void loadParamsFromFileWithIdentityMapperNotFound(String city) {
        FacadeHelper bookFacadeHelper = new FacadeHelper();
        assertThat(bookFacadeHelper.checkValidCityInput(city), is(equalTo(true)));

    }
    @Test
    @FileParameters("src/test/java/test/resources/story1-validinput.csv")
    public void loadParamsFromFileWithIdentityMapperValid(String city) {
        FacadeHelper bookFacadeHelper = new FacadeHelper();
        assertThat(bookFacadeHelper.checkValidCityInput(city), is(equalTo(true)));

    }

}


