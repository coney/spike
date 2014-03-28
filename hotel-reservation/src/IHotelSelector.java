public interface IHotelSelector {
    boolean isBetter(IHotel current, IHotel candidate, Order order);
}
