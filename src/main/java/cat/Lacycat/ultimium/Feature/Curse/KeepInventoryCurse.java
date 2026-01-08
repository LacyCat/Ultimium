package cat.Lacycat.ultimium.Feature.Curse;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.ThreadLocalRandom;

public class KeepInventoryCurse implements Curse{
    private static boolean isEnabled = false;
    private static int Intensity = 1;
    private static final int scale = 20;
    @Override
    public int getPriority() { return 1; }
    @Override
    public int getIntensity() { return Intensity; }
    @Override
    public void addIntensity(int n) { Intensity += n; }
    @Override
    public boolean getEnabled() { return isEnabled; }
    @Override
    public void setEnabled(boolean n) { isEnabled = n; }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent ev) {
        if (isEnabled) {
            double chance = Math.round((1.0 - Math.exp((double) -Intensity / scale)) * 100.0);
            Player player = ev.getPlayer();
            var inventory = player.getInventory();
            for (ItemStack item : inventory.getContents()) {
                if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                    if (item == null) continue;
                    inventory.setItem(inventory.first(item),new ItemStack(Material.AIR));
                }
            }
        }
    }

    @Override
    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
}
