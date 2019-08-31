package io.github.einstein8612.einconomy.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.einstein8612.einconomy.Einconomy;
import io.github.einstein8612.einconomy.utils.Common;
import io.github.einstein8612.einconomy.utils.DataManager;
import io.github.einstein8612.einconomy.utils.objects.PlayerBankObject;

public class CommandBaltop implements CommandExecutor {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private DataManager dm = plugin.getDm();
	private Common common = new Common();
	private ArrayList<PlayerBankObject> playerBanks = plugin.playerBanks;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender.hasPermission("einconomy.baltop")) {
			if (args.length == 1 && args[0].equalsIgnoreCase("force")) {
				playerBanks.clear();
				for (String key : dm.getEco().getConfigurationSection("Players").getKeys(true)) {
					double value = dm.getEco().getConfigurationSection("Players").getDouble(key);
					playerBanks.add(new PlayerBankObject(key, value));
				}
				this.sendSortedBanks(sender, playerBanks);
			} else if (this.playerBanks.isEmpty()) {
				for (String key : dm.getEco().getConfigurationSection("Players").getKeys(true)) {
					double value = dm.getEco().getConfigurationSection("Players").getDouble(key);
					playerBanks.add(new PlayerBankObject(key, value));
				}
				this.sendSortedBanks(sender, playerBanks);
			} else this.sendSortedBanks(sender, playerBanks);






		} else {

			//TODO: No perm
		}
		return true;


	}

	private void sendSortedBanks(CommandSender sender, ArrayList<PlayerBankObject> playerBanks) {
		Collections.sort(playerBanks);
		int i = 1;
		common.tell(sender, plugin.getConfig().getString("baltopHeading"));
		for (PlayerBankObject object : playerBanks) {
			common.tell(sender, plugin.getConfig().getString("baltopLine")
					.replace("{NAME}", object.getName())
					.replace("{NUMBER}", String.valueOf(i))
					.replace("{BALANCE}", String.valueOf(object.getMoney()))
					);
			i++;
			if (i > plugin.getConfig().getInt("Economy.baltopShown")+1) {
				return;
			}
		}
	}
}