import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseOfID {
    static Map<String, ArrayList<Place>> mapPlace = new HashMap<>();
    static ArrayList<Employee> arrayEmployee = new ArrayList<>();
    static ArrayList<Product> arrayProduct = new ArrayList<>();

    public BaseOfID(){
        ArrayList<Place> arrayShop = new ArrayList<>();
        mapPlace.put("Shop", arrayShop);
        ArrayList<Place> arrayWarehouse = new ArrayList<>();
        mapPlace.put("Warehouse", arrayWarehouse);
    }

    public void addWarehouse(Place warehouse){
        ArrayList<Place> arrayWarehouse = mapPlace.get("Warehouse");
        arrayWarehouse.add(warehouse);
        warehouse.setID(arrayWarehouse.indexOf(warehouse) + 1);
    }

    public void addShop(Place shop){
        ArrayList<Place> arrayShop = mapPlace.get("Shop");
        arrayShop.add(shop);
        shop.setID(arrayShop.indexOf(shop) + 1);
    }


    public void addEmployee(Employee employee){
        arrayEmployee.add(employee);
        employee.setID(arrayEmployee.indexOf(employee) + 1);
    }


    public void addProduct(Product product) {
        arrayProduct.add(product);
        product.setID(arrayProduct.indexOf(product) + 1);
    }


    public Place shopByID(int id) {
        for (Place arrayShop : mapPlace.get("Shop")) {
            if (id == arrayShop.getID()) {
                return arrayShop;
            }
        }
        return null;
    }

    public Place warehouseByID(int id) {
        for (Place arrayWarehouse : mapPlace.get("Warehouse")) {
            if (id == arrayWarehouse.getID()) {
                return arrayWarehouse;
            }
        }
        return null;
    }

    public Employee employeeByID(int id) {
        for (Employee employee : arrayEmployee) {
            if (id == employee.getID()) {
                return employee;
            }
        }
        return null;
    }

    public Product productByID(int id) {
        for (Product product : arrayProduct) {
            if (id == product.getID()) {
                return product;
            }
        }
        return null;
    }

    public void removeShop(int id) {
        mapPlace.get("Shop").remove(shopByID(id));
    }

    public void removeWarehouse(int id) {
        mapPlace.get("Warehouse").remove(warehouseByID(id));
    }


    public void removeEmployee(int id) {
        arrayEmployee.remove(employeeByID(id));
    }

    public void removeProduct(int id) {
        arrayProduct.remove(productByID(id));
    }

//    public static ArrayList<Place> getMapStudent() {
//        return mapPlace;
//    }

//    public static ArrayList<Product> getArrayProduct() {
//        return arrayProduct;
//    }

    public static ArrayList<Employee> getArrayEmployee() {
        return arrayEmployee;
    }
}
