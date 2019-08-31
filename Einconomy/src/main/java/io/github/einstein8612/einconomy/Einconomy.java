package io.github.einstein8612.einconomy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.einstein8612.einconomy.commands.CommandBal;
import io.github.einstein8612.einconomy.commands.CommandEco;
import io.github.einstein8612.einconomy.commands.CommandPay;
import io.github.einstein8612.einconomy.commands.CommandTest;
import io.github.einstein8612.einconomy.utils.DataManager;
import io.github.einstein8612.einconomy.utils.EconomyHook;
import io.github.einstein8612.einconomy.utils.VaultHook;

public class Einconomy extends JavaPlugin {

	private DataManager dm;
	public EconomyHook ecoHook;
    private VaultHook vaultHook;
	
	
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
		vaultSetup();
		vaultHook.hook();
		//-----------
		getCommand("bal").setExecutor(new CommandBal());
		getCommand("pay").setExecutor(new CommandPay());
		getCommand("eco").setExecutor(new CommandEco());
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
