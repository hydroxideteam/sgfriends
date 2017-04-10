package xyz.teamhydroxide.sgfriends;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SGFriends extends JavaPlugin {
	public void onEnable() {
		getLogger().info("Starting ServerGovernerFriends");
		getServer().getPluginManager().registerEvents(new FriendEvents(), this);
		
		
		// Command Executors
		getCommand("friend").setExecutor(new FriendCommand());
	}
	
	public void onDisable() {
		getLogger().info("Closing ServerGovernerFriends");
	}
	
	// checks if player has 'friend' on their friendlist
	public static boolean isPlayerFriended(Player player, Player friend) {
		YamlConfiguration list = FriendData.load();
		List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
		
		if (friendlist.contains(friend.getUniqueId().toString())) {
			return true;
		}
		return false;
	}
	
	
	// check if both players are on the friend list
	public static boolean checkMutualFriends(Player player1, Player player2) {
		
		if (isPlayerFriended(player1, player2) && isPlayerFriended(player2, player1)) {
			return true;
		}
		return false;
	}
}
