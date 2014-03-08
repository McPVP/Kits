package aardvarkpvp.com.kitpvp.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class KitUtils implements Kit {
	
	public static KitUtils instance = new KitUtils();

	public static KitUtils getInstance() {
		return instance;
	}
	
	
	
	public void GiveKitPvP(Player player) {
		PlayerInventory inv = player.getInventory();
		
		u.sortOut(player);
		u.hasKit.add(player.getName());
		
		ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta SwordMeta = Sword.getItemMeta();
		SwordMeta.setDisplayName(ChatColor.DARK_AQUA + "PvP");
		Sword.setItemMeta(SwordMeta);
		
		Sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		
		ItemStack Helmet = new ItemStack(Material.IRON_HELMET);
		
		ItemStack ChestPlate = new ItemStack(Material.IRON_CHESTPLATE);
		
		ItemStack Legging = new ItemStack(Material.IRON_LEGGINGS);
		
		ItemStack Boots = new ItemStack(Material.IRON_BOOTS);
		
		inv.setItem(0, Sword);
		
		inv.setArmorContents(new ItemStack[] {
				Boots, Legging, ChestPlate, Helmet
		});
		
		u.onKitChose(player, "pvp");
		
		for(ItemStack missing : inv.getContents()) {
			if(missing == null) {
				ItemStack Soup = new ItemStack(Material.MUSHROOM_SOUP);
				ItemMeta SoupMeta = Soup.getItemMeta();
				SoupMeta.setDisplayName(ChatColor.GOLD + "Soup");
				List<String> SoupLore = new ArrayList<String>();
				SoupLore.add(ChatColor.GREEN + "Use me to heal 3.5 hearts!");
				SoupMeta.setLore(SoupLore);
				Soup.setItemMeta(SoupMeta);
				
				inv.addItem(Soup);
			}
		}

	}

}
