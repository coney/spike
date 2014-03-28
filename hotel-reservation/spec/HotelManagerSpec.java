import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class HotelManagerSpec {

    class NameSelector implements IHotelSelector {

        String name;

        NameSelector(String name) {
            this.name = name;
        }

        @Override
        public boolean isBetter(IHotel current, IHotel candidate, Order order) {
            // choose hotel by name
            return candidate.getName().equals(name);
        }
    }

    @Test
    public void shouldSelectBestHotelByGivenSelector() throws ParseException {
        HotelManager manager = new HotelManager(new NameSelector("Bridgewood"));
        assertEquals("Bridgewood", manager.handleOrder(new Order("Regular: 16Mar2009(mon)")).getName());
    }
}
