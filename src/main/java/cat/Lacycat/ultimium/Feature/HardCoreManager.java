package cat.Lacycat.ultimium.Feature;

public class HardCoreManager {
    private static int Hard = 0;
    public static void add(int n) { Hard += n; }
    public static void subtract(int n) { Hard -= n; }
    public static int get() { return Hard; }

}
