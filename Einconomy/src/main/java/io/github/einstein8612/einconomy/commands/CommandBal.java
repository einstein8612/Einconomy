package io.github.einstein8612.einconomy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.einstein8612.einconomy.Einconomy;
import io.github.einstein8612.einconomy.utils.Common;

public class CommandBal implements CommandExecutor {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private Common Common = new Common();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (Common.checkAccount(player)) {
					Common.tellPlaceholder(player, plugin.getConfig().getString("viewBalance"));
				} else {
					plugin.getDm().getEco().set("Players." + player.getUniqueId().toString(), 0);
					Common.tell(player, Common.resolvePlaceholders(player, null, plugin.getConfig().getString("viewBalance")));
				}
			} else {
				Player target = Common.getPlayer(args[0]);
				if (Common.checkAccount(target)) {
					Common.tellPlaceholder(player, plugin.getConfig().getString("viewBalanceOthers"));
				} else {
					plugin.getDm().getEco().set("Players." + target.getUniqueId().toString(), 0);
					Common.tell(player, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("viewBalanceOthers")));
				}
			}
		} else {
			Common.tell(sender, plugin.getConfig().getString("mustBePlayer"));
		}
		return true;
	}

}
