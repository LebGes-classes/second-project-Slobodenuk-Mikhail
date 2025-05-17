public class Product {
    int ID;
    int PlaceID;
    String name;
    int price;
    String place;

    public final static String PLACE_ID = "PlaceID";
    public final static String NAME = "Name";
    public final static String PRICE = "Price";
    public final static String PLACE = "Place";


    public Product(String name, int placeID, String place, int price) {
        this.name = name;
        PlaceID = placeID;
        this.place = place;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaceID() {
        return PlaceID;
    }

    public void setPlaceID(int placeID) {
        PlaceID = placeID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "ID = " + ID + ", name - " + name + ", price = " + price;
    }
}
