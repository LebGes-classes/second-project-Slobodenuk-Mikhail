public class MainMarket {
    public static void main(String[] args) throws Exception {
        WorkWithFile.saveJSON(WorkWithFile.convertExceltoJson());
        new Menu();
    }


}