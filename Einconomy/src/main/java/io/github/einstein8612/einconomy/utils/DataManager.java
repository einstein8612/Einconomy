package io.github.einstein8612.einconomy.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.einstein8612.einconomy.Einconomy;

public class DataManager {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private Common Common = new Common();

	public FileConfiguration ecocfg;
	public File ecofile;

	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		ecofile = new File(plugin.getDataFolder(), "eco.yml");
		//adding files
		if (!ecofile.exists()) {
			try {
				ecofile.createNewFile();
				ecocfg = YamlConfiguration.loadConfiguration(ecofile);
				ecocfg.createSection("Players");

				ecocfg.save(ecofile);
				Common.log("Created eco file.");
			} catch(IOException excep) {
				Common.log("Couldn't create eco file!");
			}
		}

		ecocfg = YamlConfiguration.loadConfiguration(ecofile);

	}

	public FileConfiguration getEco() {
		return ecocfg;
	}

	public void saveEco() {
		try {
			ecocfg.save(ecofile);
		} catch (IOException excep) {
			Common.log("Couldn't save eco file!");
		}
	}

	public void reloadConfig(CommandSender sender) {
		try {
			ecocfg.load(ecofile);
			//TODO: reload bukkit config?
			Common.log("Reloaded config!");
			Common.tell(sender, "All config files have been reloaded");
		} catch (IOException excep) {
			Common.tell(sender, "Saving failed! Check console for more info.");
			Common.log("Couldn't save files!");
		} catch (InvalidConfigurationException excep) {
			Common.tell(sender, "Saving failed! Check console for more info.");
			Common.log("Invalid configuration syntax");
		}
	}

}