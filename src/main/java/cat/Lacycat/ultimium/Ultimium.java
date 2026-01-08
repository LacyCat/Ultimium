package cat.Lacycat.ultimium;

import cat.Lacycat.ultimium.Feature.Curse.KeepInventoryCurse;
import cat.Lacycat.ultimium.Feature.HardCoreManager;
import cat.Lacycat.ultimium.Feature.Listener.BlockBreakListener;
import cat.Lacycat.ultimium.Feature.Listener.EntityDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;

public final class Ultimium extends JavaPlugin {
    public HardCoreManager hcm = new HardCoreManager(this);
    public List<Listener> alltriggers = new ArrayList<>();

    @Override
    public void onEnable() {
        alltriggers.add(new BlockBreakListener(hcm));
        alltriggers.add(new EntityDeathListener(hcm));
        for (Listener i : alltriggers) {
            Bukkit.getPluginManager().registerEvents(i,this);
        }
        // Plugin startup logic
        hcm.registerCurse(new KeepInventoryCurse());
        hcm.initCurse();
    }

    @Override
    public void onDisable() {
        alltriggers.remove(new BlockBreakListener(hcm));
        alltriggers.remove(new EntityDeathListener(hcm));
        // Plugin shutdown logic
        hcm.unregisterCurse(new KeepInventoryCurse());

    }
}
