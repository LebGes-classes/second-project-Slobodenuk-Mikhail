public class Employee {
    int ID;
    String FIO;
    int placeID;
    String place;
    int salary;

    public final static String PLACE = "Place";
    public final static String PLACE_ID = "PlaceID";
    public final static String SALARY = "Salary";

    public Employee(String FIO, int placeID, String place, int salary) {
        this.placeID = placeID;
        this.place = place;
        this.FIO = FIO;
        this.salary = salary;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getPlace() {
        return place;
    }

    public int getSalary() {
        return salary;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID = " + ID +
                ", FIO - " + FIO +
                ", salary = " + salary;
    }
}
