package aardvarkpvp.com.kitpvp.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class StatsYML {
	 public static HashMap<String, FileConfiguration> files;
	
	public static void saveConfig(Player player){
        FileConfiguration config = getConfig(player);
        File file = new File("KitPvP/UserData/" + player.getName() + ".yml");
 
        try {
            config.save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
 
 
    public static FileConfiguration getConfig(Player player){
        FileConfiguration config = null;
        if(files.get(player.getName()) !=null){
            config = YamlConfiguration.loadConfiguration(new File("KitPvP/UserData/" + files.get(player.getName()) + ".yml"));
        }else {
            System.out.print("error");
        }
 
        return config;
    }
 
    public static void setPlayerConfigDefaults(Player player){
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("KitPvP/UserData", player.getName() + ".yml"));
        if(!(config.contains("Name"))){
 
            config.set("Name", player.getName());
            config.set("Balance", Integer.valueOf(150));
 
            try{
                config.save(new File("KitPvP/UserData/" + player.getName() + ".yml"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
 
	

}
