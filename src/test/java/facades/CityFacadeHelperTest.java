/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreas
 */
public class CityFacadeHelperTest {
    
    
    /**
     * Test of checkValidBookTitleInput method, of class CityFacadeHelper.
     */
    @Test
    public void testCheckValidBookTitleInput() {
        CityFacadeHelper helper = new CityFacadeHelper();
        assertTrue(!helper.checkValidBookTitleInput(""));
    }
   
     /**
     * Test of checkValidBookTitleInput method, of class CityFacadeHelper.
     */
    @Test
    public void testCheckValidBookTitleInput2() {
        CityFacadeHelper helper = new CityFacadeHelper();
        assertTrue(helper.checkValidBookTitleInput(" "));
    }
    
       /**
     * Test of checkValidBookTitleInput method, of class CityFacadeHelper.
     */
    @Test
    public void testCheckValidBookTitleInput3() {
        CityFacadeHelper helper = new CityFacadeHelper();
        assertTrue(helper.checkValidBookTitleInput("asd "));
    }
}
