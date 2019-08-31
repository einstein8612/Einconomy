package io.github.einstein8612.einconomy.utils.objects;

import java.util.UUID;

import org.bukkit.Bukkit;

import io.github.einstein8612.einconomy.utils.Common;

public class PlayerBankObject implements Comparable<PlayerBankObject> {
	
	private Common Common = new Common();
	public String uuid;
    public double money;
    
    public PlayerBankObject(String uuid, double money)
    {
        this.uuid = uuid;
        this.money = money;
    }

    public String getDisplayName() {
        return Common.getPlayer(UUID.fromString(uuid)).getDisplayName();
    }
    
    public String getName() {
    	String name = Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
    	if (name != null) {
    		return name;
    	} else {
    		return "Unavailable";
    	}
        
    }

    public String getUuid() {
    	return this.uuid;
    }
    
    public double getMoney() {
    	return this.money;
    }

	@Override
	public int compareTo(PlayerBankObject object) {
		
		return new Double(object.money).compareTo(this.money);
	}
}