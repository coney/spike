public class HotelReservationApp {
    public static void main(String[] args) {
        try {
            HotelManager hotelManager = new HotelManager();

            System.out.println(hotelManager.handleOrder(new Order(
                    "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)"
            )).getName());

            System.out.println(hotelManager.handleOrder(new Order(
                    "Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)"
            )).getName());

            System.out.println(hotelManager.handleOrder(new Order(
                    "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)"
            )).getName());

        } catch (Exception e) {
            System.out.println("got an error: " + e.toString());
        }
    }
}
