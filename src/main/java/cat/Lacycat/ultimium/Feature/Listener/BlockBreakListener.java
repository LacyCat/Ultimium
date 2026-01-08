package cat.Lacycat.ultimium.Feature.Listener;

import cat.Lacycat.ultimium.Feature.HardCoreManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.concurrent.ThreadLocalRandom;

public class BlockBreakListener implements Listener {
    private final HardCoreManager hcm;
    public BlockBreakListener(HardCoreManager hcm) {
        this.hcm = hcm;
    }

    @EventHandler
    public void OnBlockBreakEvent(BlockBreakEvent ev) {
        double chance = (1.0 - Math.exp((double) -hcm.get() / 100)) * 100.0;
        if (ThreadLocalRandom.current().nextDouble(100.0) < chance) {
            hcm.add(1);
        }
        if (hcm.get() / 20 >= 1) {

        }
    }
}
