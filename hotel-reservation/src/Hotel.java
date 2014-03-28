import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Hotel implements IHotel {

    class RateEntry {
        private int weekdayRates;
        private int weekendRates;

        public int getWeekdayRates() {
            return weekdayRates;
        }

        public int getWeekendRates() {
            return weekendRates;
        }

        RateEntry(int weekdayRates, int weekendRates) {
            this.weekdayRates = weekdayRates;
            this.weekendRates = weekendRates;
        }
    }

    private HashMap<CustomerType, RateEntry> rateTable = new HashMap<CustomerType, RateEntry>();
    private String name;
    private int rating;

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public Hotel(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public void setRate(CustomerType customerType, int weekdayRates, int weekendRates) {
        rateTable.put(customerType, new RateEntry(weekdayRates, weekendRates));
    }

    public int getPrice(CustomerType customerType, Iterable<Date> reservedDates) {
        int price = 0;
        RateEntry rate = rateTable.get(customerType);
        for (Date date : reservedDates) {
            int dayOfWeek = getDayOfWeek(date);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                price += rate.getWeekendRates();
            }
            else {
                price += rate.getWeekdayRates();
            }
        }
        return price;
    }

    private int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
