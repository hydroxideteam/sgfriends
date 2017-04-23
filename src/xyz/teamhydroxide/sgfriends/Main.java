package xyz.teamhydroxide.sgfriends;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		getLogger().info("Starting ServerGovernerFriends");
		getServer().getPluginManager().registerEvents(new FriendEvents(), this);
		
		
		// Command Executors
		
		FriendCommand friendCommand = new FriendCommand();
		getCommand("friend").setExecutor(friendCommand);
		getCommand("fhome").setExecutor(friendCommand);
		getCommand("ftp").setExecutor(friendCommand);
	}
	
	public void onDisable() {
		getLogger().info("Closing ServerGovernerFriends");
	}
	

}
