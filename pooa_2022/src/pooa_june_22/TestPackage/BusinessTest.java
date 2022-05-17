package pooa_june_22.TestPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pooa_june_22.BusinessPackage.SpecieManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
public class BusinessTest {
    private SpecieManager performance;

    @BeforeEach
    void setUp(){
        performance = new SpecieManager();
    }

    @Test
    void calcMetaScore(){
        assertEquals(1407.8999999999999, performance.calcMetaScore(4000, 4, 14));
    }
    @Test
    void calcAverage(){
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(18, 22, 44, 24));
        assertEquals(27, performance.calcAverage(list));
    }

    @Test
    void conversion(){
        assertEquals(31, performance.conversion(new Double(2678400000L)));
    }

    @Test
    void diffDate(){
        assertEquals(new Long(2678400000L), performance.diffDate(new GregorianCalendar(2022, 04, 8), new GregorianCalendar(2022, 05,8)));
    }

}
