package xyz.teamhydroxide.sgfriends.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyz.teamhydroxide.sgfriends.FriendData;

public class FriendLibrary {
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
	
	public static List<OfflinePlayer> getPlayerFriendList(Player player) {
		
		List<OfflinePlayer> friends = new ArrayList<OfflinePlayer>();
		YamlConfiguration list = FriendData.load();

		List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
		// get a list<string> from it
		for (String friendUUID : friendlist) {


			OfflinePlayer friend = Bukkit.getServer().getOfflinePlayer(UUID.fromString(friendUUID));
			friends.add(friend);

		}
		
		return friends;
	}
	
	public static List<Player> getPlayerOnlineFriends(Player player) {
		List<Player> friends = new ArrayList<Player>();
		YamlConfiguration list = FriendData.load();

		List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
		// get a list<string> from it
		for (String friendUUID : friendlist) {


			OfflinePlayer friend = Bukkit.getServer().getOfflinePlayer(UUID.fromString(friendUUID));
			if (friend.isOnline()) {
				friends.add((Player)friend);
			}

		}
		
		return friends;
	}
	
}
