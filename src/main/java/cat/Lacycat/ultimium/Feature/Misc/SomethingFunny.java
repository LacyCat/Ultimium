package cat.Lacycat.ultimium.Feature.Misc;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("CodeBlock2Expr")
public class SomethingFunny {
    private final JavaPlugin plugin;
    public SomethingFunny(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void runBruteForceEffectDelayed(Player player, String text, String color) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            new BukkitRunnable() {
                int index = 0;
                @Override
                public void run() {
                    if (index > text.length()) {
                        cancel();
                        return;
                    }
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    TextColor textColor = TextColor.fromHexString(color);
                    if (textColor == null) textColor = NamedTextColor.WHITE;
                    Component revealed = Component.text(text.substring(0, index)).color(textColor);
                    String remaining = text.substring(index);
                    Component obfuscated = Component.text(remaining)
                            .decorate(TextDecoration.OBFUSCATED).color(textColor);
                    Component message = revealed.append(obfuscated);
                    player.sendActionBar(message);
                    if (index < text.length())
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);
                    index++;
                }
            }.runTaskTimer(plugin, 0L, 2L);
        }, 40L);
    }
}
