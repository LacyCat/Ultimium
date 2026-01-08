package cat.Lacycat.ultimium.Feature.Listener;

import cat.Lacycat.ultimium.Feature.HardCoreManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import java.util.Random;

public class EntityDeathListener implements Listener {
    private HardCoreManager hcm;
    public EntityDeathListener(HardCoreManager hcm) {
        this.hcm = hcm;
    }

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent ev) {
        var source = ev.getDamageSource();
        var entity = ev.getEntity();

        if (source.getCausingEntity() instanceof Player player) {
            Random r = new Random();
            if (r.nextBoolean()) {
                Bukkit.broadcast(Component.text(player.getName() + "님이 " + entity.getName() + "를 죽였습니다."));
                hcm.add(1);
            }
        }
    }
}
