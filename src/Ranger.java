
public class Ranger extends Sprite {
    static String[] pose = {
            "up", "dn", "lt", "rt",
            "idl_dn", "idl_lt", "idl_rt",
            "dth_lt", "dth_rt",
            "fire_up", "fire_dn", "fire_lt", "fire_rt"};



    // can't figure out how to make sprite bigger/smaller
    public Ranger(int x, int y) {
        super(x, y, (int)(GameStart.screen.width*.2), (int)(GameStart.screen.height*.2), "rg", pose, 10, "PNG");
    }

}
