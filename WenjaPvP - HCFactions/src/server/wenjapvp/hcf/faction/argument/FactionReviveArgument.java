package server.wenjapvp.hcf.faction.argument;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.doctordark.internal.com.doctordark.base.BaseConstants;
import com.doctordark.util.command.CommandArgument;

import server.wenjapvp.hcf.deathban.Deathban;
import server.wenjapvp.hcf.faction.FactionMember;
import server.wenjapvp.hcf.faction.struct.Relation;
import server.wenjapvp.hcf.faction.struct.Role;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.config.ConfigurationService;
import server.wenjapvp.hcf.faction.type.PlayerFaction;
import server.wenjapvp.hcf.user.FactionUser;

public class FactionReviveArgument
  extends CommandArgument
{
  private final HCF plugin;
  
  public FactionReviveArgument(HCF plugin)
  {
    super("revive", "Revive a player using faction lives.");
    this.plugin = plugin;
  }
  
  public String getUsage(String label)
  {
    return '/' + label + ' ' + getName();
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
      return true;
    }
    if (args.length < 2)
    {
      sender.sendMessage(ChatColor.RED + "Invalid usage: /f revive <player>");
      return true;
    }
    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
    if ((!target.hasPlayedBefore()) && (!target.isOnline()))
    {
      sender.sendMessage(BaseConstants.PLAYER_WITH_NAME_OR_UUID_NOT_FOUND);
      return true;
    }
    Player player = (Player)sender;
    PlayerFaction playerFaction = this.plugin.getFactionManager().getPlayerFaction(player);
    if (playerFaction == null)
    {
      sender.sendMessage(ChatColor.RED + "You are not in a faction.");
      return true;
    }
    FactionMember factionMember = playerFaction.getMember(player);
    if (factionMember.getRole() == Role.MEMBER)
    {
      sender.sendMessage(ChatColor.RED + "You must be a faction officer to revive players.");
      return true;
    }
    if (playerFaction.getMember(target.getName()) == null)
    {
      sender.sendMessage(ChatColor.RED + "That player is not in your faction!");
      return true;
    }
    if (playerFaction.getLives() <= 0)
    {
      sender.sendMessage(ChatColor.RED + "Your faction doesn't have enough lives to revive that member!");
      return true;
    }
    if (playerFaction.getLives() - 1 < 0)
    {
      sender.sendMessage(ChatColor.RED + "Your faction doesn't have enough lives to revive that member!");
      return true;
    }
    UUID targetUUID = target.getUniqueId();
    FactionUser factionTarget = this.plugin.getUserManager().getUser(targetUUID);
    Deathban deathban = factionTarget.getDeathban();
    if ((deathban == null) || (!deathban.isActive()))
    {
      sender.sendMessage(ChatColor.RED + target.getName() + " is not death-banned.");
      return true;
    }
    Relation relation = Relation.ENEMY;
    if ((sender instanceof Player))
    {
      UUID playerUUID = player.getUniqueId();
      PlayerFaction playerFaction1 = this.plugin.getFactionManager().getPlayerFaction(player);
      playerFaction.setLives(playerFaction.getLives() - 1);
      playerFaction.broadcast(ConfigurationService.TEAMMATE_COLOUR + factionMember.getRole().getAstrix() + sender.getName() + ChatColor.RED + " has revived " + target.getName() + " using 1 faction life.");
    }
    factionTarget.removeDeathban();
    return true;
  }
}
