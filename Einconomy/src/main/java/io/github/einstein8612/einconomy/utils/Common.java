package io.github.einstein8612.einconomy.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.einstein8612.einconomy.Einconomy;

public class Common {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);

	public Player getPlayer(String playername) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.getName().equalsIgnoreCase(playername)) {
				return all;
			}
		}
		return null;
	}

	public Player getPlayer(UUID uuid) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.getUniqueId().equals(uuid)) {
				return all;
			}
		}
		return null;
	}

	public String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public void tell(Player player, String message) {
		player.sendMessage(colorize(message));
	}

	public void tell(CommandSender player, String message) {
		player.sendMessage(colorize(message));
	}

	public void tellPlaceholder(Player player, String message) {
		player.sendMessage(colorize(resolvePlaceholders(player, null, message)));
	}
	
	public void log(String message) {
		Bukkit.getConsoleSender().sendMessage("[" + Einconomy.getPlugin(Einconomy.class).getName() + "] " + message);;
	}

	public boolean checkAccount(Player player) {
		if (plugin.getDm().getEco().contains("Players." + player.getUniqueId())) {
			return true;
		} else return false;
	}
	
	public boolean isNumeric(String strNum) {
	    try {
	        @SuppressWarnings("unused")
			double number = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}

	public String resolvePlaceholders(Player player, Player target, String message) {
		EconomyCommon eco = new EconomyCommon();
		if (player != null) {
		message = message.replace("{DISPLAYNAME}", player.getDisplayName());
		message = message.replace("{NAME}", player.getName());
		message = message.replace("{BALANCE}", String.valueOf(eco.getBalance(player)));
		}
		if (target != null) {
			message = message.replace("{TARGETNAME}", target.getName());
			message = message.replace("{TARGETDISPLAYNAME}", target.getName());
			message = message.replace("{TARGETBALANCE}", String.valueOf(eco.getBalance(target)));
		}
		final String finMessage = message;
		return finMessage;
		
	}

}