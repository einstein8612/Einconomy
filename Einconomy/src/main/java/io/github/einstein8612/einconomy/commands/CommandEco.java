package io.github.einstein8612.einconomy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.einstein8612.einconomy.Einconomy;
import io.github.einstein8612.einconomy.utils.Common;
import io.github.einstein8612.einconomy.utils.EconomyCommon;

public class CommandEco implements CommandExecutor {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private EconomyCommon eco = new EconomyCommon();
	private Common common = new Common();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("einconomy.eco")) {
			if (args.length == 0) {
				this.msgUsage(sender);
			} else 
				if (args.length == 1) {
					switch (args[0]) {
					case "add":
						common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco add <player> <amount>");
						break;
					case "set":
						common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco set <player> <amount>");
						break;
					case "reset":
						common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco reset <player>");
						break;
					case "remove":
						common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco remove <player>");
						break;
					}
				} else if (args.length > 1) {
					if (args[0].equalsIgnoreCase("add")) {
						if (sender.hasPermission("einconomy.eco.add")) {
							if (args.length == 3) {
								if (common.getPlayer(args[1]) != null) {
									if (common.isNumeric(args[2])) {
										final double amount = Double.valueOf(args[2]);
										final Player target = common.getPlayer(args[1]);
										final double oldMoney = eco.getBalance(target);

										if (sender instanceof ConsoleCommandSender) {
											eco.depositPlayer(target, amount);
											common.tell(target, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoTarget")
													.replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(oldMoney + amount)));
											common.tell(sender, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoSender")
													.replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(oldMoney + amount)));

										}

										if (sender instanceof Player) {
											Player player = (Player) sender;

											eco.depositPlayer(target, amount);
											common.tell(target, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoTarget")
													.replace("{AMOUNT}", String.valueOf(oldMoney + amount))));
											common.tell(player, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoSender")
													.replace("{AMOUNT}", String.valueOf(oldMoney + amount))));
										}
									} else common.tell(sender, plugin.getConfig().getString("invalidAmount"));
								} else common.tell(sender, plugin.getConfig().getString("invalidPlayer"));
							} else common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco add <player> <amount>");
						} else common.tell(sender, plugin.getConfig().getString("noPerm"));

					} else if (args[0].equalsIgnoreCase("set")) {
						if (sender.hasPermission("einconomy.eco.set")) {
							if (args.length == 3) {
								if (common.getPlayer(args[1]) != null) {
									if (common.isNumeric(args[2])) {
										final double amount = Double.valueOf(args[2]);
										final Player target = common.getPlayer(args[1]);

										if (sender instanceof ConsoleCommandSender) {
											eco.setBalance(target, amount);
											common.tell(target, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoTarget").replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(amount)));
											common.tell(sender, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoSender").replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(amount)));
										}

										if (sender instanceof Player) {
											Player player = (Player) sender;

											eco.setBalance(target, amount);
											common.tell(target, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoTarget").replace("{AMOUNT}", String.valueOf(amount))));
											common.tell(player, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoSender").replace("{AMOUNT}", String.valueOf(amount))));
										}
									} else common.tell(sender, plugin.getConfig().getString("invalidAmount"));
								} else common.tell(sender, plugin.getConfig().getString("invalidPlayer"));
							} else common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco set <player> <amount>");
						} else common.tell(sender, plugin.getConfig().getString("noPerm"));
						
					} else if (args[0].equalsIgnoreCase("remove")) {
						if (sender.hasPermission("einconomy.eco.remove")) {
							if (args.length == 3) {
								if (common.getPlayer(args[1]) != null) {
									if (common.isNumeric(args[2])) {
										final double amount = Double.valueOf(args[2]);
										final Player target = common.getPlayer(args[1]);
										final double oldMoney = eco.getBalance(target);

										if (sender instanceof ConsoleCommandSender) {
											eco.withdrawPlayer(target, amount);
											common.tell(target, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoTarget")
													.replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(oldMoney + amount)));
											common.tell(sender, common.resolvePlaceholders(null, target, plugin.getConfig().getString("ecoSender")
													.replace("{NAME}", "Console")).replace("{AMOUNT}", String.valueOf(oldMoney + amount)));

										}

										if (sender instanceof Player) {
											Player player = (Player) sender;

											eco.withdrawPlayer(target, amount);
											common.tell(target, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoTarget")
													.replace("{AMOUNT}", String.valueOf(oldMoney - amount))));
											common.tell(player, common.resolvePlaceholders(player, target, plugin.getConfig().getString("ecoSender")
													.replace("{AMOUNT}", String.valueOf(oldMoney - amount))));

										}
									} else common.tell(sender, plugin.getConfig().getString("invalidAmount"));
								} else common.tell(sender, plugin.getConfig().getString("invalidPlayer"));
							} else common.tell(sender, plugin.getConfig().getString("syntaxError") + "/eco add <player> <amount>");
						} else common.tell(sender, plugin.getConfig().getString("noPerm"));
					} else {
						this.msgUsage(sender);
					}
				}


		} else common.tell(sender, plugin.getConfig().getString("noPerm"));
		return true;
	}


	private void msgUsage (CommandSender sender) {
		common.tell(sender, plugin.getConfig().getString("ecoHelp"));
	}

}
