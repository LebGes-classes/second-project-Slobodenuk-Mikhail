import java.util.ArrayList;

public class Shop extends Place{
    static int bossID;

    public final static String ADDRESS = "Address";
    public final static String BOSS_ID = "BossID";

    public Shop(String address, int bossID) {
        this.address = address;
        this.bossID = bossID;
    }

    @Override
    public String toString() {
        return "ID = " + ID + ", address=" + address + ", bossID=" + bossID;
    }
}
