package donotskipnight;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.event.world.TimeSkipEvent.SkipReason;
import org.bukkit.plugin.java.JavaPlugin;

public class DoNotSkipNight extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onTimeSkip(TimeSkipEvent event) {
		if (event.getSkipReason().equals(SkipReason.NIGHT_SKIP)) {
			event.setCancelled(true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.isSleeping()) {
					player.teleport(player);
					player.setStatistic(Statistic.TIME_SINCE_REST, 0);
				}
			}
		}
	}

}
