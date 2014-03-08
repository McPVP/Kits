package aardvarkpvp.com.kitpvp;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import aardvarkpvp.com.kitpvp.Files.BalanceYML;
import aardvarkpvp.com.kitpvp.Kits.Commands;
import aardvarkpvp.com.kitpvp.Kits.KitPvP;
import aardvarkpvp.com.kitpvp.listeners.MainHandlers;

public class Main extends JavaPlugin {
	public static Plugin getManager;
	public static Logger log;
	
	

	
	public void onEnable() {
		log = getLogger();
		getManager = this; 
		
		
		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " enabled!");
		
		 BalanceYML.getInstance().setup(this);
		
		onListeners();
		loadKits();
		loadCommands();
		
		
	}
	
	public void loadKits() {
		getCommand("pvp").setExecutor(new KitPvP());
	}
	
	public void loadCommands() {
		getCommand("help").setExecutor(new Commands());
		getCommand("balance").setExecutor(new Commands());
		getCommand("kits").setExecutor(new Commands());
	}
	
	
	public void onListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new MainHandlers(), this);
		
	}
	

	public void onDisable() {
		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " disabled!");
		
	}
	

	

	
}
