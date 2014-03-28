public class PriceRatingSelector implements IHotelSelector {
    @Override
    public boolean isBetter(IHotel current, IHotel candidate, Order order) {
        int currentPrice = current.getPrice(order.getCustomerType(), order.getReservedDates());
        int candidatePrice = candidate.getPrice(order.getCustomerType(), order.getReservedDates());
        if (candidatePrice != currentPrice) {
            return candidatePrice < currentPrice;
        } else {
            return candidate.getRating() > current.getRating();
        }
    }
}
