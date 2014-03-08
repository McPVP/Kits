package aardvarkpvp.com.kitpvp.Kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aardvarkpvp.com.kitpvp.Utils.Kit;

public class Commands implements CommandExecutor, Kit {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("balance")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.DARK_AQUA + u.getCredits(p) + ChatColor.GRAY + " credits in your account!");
				return true;
			}
			else if(args.length == 1) {
				if(p.hasPermission("balance.others"))  {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null) {
						p.sendMessage(ChatColor.RED + t.getName() + " has " + u.getCredits(t) + " in his account");
					}
					else {
						p.sendMessage(ChatColor.RED  +"That player is not online!");
					}
				}
				else {
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.DARK_AQUA + u.getCredits(p) + ChatColor.GRAY + " credits in your account!");
				}
			}
			
		}
		if(cmd.getName().equalsIgnoreCase("kits")) {
			u.KitList(p);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("help")) {
			p.sendMessage(ChatColor.GRAY + "**** " + ChatColor.DARK_AQUA + "Player Commands" + ChatColor.GRAY + " ****");
			p.sendMessage(ChatColor.GRAY + " /stats - View your own stats");
			p.sendMessage(ChatColor.GRAY + " /balance - View credits in your bank");
			p.sendMessage(ChatColor.GRAY + " /stats [playerName] - View stats of other players");
			p.sendMessage(ChatColor.GRAY + " /kits - List of kits on the server");
			p.sendMessage(ChatColor.GRAY + " /spawn - Teleport you to 'spawn'");
			p.sendMessage(ChatColor.GRAY + " /[kitName] - Use the kit you want");
			p.sendMessage(ChatColor.GRAY + " /warp - Used to warp to & warp list");
			p.sendMessage(ChatColor.GRAY + " /who - List the players online");
		}
		return false;
	}

}
