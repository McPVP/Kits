package aardvarkpvp.com.kitpvp.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class PlayerUtils implements Kit {
	public ArrayList<String> hasKit = new ArrayList<String>();
	public HashMap<String, String> lastKit = new HashMap<String, String>();
	
	
	static PlayerUtils instance = new PlayerUtils();
	
	public static PlayerUtils getInstance() {
		return instance;
	}
	
	
	public void messageNerby(Player p, String message) {
		for(Entity e : p.getNearbyEntities(15, 15, 15)) {
			if(e instanceof Player) {
				Player targets = (Player)e;
				targets.sendMessage(message);
			}
		}
	}
	
	public void sortOut(Player player) {
		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		for(PotionEffect potion : player.getActivePotionEffects()) {
			player.removePotionEffect(potion.getType());
		}
		player.setHealth(20.0);
	}
	
	
	 /**
     * Debug something and see if it sends
     *
     * @param player Send the message to that player
     * @param messgae The message to send the player
     */
	public void onDebug(Player player, String message) {
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Ã" + ChatColor.AQUA + message);
	}
		

	 /**
    * Send the message about the kit they used
    *
    * @param player Send the kit name to that player
    * @param kitname The kit name to message
    */
	public void onKitChose(Player player, String kitname) {
		player.sendMessage(ChatColor.GRAY + "You have choosen the kit " + ChatColor.DARK_AQUA + kitname.toUpperCase() + ChatColor.GRAY + "!");
	}
	
	
	public void haveKitMSG(Player p) {
		p.sendMessage(ChatColor.RED + "You already have choosen a kit!");
	}
	
	public boolean hasKit(Player player) {
		if(hasKit.contains(player.getName())) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Gets credit of a player
	 * 
	 * @param p Get the players balance
	 */
	public double getCredits(Player player) {
		
		return b.getConfig().contains(player.getName()) ? b.getConfig().getDouble(player.getName()) : 0.0D;
	}
	
	
	/**
	 * Deposit credits
	 * 
	 * @param p Get the bank account of the player
	 * @param amount The amount to be deposited
	 */
	public void depositAmount(Player player, double amount) {
		double balance = getCredits(player);
		balance += amount;
		b.getConfig().set(player.getName(), balance);
		b.saveConfig();
	}
	
	
	/**
	 * Withdraw credits
	 * 
	 * @param p Gets the bank account of that player
	 * @param amount The amount to be withdrawn
	 */
	
	public void withdrawAmount(Player player, double amount) {
		double balance = getCredits(player);
		balance -= amount;
		b.getConfig().set("Balance", balance);
		b.saveConfig();
	}

	
	public void KitList(Player player) {
		List<String> yourkits = new ArrayList<String>();
		List<String> otherKits = new ArrayList<String>();
		
		yourkits.add(ChatColor.GREEN + "PvP" + ChatColor.GRAY);
		
		player.sendMessage(ChatColor.GOLD + "Avaliable Kits: " + ChatColor.WHITE + yourkits.toString());
		player.sendMessage(ChatColor.GOLD + "Other Kits: " + ChatColor.WHITE + otherKits.toString());
	}
}
