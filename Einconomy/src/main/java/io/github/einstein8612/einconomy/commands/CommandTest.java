package io.github.einstein8612.einconomy.commands;

import java.text.DecimalFormat;

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
		//plugin.getDm().getEco().set("Players.fe8994fa-1391-4145-a314-3038a11c9d3f", currencyFormat.format(100000000));
		
		System.out.println(plugin.getDm().getEco().getDouble("Players.fe8994fa-1391-4145-a314-3038a11c9d3f"));
		return true;
	}
}