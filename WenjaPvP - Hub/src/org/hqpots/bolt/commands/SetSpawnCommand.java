package org.hqpots.bolt.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hqpots.bolt.Bolt;

public class SetSpawnCommand implements CommandExecutor
{
	Bolt main;

	public SetSpawnCommand(Bolt plugin)
	{
		this.main = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (command.getName().equalsIgnoreCase("setspawn") && !(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "No");
			return true;
		}
		else if (!sender.hasPermission("core.command.setspawn"))
		{
			sender.sendMessage(ChatColor.RED + "No");
			return true;
		}
		else
		{
			if (sender.hasPermission("core.command.setspawn"))
			{
				Player p = (Player) sender;
				Location l = p.getLocation();
				int xc = l.getBlockX();
				int yc = l.getBlockY();
				int zc = l.getBlockZ();
				p.getWorld().setSpawnLocation(xc, yc, zc);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have set the world spawn!"));
			}

			return true;
		}
	}
}