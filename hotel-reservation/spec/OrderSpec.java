import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class OrderSpec {
    @Test
    public void shouldGetCorrectCustomerTypeFromInput() throws ParseException {
        assertEquals(CustomerType.Regular, new Order("Regular: 16Mar2009(mon)").getCustomerType());
        assertEquals(CustomerType.Rewards, new Order("Rewards: 16Mar2009(mon)").getCustomerType());
    }

    @Test
    public void shouldGetCorrectReservedDatesFromInput() throws ParseException {
        ArrayList<Date> dates = new Order("Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)").getReservedDates();
        assertEquals(3, dates.size());
        SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2009-03-16", formator.format(dates.get(0)));
        assertEquals("2009-03-17", formator.format(dates.get(1)));
        assertEquals("2009-03-18", formator.format(dates.get(2)));
    }
}
