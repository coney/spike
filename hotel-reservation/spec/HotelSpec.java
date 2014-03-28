import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class HotelSpec {

    @Test
    public void shouldAbleToGetNameAndRatingAfterCreates() {
        Hotel hotel = new Hotel("Lakewood", 4);
        assertEquals("Lakewood", hotel.getName());
        assertEquals(4, hotel.getRating());
    }

    @Test
    public void shouldCalculatePriceAccordingToRatings() throws ParseException{
        Hotel hotel = new Hotel("Lakewood", 4);
        hotel.setRate(CustomerType.Regular, 10, 20);
        hotel.setRate(CustomerType.Rewards, 30, 40);
        SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(formator.parse("2014-03-28"));    // Friday
        dates.add(formator.parse("2014-03-29"));    // Saturday

        assertEquals(30, hotel.getPrice(CustomerType.Regular, dates));
        assertEquals(70, hotel.getPrice(CustomerType.Rewards, dates));
    }
}