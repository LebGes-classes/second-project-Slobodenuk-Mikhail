import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WorkWithData {
    static Map<String, List<Map<String, String>>> workbook;
    static BaseOfID baseOfID;

    public WorkWithData(BaseOfID baseOfID, Map<String, List<Map<String, String>>> workbook) {
        WorkWithData.baseOfID = baseOfID;
        WorkWithData.workbook = workbook;
    }

    public void readShop(String address, int bossID) {
        if (!Objects.equals(address, "")) {
            Place shop = new Shop(address, bossID);
            baseOfID.addShop(shop);
        }

    }

    public void readWarehouse(String address) {
        if (!Objects.equals(address, "")) {
            Place warehouse = new Warehouse(address);
            baseOfID.addWarehouse(warehouse);
        }

    }

    public void readProduct(int placeID, String place, String name, int price) {
        if (!Objects.equals(name, "")) {
            Product product = new Product(name, placeID, place, price);
            baseOfID.addProduct(product);
        }
    }


    public void readEmployee(String FIO, int placeID, String place, int salary) {
        if (!Objects.equals(FIO, "")) {
            Employee employee = new Employee(FIO, placeID, place, salary);
            baseOfID.addEmployee(employee);
        }
    }


    public static void addWarehouse(String address) {
        if (!Objects.equals(address, "")) {
            Place warehouse = new Warehouse(address);
            baseOfID.addWarehouse(warehouse);
            String ID = String.valueOf(baseOfID.mapPlace.get(Data.WAREHOUSE).indexOf(warehouse) + 1);
            Map<String, String> newPlace = new LinkedHashMap<>();
            newPlace.put("ID", ID);
            newPlace.put(Warehouse.ADDRESS, address);
            workbook.get(Data.WAREHOUSE).add(newPlace);
        }
    }

    public static void addShop(String address, int bossID) {
        if (!Objects.equals(address, "")) {
            Place shop = new Shop(address, bossID);
            baseOfID.addShop(shop);
            String ID = String.valueOf(baseOfID.mapPlace.get("Shop").indexOf(shop) + 1);
            Map<String, String> newPlace = new LinkedHashMap<>();
            newPlace.put("ID", ID);
            newPlace.put(Warehouse.ADDRESS, address);
            newPlace.put(Shop.BOSS_ID, String.valueOf(bossID));
            workbook.get(Data.WAREHOUSE).add(newPlace);
        }
    }


    public static void addProduct(String name, int placeID, String place, int price) {
        if (!Objects.equals(name, "")) {
            Product product = new Product(name, placeID, place, price);
            baseOfID.addProduct(product);
            String ID = String.valueOf(baseOfID.arrayProduct.indexOf(product) + 1);
            Map<String, String> newProduct = new LinkedHashMap<>();
            newProduct.put("ID", ID);
            newProduct.put(Product.PLACE_ID, String.valueOf(placeID));
            newProduct.put(Product.PLACE, place);
            newProduct.put(Product.NAME, name);
            newProduct.put(Product.PRICE, String.valueOf(price));
            workbook.get(Data.PRODUCT).add(newProduct);
        }
    }

    public static void addEmployee(String FIO, int placeID, String place, int salary) {
        if (!Objects.equals(FIO, "")) {
            Employee employee = new Employee(FIO, placeID, place, salary);
            baseOfID.addEmployee(employee);
            String ID = String.valueOf(baseOfID.arrayEmployee.indexOf(employee) + 1);
            Map<String, String> newEmployee = new LinkedHashMap<>();
            newEmployee.put("ID", ID);
            newEmployee.put(Employee.PLACE_ID, String.valueOf(placeID));
            newEmployee.put("FIO", FIO);
            newEmployee.put(Employee.SALARY, String.valueOf(salary));
            workbook.get(Data.EMPLOYEE).add(newEmployee);
        }
    }

    public static void removeWarehouse(int ID) {
        //удаляем все продукты этого склада и обновляем айдишник оставшихся
        for (int i = 0; i < baseOfID.arrayProduct.size(); i++) {
            Product product = baseOfID.arrayProduct.get(i);
            if (Objects.equals(product.place, "w") && product.getPlaceID() == ID){
                removeProduct(i+1);
            }
            else if (Objects.equals(product.place, "w") && product.getPlaceID() > ID){
                product.setPlaceID(product.PlaceID - 1);
            }
        }

        //удаляем всех работников этого склада и обновляем айдишник оставшихся
        for (int i = 0; i < baseOfID.arrayEmployee.size(); i++) {
            Employee employee = baseOfID.arrayEmployee.get(i);
            if (Objects.equals(employee.place, "w") && employee.getPlaceID() == ID){
                removeProduct(i+1);
            }
            else if (Objects.equals(employee.place, "w") && employee.getPlaceID() > ID){
                employee.setPlaceID(employee.placeID - 1);
            }
        }

        baseOfID.removeWarehouse(ID);
        workbook.get(Data.WAREHOUSE).remove(ID-1);
        // обновляем айдишники
        for (int i = 0; i < BaseOfID.mapPlace.get(Data.WAREHOUSE).size(); i++) {
            BaseOfID.mapPlace.get(Data.WAREHOUSE).get(i).setID(i+1);
            workbook.get(Data.WAREHOUSE).get(i).replace("ID", String.valueOf(i+1));
        }
    }

    public static void removeShop(int ID) {
        //удаляем все продукты этого магазина и обновляем айдишник оставшихся
        for (int i = 0; i < baseOfID.arrayProduct.size(); i++) {
            Product product = baseOfID.arrayProduct.get(i);
            if (Objects.equals(product.place, "s") && product.getPlaceID() == ID){
                removeProduct(i+1);
            }
            else if (Objects.equals(product.place, "s") && product.getPlaceID() > ID){
                product.setPlaceID(product.PlaceID - 1);
            }
        }

        //удаляем всех работников этого магазина и обновляем айдишник оставшихся
        for (int i = 0; i < baseOfID.arrayEmployee.size(); i++) {
            Employee employee = baseOfID.arrayEmployee.get(i);
            if (Objects.equals(employee.place, "s") && employee.getPlaceID() == ID){
                removeProduct(i+1);
            }
            else if (Objects.equals(employee.place, "s") && employee.getPlaceID() > ID){
                employee.setPlaceID(employee.placeID - 1);
            }
        }

        baseOfID.removeShop(ID);
        workbook.get(Data.SHOP).remove(ID - 1);
        // обновляем айдишники
        for (int i = 0; i < baseOfID.mapPlace.get(Data.SHOP).size(); i++) {
            baseOfID.mapPlace.get(Data.SHOP).get(i).setID(i+1);
            workbook.get(Data.WAREHOUSE).get(i).replace("ID", String.valueOf(i+1));
        }
    }

    public static void removeProduct(int ID) {
        baseOfID.removeProduct(ID);
        workbook.get(Data.PRODUCT).remove(ID-1);

        // обновляем айдишники
        for (int i = 0; i < baseOfID.arrayProduct.size(); i++) {
            baseOfID.arrayProduct.get(i).setID(i+1);
            workbook.get(Data.PRODUCT).get(i).replace("ID", String.valueOf(i+1));
        }
    }

    public static void removeEmployee(int ID) {
        if (Objects.equals(baseOfID.employeeByID(ID).getPlace(), "s") ) {
            baseOfID.mapPlace.get(Data.SHOP).get(baseOfID.employeeByID(ID).placeID).setBossID(0);
        }

        baseOfID.removeEmployee(ID);
        workbook.get(Data.EMPLOYEE).remove(ID-1);
        // обновляем айдишники
        for (int i = 0; i < baseOfID.arrayEmployee.size(); i++) {
            baseOfID.arrayEmployee.get(i).setID(i+1);
            workbook.get(Data.EMPLOYEE).get(i).replace("ID", String.valueOf(i+1));
        }
    }
}
