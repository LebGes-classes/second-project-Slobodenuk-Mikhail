import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ActionsWithEmployee {
    Scanner scan = new Scanner(System.in);

    public ActionsWithEmployee() throws Exception {
        Map<String, List<Map<String, String>>> workbook = Menu.workbook;
        BaseOfID baseOfID = Menu.data.baseOfID;


        System.out.println(" 1) Добавить сотрудника \n 2) Уволить сотрудника \n q - выход в меню");
        String key = scan.nextLine();
        int EmployeeID;
        int PlaceID;
        String FIO;

        switch (key) {
            case "1":
                System.out.println("Введите ФИО нового сотрудника");
                FIO = scan.next();
                System.out.println("На склад или в магазин?");
                System.out.println(" 1) Warehouse \n 2) Shop");
                int place = scan.nextInt();
                if(place > 2 || place < 1) {
                    System.out.println("Выберите коректный номер");
                    place = scan.nextInt();
                }
                if (place == 1) {
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
                }
                else{
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
                }
                String placeLit = place == 1 ? "w" : "s";
                System.out.println("Введите зарплату нового сотрудника");
                int price = scan.nextInt();
                WorkWithData.addEmployee(FIO, PlaceID, placeLit, price);

                WorkWithFile.saveJSON(workbook);
                System.out.println("Новый работник добавлен");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "2":
                System.out.println("Выберите сотрудника");
                for (Employee employee : baseOfID.arrayEmployee) {
                        System.out.println(employee.ID + " - " + employee.FIO);
                }
                EmployeeID = scan.nextInt();
                // проверка на наличие работника
                while (  ( (EmployeeID > baseOfID.arrayEmployee.size()) || (EmployeeID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    EmployeeID = scan.nextInt();
                }
                WorkWithData.removeEmployee(EmployeeID);
                WorkWithFile.saveJSON(workbook);
                System.out.println("Сотрудник уволен");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "q":
                new Menu();
                break;

            default:
                System.out.println("Введены некоректные данные");
                new ActionsWithEmployee();
                break;
        }
    }
}
