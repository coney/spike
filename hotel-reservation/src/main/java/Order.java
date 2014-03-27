import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {

    static Pattern orderPattern = Pattern.compile("^(Regular|Rewards): (.+)$");

    private ArrayList<Date> reservedDates = new ArrayList<>();

    private CustomerType customerType;

    public ArrayList<Date> getReservedDates() {
        return reservedDates;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Order(String input) throws ParseException {
        parseOrder(input);
    }

    private void parseOrder(String input) throws ParseException {
        Matcher result = orderPattern.matcher(input);
        if (!result.matches()) {
            throw new ParseException(input, 0);
        }

        parseCustomerType(result.group(1));
        parseReservedDates(result.group(2));
    }

    private void parseCustomerType(String type) throws ParseException {
        if (type.equals("Regular")) {
            customerType = CustomerType.Regular;
        } else if (type.equals("Rewards")) {
            customerType = CustomerType.Rewards;
        } else {
            throw new ParseException(type, 0);
        }
    }

    private void parseReservedDates(String dates) throws ParseException {
        String[] inputDates = dates.split(",");
        for (String date : inputDates) {
            parseReservedDate(date.trim());
        }
    }

    private void parseReservedDate(String date) throws ParseException {
        // replace non-standard week name
        date = date.replace("tues", "tue").replace("thur", "thu");
        SimpleDateFormat formator = new SimpleDateFormat("ddMMMyyyy(EEE)", Locale.ENGLISH);
        getReservedDates().add(formator.parse(date));
    }
}
