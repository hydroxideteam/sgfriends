package xyz.teamhydroxide.sgfriends;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyz.teamhydroxide.utils.YamlData;

import net.md_5.bungee.api.ChatColor;
import xyz.teamhydroxide.sgfriends.lib.FriendLibrary;

// this class recieves and handles '/friend' commands
public class FriendCommand implements CommandExecutor {

	public FriendCommand() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("ftp")) {
				if(cmd.getName().equalsIgnoreCase("teleport")){
					Player teleportTarget = player.getServer().getPlayer(args[0]);

					if (FriendLibrary.checkMutualFriends(player, teleportTarget)) {
						player.teleport(teleportTarget.getLocation()); 
						teleportTarget.sendMessage(ChatColor.GRAY+player.getDisplayName()+" has teleported to you.");
						player.sendMessage(ChatColor.GRAY+"You have been teleported.");
					} else {
						player.sendMessage(ChatColor.RED+"ERROR: you cannot teleport to this player.");
					}
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("friend"))
				if (args.length == 0) {
					sender.sendMessage("Friend system");
					sender.sendMessage(ChatColor.GRAY+"Usage: /friend add <username>");
					sender.sendMessage(ChatColor.GRAY+"Usage: /friend remove <username>");
					sender.sendMessage(ChatColor.GRAY+"Usage: /friend list");
					sender.sendMessage(ChatColor.GRAY+"Usage: /friend chat <message>");
					return true;
				} else {

					if (args[0].equalsIgnoreCase("add") && args.length >= 2) {

						Player friend = Bukkit.getServer().getPlayer(args[1]);

						if (friend == null) {
							player.sendMessage(ChatColor.RED+"ERROR: Player not found!");
						} else {
							YamlConfiguration list = YamlData.load("friends");

							List<String> friendlist = list.getStringList(player.getUniqueId()+".list");


							if (friendlist.contains(friend.getUniqueId().toString())) {
								player.sendMessage(ChatColor.RED+"ERROR: This player is already on your friend list.");
							} else {
								player.sendMessage("You have added "+friend.getDisplayName()+" to your friend list.");
								friend.sendMessage(player.getDisplayName()+ChatColor.YELLOW+" has added you to their friend list.");
								friendlist.add(friend.getUniqueId().toString());

								list.set(player.getUniqueId()+".list", friendlist);
								YamlData.save("friends",list);
							}

						}
					}
					return true;
				}

			if (args[0].equalsIgnoreCase("remove") && args.length >= 2 ) {
				Player friend = Bukkit.getServer().getPlayer(args[1]);
				if (friend == null) {
					player.sendMessage(ChatColor.RED+"ERROR: Player not found!");
				} else {
					YamlConfiguration list = YamlData.load("friends");
					List<String> friendlist = list.getStringList(player.getUniqueId()+".list");
					if (friendlist.contains(friend.getUniqueId())); {
						friendlist.remove(friend);
					}

				}
				return true;
			}

			// give the sender a list of their friends
			if (args[0].equalsIgnoreCase("list")) {
				// load configuration into a YamlConfiguration
				List<OfflinePlayer> list = FriendLibrary.getPlayerFriendList(player);
				for (OfflinePlayer p : list) {
					player.sendMessage(ChatColor.GRAY+p.getName());
				}
				return true;
			}
		}
		return false;
	}
}

