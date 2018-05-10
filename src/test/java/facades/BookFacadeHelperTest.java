package facades;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class BookFacadeHelperTest {

    @Test
    public void checkInvalidCityInput() {
        BookFacadeHelper helper = new BookFacadeHelper();
        assertThat(helper.checkValidCityInput(""), is(equalTo(false)));
    }
    @Test
    public void checkInvalidCityInput2() {
        BookFacadeHelper helper = new BookFacadeHelper();
        assertThat(helper.checkValidCityInput("  "), is(equalTo(false)));

    }

    /**todo parameterized tests*/
    @Test
    public void isNumeric() {
        BookFacadeHelper helper = new BookFacadeHelper();
        assertTrue(helper.isNumeric("123"));
    }
    @Test
    public void isNumeric2() {
        BookFacadeHelper helper = new BookFacadeHelper();
        assertTrue(helper.isNumeric("-123"));
    }
    @Test
    public void isNumeric3() {
        BookFacadeHelper helper = new BookFacadeHelper();
        assertTrue(!helper.isNumeric("hjkj"));
    }
}