package io.github.einstein8612.einconomy.utils;

import java.util.List;

import org.bukkit.OfflinePlayer;

import io.github.einstein8612.einconomy.Einconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyHook implements Economy {

	private Einconomy plugin = Einconomy.getPlugin(Einconomy.class);
	private EconomyCommon eco = new EconomyCommon();

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public String getName() {

		return plugin.getName();
	}

	@Override
	public boolean hasBankSupport() {

		return false;
	}

	@Override
	public int fractionalDigits() {

		return 2;
	}

	@Override
	public String format(double amount) {

		return (plugin.getConfig().getString("economy.symbol") + String.valueOf(amount));
	}

	@Override
	public String currencyNamePlural() {
		// TODO Auto-generated method stub
		return "dollars";
	}

	@Override
	public String currencyNameSingular() {
		// TODO Auto-generated method stub
		return "dollar";
	}

	@Override
	public boolean hasAccount(String playerName) {

		return (eco.hasAccount(playerName));
	}

	@Override
	public boolean hasAccount(OfflinePlayer player) {

		return (eco.hasAccount(player));
	}

	@Override
	public boolean hasAccount(String playerName, String worldName) {

		return (eco.hasAccount(playerName));
	}

	@Override
	public boolean hasAccount(OfflinePlayer player, String worldName) {

		return (eco.hasAccount(player));
	}

	@Override
	public double getBalance(String playerName) {

		return (eco.getBalance(playerName));
	}

	@Override
	public double getBalance(OfflinePlayer player) {

		return (eco.getBalance(player));
	}

	@Override
	public double getBalance(String playerName, String world) {

		return (eco.getBalance(playerName));
	}

	@Override
	public double getBalance(OfflinePlayer player, String world) {

		return (eco.getBalance(player));
	}

	@Override
	public boolean has(String playerName, double amount) {

		return (eco.has(playerName, amount));
	}

	@Override
	public boolean has(OfflinePlayer player, double amount) {

		return (eco.has(player, amount));
	}

	@Override
	public boolean has(String playerName, String worldName, double amount) {

		return (eco.has(playerName, amount));
	}

	@Override
	public boolean has(OfflinePlayer player, String worldName, double amount) {

		return (eco.has(player, amount));
	}

	@Override
	public EconomyResponse withdrawPlayer(String playerName, double amount) {

		return new EconomyResponse(amount, eco.getBalance(playerName) - amount, eco.withdrawPlayer(playerName, amount) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {

		return new EconomyResponse(amount, eco.getBalance(player) - amount, eco.withdrawPlayer(player, amount) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {

		return new EconomyResponse(amount, eco.getBalance(playerName) - amount, eco.withdrawPlayer(playerName, amount) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
		
		return new EconomyResponse(amount, eco.getBalance(player) - amount, eco.withdrawPlayer(player, amount) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, double amount) {
		eco.depositPlayer(playerName, amount);
		return new EconomyResponse(amount, eco.getBalance(playerName), ResponseType.SUCCESS, "");
		
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		eco.depositPlayer(player, amount);
		return new EconomyResponse(amount, eco.getBalance(player), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
		eco.depositPlayer(playerName, amount);
		return new EconomyResponse(amount, eco.getBalance(playerName), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
		eco.depositPlayer(player, amount);
		return new EconomyResponse(amount, eco.getBalance(player), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse createBank(String name, String player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse createBank(String name, OfflinePlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankBalance(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankHas(String name, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String name, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String name, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String name, String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String name, String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String name, OfflinePlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBanks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createPlayerAccount(String playerName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(String playerName, String worldName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
		// TODO Auto-generated method stub
		return false;
	}
}