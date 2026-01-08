package cat.Lacycat.ultimium;

import cat.Lacycat.ultimium.Feature.Curse.KeepInventoryCurse;
import cat.Lacycat.ultimium.Feature.Manager.HardCoreManager;
import cat.Lacycat.ultimium.Feature.Listener.BlockBreakListener;
import cat.Lacycat.ultimium.Feature.Listener.EntityDeathListener;
import cat.Lacycat.ultimium.Feature.Misc.SomethingFunny;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class Ultimium extends JavaPlugin {
    public SomethingFunny sf = new SomethingFunny(this);
    public HardCoreManager hcm = new HardCoreManager(this, sf);
    public List<Listener> AllTriggers = new ArrayList<>();
    @Override
    public void onEnable() {
        AllTriggers.add(new BlockBreakListener(hcm));
        AllTriggers.add(new EntityDeathListener(hcm));
        for (Listener i : AllTriggers) {
            Bukkit.getPluginManager().registerEvents(i,this);
        }
        hcm.registerCurse(new KeepInventoryCurse());
        hcm.initCurse();
    }

    @Override
    public void onDisable() {

    }
}
