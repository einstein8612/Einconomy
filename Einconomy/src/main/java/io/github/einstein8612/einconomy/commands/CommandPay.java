package io.github.einstein8612.einconomy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.einstein8612.einconomy.Einconomy;
import io.github.einstein8612.einconomy.utils.Common;
import io.github.einstein8612.einconomy.utils.EconomyCommon;

public class CommandPay implements CommandExecutor {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private EconomyCommon eco = new EconomyCommon();
	private Common Common = new Common();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (args.length != 2) {
				Common.tell(player, Common.resolvePlaceholders(player, null, plugin.getConfig().getString("syntaxError") + "/pay <player> <amount>"));
			} else {
				if (Common.getPlayer(args[0]) != null) {
					final Player target = Common.getPlayer(args[0]);
					if (Common.checkAccount(target)) {
						if (Common.isNumeric(args[1])) {
							if (Double.valueOf(args[1]) > 0) {
								final double amount = Double.valueOf(args[1]);

								if (plugin.getDm().getEco().getDouble("Players." + player.getUniqueId().toString()) > amount) {
									eco.depositPlayer(target, amount);
									eco.withdrawPlayer(player, amount);
									Common.tell(player, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("havePaid").replace("{AMOUNT}", String.valueOf(amount))));
									Common.tell(target, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("haveBeenPaid").replace("{AMOUNT}", String.valueOf(amount))));
								} else {
									player.sendMessage("fr?");
									Common.tell(player, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("noMoney")));
								}
							} else {
								player.sendMessage("Dont use -1");
							}
						} else {
							Common.tell(player, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("syntaxError") + "/pay <player> <amount>"));
						}
					} else {
						plugin.getDm().getEco().set("Players." + target.getUniqueId().toString(), 0);
						Common.tell(player, Common.resolvePlaceholders(player, target, plugin.getConfig().getString("viewBalanceOthers")));
					}
				} else {
					Common.tell(player, Common.resolvePlaceholders(player, null, plugin.getConfig().getString("syntaxError") + "/pay <player> <amount>"));
				}
			}
		} else {
			Common.tell(sender, plugin.getConfig().getString("mustBePlayer"));
		}
		return true;
	}

}
