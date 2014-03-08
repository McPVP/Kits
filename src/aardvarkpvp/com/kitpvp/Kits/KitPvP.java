package aardvarkpvp.com.kitpvp.Kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aardvarkpvp.com.kitpvp.Utils.Kit;

public class KitPvP implements Kit, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("pvp")) {
			if(u.hasKit(player)) {
				u.haveKitMSG(player);
				return true;
			}
			k.GiveKitPvP(player);
		}
		return false;
	}


}
