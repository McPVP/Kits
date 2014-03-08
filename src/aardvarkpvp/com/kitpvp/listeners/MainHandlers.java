package aardvarkpvp.com.kitpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import aardvarkpvp.com.kitpvp.Files.StatsYML;
import aardvarkpvp.com.kitpvp.Utils.Kit;




public class MainHandlers implements Kit, Listener {
	
	
	@EventHandler
	public void onHeal(EntityRegainHealthEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getRegainReason() == RegainReason.SATIATED) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onKitremove(PlayerDeathEvent e) {
		Player player  = e.getEntity();
		if(u.hasKit(player)) {
			u.hasKit.remove(player.getName());
			u.lastKit.remove(player.getName());
		}
	}
	
	/*
	 * QuitEvent
	 */
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.RED+ e.getPlayer().getName() + ChatColor.GRAY + " has left");
	}
	
	/*
	 * JoinEvent
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(!player.hasPlayedBefore()) {
			e.setJoinMessage(ChatColor.RED + "Welcome " + player.getName() + " has joined the server!");
			StatsYML.setPlayerConfigDefaults(player);
			return;
		}
		e.setJoinMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " has joined");
	}
	
	/*
	 * Strength debuff
	 */
	 @EventHandler
	    public void onPlayerDamage(EntityDamageByEntityEvent event) {
	      if ((event.getDamager() instanceof Player))
	      {
	        Player player = (Player)event.getDamager();
	        if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
	        {
	          for (PotionEffect Effect : player.getActivePotionEffects())
	          {
	            if (Effect.getType().equals(PotionEffectType.INCREASE_DAMAGE))
	            {
	              double DamagePercentage = (Effect.getAmplifier() + 1) * 1.3D + 1.0D;
	              int NewDamage;
	              if (event.getDamage() / DamagePercentage <= 1.0D)
	              {
	                NewDamage = (Effect.getAmplifier() + 1) * 3 + 1;
	              }
	              else
	              {
	                NewDamage = (int)(event.getDamage() / DamagePercentage) + (Effect.getAmplifier() + 1) * 3;
	              }
	              event.setDamage(NewDamage);
	              break;
	            }
	          }
	        }
	      }
	    }
	
	/*
	 * Food debuff
	 */
	@EventHandler
	  public void onHungerLoss(FoodLevelChangeEvent e) {
	      e.setCancelled(true);
	  }
	
	
	/*
	 * Death =(
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player killer = e.getEntity().getKiller();
			Player player = e.getEntity();
			
			
			double i = killer.getHealth();
			killer.sendMessage(ChatColor.GRAY + "You beat down " + ChatColor.DARK_AQUA + player.getName() + ChatColor.GRAY + "!");
			player.sendMessage(ChatColor.GRAY + "You were killed by " + ChatColor.DARK_AQUA + killer.getName() + ChatColor.GRAY + " with " + ChatColor.YELLOW + i + ChatColor.GRAY + "/" + ChatColor.YELLOW + "20" + ChatColor.GRAY + " health");
			
			
			killer.sendMessage(ChatColor.GRAY + "You were awarded " + ChatColor.DARK_AQUA + "15" + ChatColor.GRAY  + " credits for killing " + ChatColor.DARK_AQUA + player.getName());
			u.depositAmount(killer, Double.valueOf(15));
			
			
			
		}
	}
	
	/*
	 * Soup
	 */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (!player.getGameMode().equals(GameMode.CREATIVE)) && (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP))) {

			if (player.getHealth() < 20) {
				if ((player.getHealth() + 7 == 20)) {
					player.getItemInHand().setType(Material.BOWL);
					player.setHealth(20);

				}
				if ((player.getHealth() + 7 > 20)) {
					player.getItemInHand().setType(Material.BOWL);
					player.setHealth(20);

				}
				if (player.getHealth() < 20 - 7) {
					player.setHealth(player.getHealth() + 7);
					player.getItemInHand().setType(Material.BOWL);
				}
			} else {
				player.getItemInHand().setType(Material.MUSHROOM_SOUP);

			}
		}
	}

}
