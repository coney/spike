import java.util.ArrayList;

public class HotelManager {

    private ArrayList<Hotel> hotels = new ArrayList<>();

    public HotelManager() {
        loadHotelsData();
    }

    private void loadHotelsData() {
        Hotel lakewood = new Hotel("Lakewood", 3);
        lakewood.setRate(CustomerType.Regular, 110, 90);
        lakewood.setRate(CustomerType.Rewards, 80, 80);
        hotels.add(lakewood);

        Hotel bridgewood = new Hotel("Bridgewood", 4);
        bridgewood.setRate(CustomerType.Regular, 160, 60);
        bridgewood.setRate(CustomerType.Rewards, 110, 50);
        hotels.add(bridgewood);

        Hotel ridgewood = new Hotel("Ridgewood", 5);
        ridgewood.setRate(CustomerType.Regular, 220, 150);
        ridgewood.setRate(CustomerType.Rewards, 100, 40);
        hotels.add(ridgewood);
    }

    public Hotel handleOrder(Order order) {
        Hotel currentHotel = null;
        for (Hotel candidate : hotels) {
            if (isBetter(currentHotel, candidate, order)) {
                currentHotel = candidate;
            }
        }
        return currentHotel;
    }

    private Boolean isBetter(Hotel current, Hotel candidate, Order order) {
        if (current == null) {
            return true;
        }

        int currentPrice = current.getPrice(order.getCustomerType(), order.getReservedDates());
        int candidatePrice = candidate.getPrice(order.getCustomerType(), order.getReservedDates());
        if (candidatePrice != currentPrice) {
            return candidatePrice < currentPrice;
        } else {
            return candidate.getRating() > current.getRating();
        }
    }

}
