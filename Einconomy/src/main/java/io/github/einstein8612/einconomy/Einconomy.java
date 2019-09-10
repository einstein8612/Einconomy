package io.github.einstein8612.einconomy;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.einstein8612.einconomy.commands.CommandBal;
import io.github.einstein8612.einconomy.commands.CommandBaltop;
import io.github.einstein8612.einconomy.commands.CommandEco;
import io.github.einstein8612.einconomy.commands.CommandPay;
import io.github.einstein8612.einconomy.commands.CommandTest;
import io.github.einstein8612.einconomy.utils.DataManager;
import io.github.einstein8612.einconomy.utils.EconomyHook;
import io.github.einstein8612.einconomy.utils.VaultHook;
import io.github.einstein8612.einconomy.utils.objects.PlayerBankObject;
import net.md_5.bungee.api.ChatColor;

public class Einconomy extends JavaPlugin {

	private DataManager dm;
	public EconomyHook ecoHook;
	private VaultHook vaultHook;
	public ArrayList<PlayerBankObject> playerBanks = new ArrayList<PlayerBankObject>();
	
	@Override
	public void onEnable() {
		//Config
		//Custom data
		setup();
		//End custom
		//bukkit
		getConfig().options().copyDefaults(true);
		saveConfig();
		//end bukkit
		//end config

		//Vault setup

		if(getServer().getPluginManager().getPlugin("Vault") != null) {
			vaultSetup();
			vaultHook.hook();
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4=================================="));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVaultAPI Not found! This means the"));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEconomy itself will work, but it "));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&ccannot be accessed by others"));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4=================================="));
		}
		
		//-----------
		
		getCommand("bal").setExecutor(new CommandBal());
		getCommand("pay").setExecutor(new CommandPay());
		getCommand("eco").setExecutor(new CommandEco());
		getCommand("baltop").setExecutor(new CommandBaltop());
		getCommand("test").setExecutor(new CommandTest());

		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
	}
	@Override
	public void onDisable() {
		vaultHook.unhook();
		dm.saveEco();
	}

	private void vaultSetup() {
		ecoHook = new EconomyHook();
		vaultHook = new VaultHook();
	}

	private void setup() {
		dm = new DataManager();
		dm.setup();
	}

	public DataManager getDm() {
		return dm;
	}
}
