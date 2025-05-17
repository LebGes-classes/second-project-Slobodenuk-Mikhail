import java.util.*;

public class ActionsWithProduct {

    Scanner scan = new Scanner(System.in);

    public ActionsWithProduct() throws Exception {
        Map<String, List<Map<String, String>>> workbook = Menu.workbook;
        BaseOfID baseOfID = Menu.data.baseOfID;

        System.out.println(baseOfID.mapPlace);
        System.out.println(" 1) Переместить товар \n 2) Купить товар \n 3) Удалить товар \n q - выход в меню" );
        String key = scan.nextLine();
        int ProductID;
        int place;
        int PlaceID;
        switch (key) {

            case "1":
                System.out.println("Выберите товар");
                for (Product product : baseOfID.arrayProduct) {
                    if (Objects.equals(product.place, "w")){
                        System.out.println(product.ID + " - " + product.name + " " + "склад - " + product.PlaceID);
                    }
                    else {
                        System.out.println(product.ID + " - " + product.name + " " + "магазин - " + product.PlaceID);
                    }

                }
                ProductID = scan.nextInt();
                // проверка на наличие продукта
                while (  ( (ProductID > baseOfID.arrayProduct.size()) || (ProductID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    ProductID = scan.nextInt();
                }
                System.out.println("На склад или в магазин?");
                System.out.println(" 1) Warehouse \n 2) Shop");
                place = scan.nextInt();
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
                    workbook.get(Data.PRODUCT).get(ProductID - 1).replace(Product.PLACE_ID, String.valueOf(PlaceID));
                    workbook.get(Data.PRODUCT).get(ProductID - 1).replace(Product.PLACE, "w");
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
                    workbook.get(Data.PRODUCT).get(ProductID - 1).replace(Product.PLACE_ID, String.valueOf(PlaceID));
                    workbook.get(Data.PRODUCT).get(ProductID - 1).replace(Product.PLACE, "s");
                    System.out.println(workbook);

                }

                WorkWithFile.saveJSON(workbook);
                System.out.println("Товар перемещён");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "2":
                System.out.println("На склад или в магазин?");
                System.out.println(" 1) Warehouse \n 2) Shop");
                place = scan.nextInt();
                String placeLit;
                if(place > 2 || place < 1) {
                    System.out.println("Выберите коректный номер");
                    place = scan.nextInt();
                }
                if (place == 1) {
                    System.out.println("Выберите склад");
                    for (Place warehouse : baseOfID.mapPlace.get(Data.WAREHOUSE)) {
                        System.out.println(warehouse.ID + " - " + warehouse.address);
                    }
                    placeLit = "w";
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
                    placeLit = "s";
                    PlaceID = scan.nextInt();
                    // проверка на наличие магазина
                    while (  ( (PlaceID > baseOfID.mapPlace.get(Data.SHOP).size()) || (PlaceID < 1) )  ) {
                        System.out.println("Выберите коректный номер");
                        PlaceID = scan.nextInt();
                    }
                }

                System.out.println("Введите имя нового товара");
                String name = scan.next();
                System.out.println("Введите цену нового товара");
                int price = scan.nextInt();
                WorkWithData.addProduct(name, PlaceID, placeLit, price);

                WorkWithFile.saveJSON(workbook);
                System.out.println("Товар добавлен");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "3":
                System.out.println("Выберите товар");
                for (Product product : baseOfID.arrayProduct) {
                    if (Objects.equals(product.place, "w")){
                        System.out.println(product.ID + " - " + product.name + " " + "склад - " + product.PlaceID);
                    }
                    else {
                        System.out.println(product.ID + " - " + product.name + " " + "магазин - " + product.PlaceID);
                    }
                }
                ProductID = scan.nextInt();
                // проверка на наличие продукта
                while (  ( (ProductID > baseOfID.arrayProduct.size()) || (ProductID < 1) )  ) {
                    System.out.println("Выберите коректный номер");
                    ProductID = scan.nextInt();
                }
                WorkWithData.removeProduct(ProductID);
                WorkWithFile.saveJSON(workbook);
                System.out.println("Товар удалён");
                System.out.println("Введиет любой символ для выхода в меню");
                key = scan.next();
                new Menu();
                break;

            case "q":
                new Menu();
                break;

            default:
                System.out.println("Введены некоректные данные");
                new ActionsWithProduct();
                break;

        }
    }
}
