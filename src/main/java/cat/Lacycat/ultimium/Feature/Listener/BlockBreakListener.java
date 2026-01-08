package cat.Lacycat.ultimium.Feature.Listener;

import cat.Lacycat.ultimium.Feature.HardCoreManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void OnBlockBreakEvent(BlockBreakEvent ev) {
        Random r = new Random();
        if (r.nextBoolean()) {
            if (r.nextBoolean()) {
                if (r.nextBoolean()) {
                    HardCoreManager.add(1);
                }
            }
        }
    }
}
