package cat.Lacycat.ultimium.Feature;

import cat.Lacycat.ultimium.Feature.Curse.Curse;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HardCoreManager {
    private JavaPlugin plugin;
    public HardCoreManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private int Hard = 0;
    private List<Curse> curses = new ArrayList<>();
    public void add(int n) { Hard += n; }
    public void subtract(int n) { Hard -= n; }
    public int get() { return Hard; }
    public void registerCurse(Curse c) { curses.add(c); }
    public void unregisterCurse(Curse c) { curses.remove(c); }
    public void initCurse() {
        for (Curse i : curses) {
            i.register(plugin);
        }
    }
}
