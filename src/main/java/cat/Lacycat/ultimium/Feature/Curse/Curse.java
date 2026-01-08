package cat.Lacycat.ultimium.Feature.Curse;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public interface Curse extends Listener {
    int getPriority();
    int getIntensity();
    void addIntensity(int n);
    boolean getEnabled();
    void setEnabled(boolean n);
}
