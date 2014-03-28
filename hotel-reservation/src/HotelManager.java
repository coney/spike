import java.util.ArrayList;

public class HotelManager {

    private ArrayList<IHotel> hotels = new ArrayList<IHotel>();
    IHotelSelector selector;
    public HotelManager(IHotelSelector selector) {
        this.selector = selector;
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

    public IHotel handleOrder(Order order) {
        IHotel currentHotel = null;
        for (IHotel candidate : hotels) {
            if (currentHotel == null || selector.isBetter(currentHotel, candidate, order)) {
                currentHotel = candidate;
            }
        }
        return currentHotel;
    }
}
