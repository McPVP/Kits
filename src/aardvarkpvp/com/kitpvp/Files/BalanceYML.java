package aardvarkpvp.com.kitpvp.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class BalanceYML {
	
	
static BalanceYML instance = new BalanceYML();
	
	public static BalanceYML getInstance() {
		return instance;
	}
	
	Plugin p;
	
	FileConfiguration balances;
	File balancefile;
	
	public void setup(Plugin p) {
		this.p = p;
		
		if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
		
		balancefile = new File(p.getDataFolder(), "balances.yml");
		balances = YamlConfiguration.loadConfiguration(balancefile);
		
		if (!balancefile.exists()) {
			try {
				balancefile.createNewFile();
			}
			catch (IOException e) {
				System.out.println("Could not create balance file!");
			}
		}
	}
	
	public FileConfiguration getConfig() {
		return balances;
	}
	
	public void saveConfig() {
		try {
			balances.save(balancefile);
		}
		catch (IOException e) {
			System.out.println("Could not save balance file!");
		}
	}
	
	public PluginDescriptionFile getDescription() {
		return p.getDescription();
	}
}




