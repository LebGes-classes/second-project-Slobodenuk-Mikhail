import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Data {
    BaseOfID baseOfID = new BaseOfID();
    Map<String, List<Map<String, String>>> allData;

    {
        try {
            allData = WorkWithFile.convertJsonToMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public final static String PRODUCT = "Product";
    public final static String WAREHOUSE = "Warehouse";
    public final static String SHOP = "Shop";
    public final static String EMPLOYEE = "Employee";

    public Data() {
        int cntProduct = allData.get(PRODUCT).size();
        int cntWarehouse = allData.get(WAREHOUSE).size();
        int cntShop = allData.get(SHOP).size();
        int cntEmployee = allData.get(EMPLOYEE).size();


        WorkWithData workWithData = new WorkWithData(baseOfID, allData);
        WorkWithFile workWithFile = new WorkWithFile(baseOfID, allData);

        // считываем продукты и заполняем массив
        for (int i = 0; i < cntProduct; i++) {
            String name = allData.get(PRODUCT).get(i).get(Product.NAME);
            int placeID = Integer.parseInt(allData.get(PRODUCT).get(i).get(Product.PLACE_ID));
            String place = allData.get(PRODUCT).get(i).get(Product.PLACE);
            int price = Integer.parseInt(allData.get(PRODUCT).get(i).get(Product.PRICE));

            workWithData.readProduct(placeID, place, name, price);
        }

        // Считываем склады и заполняем массив
        for (int i = 0; i < cntWarehouse; i++) {
            String address = allData.get(WAREHOUSE).get(i).get(Warehouse.ADDRESS);
            workWithData.readWarehouse(address);
        }

        // Считываем магазины и заполняем массив
        for (int i = 0; i < cntShop; i++) {
            String address = allData.get(SHOP).get(i).get(Shop.ADDRESS);
            int bossID = Integer.parseInt(allData.get(SHOP).get(i).get(Shop.BOSS_ID));
            workWithData.readShop(address, bossID);
        }

        // Считываем работников и заполняем массив
        for (int i = 0; i < cntEmployee; i++) {
            String FIO = allData.get(EMPLOYEE).get(i).get("FIO");
            int PlaceID = Integer.parseInt(allData.get(EMPLOYEE).get(i).get(Employee.PLACE_ID));
            String place = allData.get(EMPLOYEE).get(i).get(Product.PLACE);
            int salary = Integer.parseInt(allData.get(EMPLOYEE).get(i).get(Employee.SALARY));
            workWithData.readEmployee(FIO, PlaceID, place, salary);
        }

    }
}
