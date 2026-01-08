package cat.Lacycat.ultimium.Feature.Listener;

import cat.Lacycat.ultimium.Feature.Manager.HardCoreManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import java.util.concurrent.ThreadLocalRandom;

public class EntityDeathListener implements Listener {
    private final HardCoreManager hcm;
    public EntityDeathListener(HardCoreManager hcm) {
        this.hcm = hcm;
    }

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent ev) {
        var source = ev.getDamageSource();
        double chance = Math.round((1.0 - Math.exp((double) -hcm.get() / 100)) * 100.0);
        if (source.getCausingEntity() instanceof Player) {
            if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                hcm.add(1);
            }
        }
        if (hcm.get() / 20 >= 1) {
            hcm.processCurseActivation();
        }
    }
}
