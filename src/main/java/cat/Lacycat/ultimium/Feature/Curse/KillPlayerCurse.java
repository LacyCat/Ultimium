package cat.Lacycat.ultimium.Feature.Curse;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class KillPlayerCurse implements Curse  {

    private static boolean isEnabled = false;
    /** 최대 3까지*/
    private static int Intensity = 1;

    private static final int scale = 25;

    @Override
    public String getName() { return "살인의 저주"; }

    @Override
    public String getNameColor() {
        return "#E942B6";
    }

    @Override
    public int getPriority() {
        return 1;
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
    public void addIntensity(int n) {  Intensity += n; }

    @Override
    public boolean getEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean n) { isEnabled = n; }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent ev)  {
        Player player = ev.getEntity().getKiller();
        if (isEnabled) {
            if (Intensity > 3) {
                Intensity = 3;
            }

            int damageMulti = switch (Intensity) {
                case 2 -> 3;
                case 3 -> 5;
                default -> 2;
            };

            if(((Objects.requireNonNull(ev.getEntity().getLastDamageCause())).getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) ||
                    (Objects.requireNonNull(ev.getEntity().getLastDamageCause())).getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK))) {
                double chance = Math.round((1.0 - Math.exp((double) -Intensity / scale)) * 100.0);
                if (player == null) return;
                double damage = ThreadLocalRandom.current().nextDouble(1.5, 3.5) * damageMulti + 1.0 / 2.0;
                if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                    player.sendActionBar(Component.text( "§c화가 난 영혼§f인 "+ev.getEntity().getName() +"(이)가 당신에게 §4저주를 겁니다.§f"));
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 3.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 3.0f, 1.0f);
                    player.damage(damage);
                }
            }
        }
    }

    @Override
    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
