import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ActionsWithWarehouse {
    Scanner scan = new Scanner(System.in);

    public ActionsWithWarehouse() throws Exception {
        Map<String, List<Map<String, String>>> workbook = Menu.workbook;
        BaseOfID baseOfID = Menu.data.baseOfID;



        System.out.println(" 1) Создать склад \n 2) Удалить склад \n 3) Показать продукты склада \n 4) Показать работников \n q - выход в меню");
        String key = scan.nextLine();
        int PlaceID;
        String address;
        switch (key) {
            case "1":
                System.out.println("Введите адрес нового склада");
                address = scan.nextLine();
                WorkWithData.addWarehouse(address);
                WorkWithFile.saveJSON(workbook);
                System.out.println("Новый склад добавлен");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "2":
                System.out.println("Выберите склад");
                for (Place warehouse : baseOfID.mapPlace.get(Data.WAREHOUSE)) {
                        System.out.println(warehouse.ID + " - " + warehouse.address);
                }
                PlaceID = scan.nextInt();
                // проверка на наличие склада
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.WAREHOUSE).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }

                WorkWithData.removeWarehouse(PlaceID);
                WorkWithFile.saveJSON(workbook);
                System.out.println("Склад удалён");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "3":
                System.out.println("Выберите склад");
                for (Place warehouse : baseOfID.mapPlace.get(Data.WAREHOUSE)) {
                    System.out.println(warehouse.ID + " - " + warehouse.address);
                }
                PlaceID = scan.nextInt();
                // проверка на наличие склада
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.WAREHOUSE).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }
                System.out.println("Список товаров:");
                for (Product product : baseOfID.arrayProduct) {
                    if (Objects.equals(product.place, "w") && (product.PlaceID == PlaceID)) {
                        System.out.println(product);
                    }
                }
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "4":
                System.out.println("Выберите склад");
                for (Place warehouse : baseOfID.mapPlace.get(Data.WAREHOUSE)) {
                    System.out.println(warehouse.ID + " - " + warehouse.address);
                }
                PlaceID = scan.nextInt();
                // проверка на наличие склада
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.WAREHOUSE).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }
                System.out.println("Список работников:");
                for (Employee employee : baseOfID.arrayEmployee) {
                    if (Objects.equals(employee.place, "w") && (employee.placeID == PlaceID)) {
                        System.out.println(employee);
                    }
                }
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;


            case "q":
                new Menu();
                break;

            default:
                System.out.println("Введены некоректные данные");
                new ActionsWithWarehouse();
                break;

        }
    }
}
