package cat.Lacycat.ultimium.Feature.Curse;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class KillPlayerCurse implements Curse {

    private static boolean isEnabled = false;
    private static int Intensity = 1;
    /** 1 ~ 25사이 랜덤 값을 scale 형식으로 사용함*/
    private static final int scale =
            ThreadLocalRandom.current().nextInt(1, 25);

    @Override
    public String getName() { return "살인의 저주"; }

    @Override
    public String getNameColor() {
        return "#E942B6";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean usesIntensity() {
        return true;
    }

    @Override
    public int getIntensity() {
        return Intensity;
    }

    @Override
    public void addIntensity(int n) { Intensity += n; }

    @Override
    public boolean getEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean n) { isEnabled = n; }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent ev) {
        Player player = ev.getEntity().getKiller();
        if (isEnabled) {
            if((Objects.requireNonNull(ev.getEntity().getLastDamageCause())).getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                double chance = Math.round(
                        (1.0 - Math.exp((double) -Intensity / scale)) * 100.0);
                if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                    if (player == null) return;

                    while(ThreadLocalRandom.current().nextBoolean()) {
                        if (ThreadLocalRandom.current().nextBoolean()) {
                            player.setHealth(player.getHealth() - 0.5);

                        } else if (player.getHealth() <= ThreadLocalRandom.current().nextDouble(3.0, 5.0)) break;

                    }
                }
            }
        }
    }

    @Override
    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
