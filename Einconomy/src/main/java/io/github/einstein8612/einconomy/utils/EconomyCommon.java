package io.github.einstein8612.einconomy.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.einstein8612.einconomy.Einconomy;

public class EconomyCommon {
	private DataManager dm = Einconomy.getPlugin(Einconomy.class).getDm();
	private Common Common = new Common();
	
	public double getBalance(OfflinePlayer player) {
		return dm.getEco().getDouble("Players." + player.getUniqueId().toString());
	}

	public double getBalance(String playername) {
		if (Common.getPlayer(playername) == null) {
			return 0.00;
		}
		Player player = Common.getPlayer(playername);
		return (this.getBalance(player));
	}

	public boolean depositPlayer(OfflinePlayer player, double amount) {
		final double oldMoney = getBalance(player);
		dm.getEco().set("Players." + player.getUniqueId().toString(), oldMoney + amount);
		return true;
	}

	public boolean depositPlayer(String playername, double amount) {
		if (Common.getPlayer(playername) == null) {
			return false;
		}
		final Player player = Common.getPlayer(playername);
		
		return (this.depositPlayer(player, amount));
	}

	public boolean hasAccount(OfflinePlayer player) {
		if (dm.getEco().get("Players." + player.getUniqueId().toString()) == null) {
			return false;
		}
		return true;
	}

	public boolean hasAccount(String playername) {
		if (Common.getPlayer(playername) == null) {
			return false;
		}
		Player player = Common.getPlayer(playername);
		return (this.hasAccount(player));
	}

	public boolean has(OfflinePlayer player, double amount) {
		return (getBalance(player) > amount);
	}

	public boolean has(String playername, double amount) {
		if (Common.getPlayer(playername) == null) {
			return false;
		}
		Player player = Common.getPlayer(playername);
		return (getBalance(player) > amount);
	}

	public boolean withdrawPlayer(String playerName, double amount) {
		if (Common.getPlayer(playerName) == null) {
			return false;
		}
		final Player player = Common.getPlayer(playerName);
		withdrawPlayer(player, amount);
		return true;
	}

	public boolean withdrawPlayer(OfflinePlayer player, double amount) {
		if (this.getBalance(player) < amount) {
			return false;
		}
		final double oldMoney = this.getBalance(player);
		dm.getEco().set("Players." + player.getUniqueId().toString(), oldMoney - amount);
		return true;
	}

}