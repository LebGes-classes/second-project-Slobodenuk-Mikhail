import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ActionsWithShop {
    Scanner scan = new Scanner(System.in);

    public ActionsWithShop() throws Exception {
        Map<String, List<Map<String, String>>> workbook = Menu.workbook;
        BaseOfID baseOfID = Menu.data.baseOfID;

        System.out.println(baseOfID.mapPlace.get(Data.SHOP));
        System.out.println(" 1) Создать магазин \n 2) Удалить магазин \n 3) Показать продукты магазина \n 4) Показать работников \n 5) Изменить управляющего \n q - выход в меню");
                String key = scan.nextLine();
        int PlaceID;
        String address;
        int bossID;
        switch (key) {

            case "1":
                System.out.println("Введите адрес нового магазина");
                address = scan.nextLine();
                System.out.println("Выберите управляющего новым магазином");
                for (Employee employee : baseOfID.arrayEmployee) {
                        System.out.println(employee.ID + " - " + employee.FIO);
                }
                bossID = scan.nextInt();
                // проверка на наличие работника
                while (  ( (bossID > baseOfID.arrayEmployee.size()) || (bossID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    bossID = scan.nextInt();
                }
                WorkWithData.addShop(address, bossID);
                PlaceID = baseOfID.mapPlace.size() + 1;
                workbook.get(Data.EMPLOYEE).get(bossID-1).replace(Employee.PLACE_ID, String.valueOf(PlaceID));
                WorkWithFile.saveJSON(workbook);
                System.out.println("Новый магазин добавлен");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "2":
                System.out.println("Выберите магазин");
                for (Place shop : baseOfID.mapPlace.get(Data.SHOP)) {
                        System.out.println(shop.ID + " - " + shop.address);
                }
                PlaceID = scan.nextInt();
                // проверка на наличие магазина
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.SHOP).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }

                WorkWithData.removeShop(PlaceID);
                WorkWithFile.saveJSON(workbook);
                System.out.println("Магазин удалён");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "3":
                System.out.println("Выберите магазин");
                for (Place shop : baseOfID.mapPlace.get(Data.SHOP)) {
                        System.out.println(shop.ID + " - " + shop.address);
                }
                PlaceID = scan.nextInt();
                // проверка на наличие магазина
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.SHOP).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }

                System.out.println("Список товаров:");
                for (Product product : baseOfID.arrayProduct) {
                    if (Objects.equals(product.place, "s") && (product.PlaceID == PlaceID)) {
                        System.out.println(product);
                    }
                }
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "4":
               System.out.println("Выберите магазин");
               for (Place shop : baseOfID.mapPlace.get(Data.SHOP)) {
                        System.out.println(shop.ID + " - " + shop.address);
                    }
               PlaceID = scan.nextInt();
               // проверка на наличие магазина
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.WAREHOUSE).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }
                bossID = baseOfID.shopByID(PlaceID).getBossID() + 1;
                System.out.println(baseOfID.shopByID(PlaceID));
                if (bossID == 0) {
                    System.out.println("Управляющего нет");
                }
                else {
                    System.out.println("Управляющий - " + baseOfID.arrayEmployee.get(bossID-1).FIO + " ID = " + bossID);
                }

                System.out.println("Список работников:");
                for (Employee employee : baseOfID.arrayEmployee) {
                    if (Objects.equals(employee.place, "s") && (employee.placeID == PlaceID) && (employee.ID != bossID)) {
                        System.out.println(employee);
                    }
                }
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "5":
               System.out.println("Выберите магазин");
               for (Place shop : baseOfID.mapPlace.get(Data.SHOP)) {
                        System.out.println(shop.ID + " - " + shop.address);
               }
                PlaceID = scan.nextInt();
                // проверка на наличие магазина
                while (  ( (PlaceID > baseOfID.mapPlace.get(Data.WAREHOUSE).size()) || (PlaceID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    PlaceID = scan.nextInt();
                }

                System.out.println("Выберите нового управляющего");
                for (Employee employee : baseOfID.arrayEmployee) {
                        System.out.println(employee.ID + " - " + employee.FIO);
                }
                bossID = scan.nextInt();
                // проверка на наличие работника
                while (  ( (bossID > baseOfID.arrayEmployee.size()) || (bossID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    bossID = scan.nextInt();
                }
                workbook.get(Data.SHOP).get(PlaceID-1).replace(Shop.BOSS_ID, String.valueOf(bossID));
                WorkWithFile.saveJSON(workbook);
                System.out.println("Управляющий обновлён");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "q":
                new Menu();
                break;

            default:
                System.out.println("Введены некоректные данные");
                new ActionsWithShop();
                break;

        }
    }
}
