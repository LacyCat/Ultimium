package cat.Lacycat.ultimium.Feature;

import cat.Lacycat.ultimium.Feature.Curse.Curse;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
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
        if (l == null) {
            l = new ArrayList<>();
        }
        l.add(c);
        curses.put(c.getPriority(),l);
    }

    /**
     * 저주를 등록 해제합니다.
     * @param c 해제할 클래스 (클래스 종류만 맞으면 됩니다.)
     */
    public void unregisterCurse(Curse c) {
        List<Curse> l = curses.get(c.getPriority());
        l.removeIf(curse -> curse.getClass() == c.getClass());
    }

    /**
     * 한 우선순위의 저주 리스트를 가져옵니다.
     * @param priority 가져올 우선순위
     * @return 저주 리스트
     */
    public List<Curse> getFromMap(int priority) {
        return curses.get(priority);
    }

    /**
     * 저주 객체를 덮어씁니다.
     * @param c 덮어쓸 원래 있던 저주와 같은 클래스의 저주
     */
    public void replace(Curse c) {
        List<Curse> l = curses.get(c.getPriority());
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getClass() == c.getClass()) {
                l.set(i,c);
                return;
            }
        }
    }

    /**
     * 전체 우선순위를 덮어씁니다.
     * @param l l의 하위 원소 (Curse를 상속)들은 무조건 한가지의 우선순위 종류로만 이루어져야합니다.
     */
    public void replaceALL(List<Curse> l) {
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

    /**
     * 한 우선순위의 저주들중 하나를 뽑은 뒤 이에 대해 액션을 실행합니다.
     * @param priority 하나를 뽑을 우선순위
     * @param action 람다식 액션
     */
    public void pickOne(int priority, BiConsumer<Curse,HardCoreManager> action) {
        List<Curse> l = curses.get(priority);
        if (l == null || l.isEmpty()) return;
        Curse picked = l.get(ThreadLocalRandom.current().nextInt(l.size()));
        action.accept(picked,this);
    }

    /**
     * 한 우선순위의 모든 저주가 활성화되었는지 확인합니다.
     * @param priority 체크할 우선순위
     * @return 모든 저주가 활성화되었는지 확인합니다.
     */
    public boolean checkIsPriorityAllEnabled(int priority) {
        List<Curse> l = curses.get(priority);
        int disabled = 0;
        for (Curse i : l) {
            if (!i.getEnabled()) disabled++;
        }
        return disabled == 0;
    }

    private boolean checkDuplicated(Curse c) {
        List<Curse> l = curses.get(c.getPriority());
        int count = 0;
        for (Curse i : l) {
            if (i.getClass() == c.getClass()) count++;
        }
        return count >= 2;
    }
}
