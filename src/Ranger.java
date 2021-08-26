public class Ranger extends Sprite {
    static String[] pose = {"up", "dn", "lt", "rt"};

    public Ranger(int x, int y) {
        super(x, y, 300, 300, "rg", pose, 10, "PNG");
    }
}
