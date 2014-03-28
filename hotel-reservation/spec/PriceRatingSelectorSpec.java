import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class PriceRatingSelectorSpec {
    Hotel cheaperRating5 = new Hotel("cheaperRating5", 5);
    Hotel cheaperRating4 = new Hotel("cheaperRating5", 4);
    Hotel expensiveRating4 = new Hotel("expensiveRating4", 4);

    @Before
    public void setup() {
        cheaperRating5.setRate(CustomerType.Regular, 10, 10);
        cheaperRating4.setRate(CustomerType.Regular, 10, 10);
        expensiveRating4.setRate(CustomerType.Regular, 20, 20);
    }

    @Test
    public void shouldReturnCheaperPrice() throws ParseException {
        PriceRatingSelector selector = new PriceRatingSelector();
        assertTrue(selector.isBetter(expensiveRating4, cheaperRating4, new Order("Regular: 16Mar2009(mon)")));
    }

    @Test
    public void shouldReturnHigherRatingingIfPricesAreTheSame() throws ParseException {
        PriceRatingSelector selector = new PriceRatingSelector();
        assertTrue(selector.isBetter(cheaperRating4, cheaperRating5, new Order("Regular: 16Mar2009(mon)")));
    }
}
