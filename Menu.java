import java.util.*;

public class Menu {
    Scanner scan = new Scanner(System.in);
    static Map<String, List<Map<String, String>>> workbook;
    static Data data = new Data();




    public Menu() throws Exception {
        String key = "";
        while (!Objects.equals(key, "q")) {
            workbook = data.allData;
            System.out.println(" 1) Действия с продуктами \n 2) Действия со складами \n 3) Действия с магазинами \n 4) Действия с работниками \n q - выход из программы");
            key = scan.nextLine();
            switch (key) {
                case "1":
                    new ActionsWithProduct();
                    break;

                case "2":
                    new ActionsWithWarehouse();
                    break;

                case "3":
                    new ActionsWithShop();
                    break;

                case "4":
                    new ActionsWithEmployee();
                    break;

                case "q":
                    WorkWithFile.saveExcel();
                    System.out.println("Файл Excel обновлён");
                    System.exit(0);



                default:
                    System.out.println("Введены некоректные данные");
                    break;
            }
        }
    }
}
