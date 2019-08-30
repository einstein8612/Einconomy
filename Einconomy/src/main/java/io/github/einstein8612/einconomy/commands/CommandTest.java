package io.github.einstein8612.einconomy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.einstein8612.einconomy.Einconomy;
import io.github.einstein8612.einconomy.utils.EconomyCommon;

public class CommandTest implements CommandExecutor {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private EconomyCommon eco = new EconomyCommon();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		eco.depositPlayer(args[0], 100);
		return true;
	}

}
