package server.wenjapvp.hcf.deathban.lives;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.doctordark.util.BukkitUtils;
import com.doctordark.util.command.ArgumentExecutor;
import com.doctordark.util.command.CommandArgument;

import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.deathban.lives.argument.*;

/**
 * Handles the execution and tab completion of the lives command.
 */
public class LivesExecutor extends ArgumentExecutor {

    public LivesExecutor(HCF plugin) {
        super("lives");

        addArgument(new LivesCheckArgument(plugin));
        addArgument(new LivesCheckDeathbanArgument(plugin));
        addArgument(new LivesClearDeathbansArgument(plugin));
        addArgument(new LivesGiveArgument(plugin));
        addArgument(new LivesReviveArgument(plugin));
        addArgument(new LivesSetArgument(plugin));
        addArgument(new LivesSetDeathbanTimeArgument());
        addArgument(new LivesTopArgument(plugin));
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 1) {
        	sender.sendMessage(ChatColor.GOLD + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        	sender.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "LIVES HELP" + ChatColor.GOLD + " - " + ChatColor.GRAY + " (Page 1/1)");
            sender.sendMessage(ChatColor.YELLOW + "/lives check" + ChatColor.GOLD + " - " + ChatColor.GRAY + "Check your lives.");
            sender.sendMessage(ChatColor.YELLOW + "/lives revive <player>" + ChatColor.GOLD + " - " + ChatColor.GRAY + "Revive a player.");
            sender.sendMessage(ChatColor.YELLOW + "/lives give <player>" + ChatColor.GOLD + " - " + ChatColor.GRAY + "Gives a player.");
            sender.sendMessage(ChatColor.YELLOW + "/lives top" + ChatColor.GOLD + " - " + ChatColor.GRAY + "Top players with the most lives.");
            if (sender.hasPermission("hcf.command.lives.staff")) {
            	sender.sendMessage(ChatColor.YELLOW + "/lives set <player> <amount>" + ChatColor.GOLD + " - " + ChatColor.GRAY + "Give a player lives." + ChatColor.GRAY);
            }
            sender.sendMessage(ChatColor.GOLD + BukkitUtils.STRAIGHT_LINE_DEFAULT);

            return true;
        }
        final CommandArgument argument2 = this.getArgument(args[0]);
        final String permission2 = (argument2 == null) ? null : argument2.getPermission();
        if (argument2 == null || (permission2 != null && !sender.hasPermission(permission2))) {
            sender.sendMessage(ChatColor.RED + "Command not found");
            return true;
        }
        argument2.onCommand(sender, command, label, args);
        return true;
    }
}