import java.util.Date;

public interface IHotel {
    public String getName();

    public int getRating();

    public int getPrice(CustomerType customerType, Iterable<Date> reservedDates);
}
