package io.github.einstein8612.einconomy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
	
	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!plugin.getDm().getEco().contains("Players." + event.getPlayer().getUniqueId().toString())) {
			plugin.getDm().getEco().set("Players." + event.getPlayer().getUniqueId().toString(),
					plugin.getConfig().get("Players." + event.getPlayer().getUniqueId().toString()));
			return;
		}
		return;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		plugin.getDm().saveEco();
	}
	
}
