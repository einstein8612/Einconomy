package io.github.einstein8612.einconomy.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import io.github.einstein8612.einconomy.Einconomy;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class VaultHook {
	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);

	private Economy provider;

	public void hook() {
		provider = plugin.ecoHook;
		Bukkit.getServicesManager().register(Economy.class, this.provider, this.plugin, ServicePriority.Normal);
		Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "]" + ChatColor.GREEN + " Hooked into Vault");
	}

	public void unhook() {
		Bukkit.getServicesManager().unregister(Economy.class, this.provider);
		Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "]" + ChatColor.YELLOW + " Unhooked from Vault");

	}
}