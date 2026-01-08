package cat.Lacycat.ultimium.Feature;

import cat.Lacycat.ultimium.Feature.Curse.Curse;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused","ConstantValue"})
public class HardCoreManager {
    private final JavaPlugin plugin;
    public HardCoreManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private int Hard = 0;
    private final Map<Integer,List<Curse>> curses = new HashMap<>();

    /**
     * 난이도를 n만큼 더합니다.
     * @param n 더할 숫자
     */
    public void add(int n) { Hard += n; }

    /**
     * 난이도를 n만큼 뺍니다.
     * @param n 뺄 숫자
     */
    public void subtract(int n) { Hard -= n; }

    /**
     * 난이도를 반환합니다.
     * @return 난이도
     */
    public int get() { return Hard; }

    /**
     * 저주를 등록합니다.
     * @param c 등록할 저주
     */
    public void registerCurse(Curse c) {
        List<Curse> l = curses.get(c.getPriority());
        if (l != null) {
            l.add(c);
            curses.put(c.getPriority(),l);
        }
        else if (l == null) {
            l = new ArrayList<>();
            l.add(c);
            curses.put(c.getPriority(),l);
        }
    }

    /**
     * 전체 우선순위를 덮어씁니다.
     * @param l l의 하위 원소 (Curse를 상속)들은 무조건 한가지의 우선순위 종류로만 이루어져야합니다.
     */
    public void registerALL(List<Curse> l) {
        int t = 0;
        for (Curse i : l) {
            if (t == 0) t = i.getPriority();
            if (t != i.getPriority()) return;
        }
        curses.put(t,l);
    }

    /**
     * 모든 저주 이벤트 리스너를 등록합니다.
     * 무조건 실행되어야만 적용됩니다.
     */
    public void initCurse() {
        for (List<Curse> l : curses.values()) {
            for (Curse i : l) {
                i.register(plugin);
            }
        }
    }
}
