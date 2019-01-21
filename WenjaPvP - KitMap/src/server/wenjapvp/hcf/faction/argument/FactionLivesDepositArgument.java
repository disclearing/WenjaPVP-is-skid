//package server.wenjapvp.hcf.faction.argument;
//
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//
//import com.doctordark.util.command.CommandArgument;
//
//import server.wenjapvp.hcf.faction.FactionMember;
//import server.wenjapvp.hcf.HCF;
//import server.wenjapvp.hcf.config.ConfigurationService;
//import server.wenjapvp.hcf.faction.type.PlayerFaction;
//
//public class FactionLivesDepositArgument extends CommandArgument
//{
//	private final HCF plugin;
//
//	public FactionLivesDepositArgument(HCF plugin)
//	{
//		super("depositlives", "Deposit lives into your faction");
//		this.plugin = plugin;
//	}
//
//	public static boolean isInteger(String s)
//	{
//		return isInteger(s, 10);
//	}
//
//	public static boolean isInteger(String s, int radix)
//	{
//		if (s.isEmpty()) { return false; }
//		for (int i = 0; i < s.length(); i++)
//		{
//			if ((i == 0) && (s.charAt(i) == '-'))
//			{
//				if (s.length() == 1) { return false; }
//			}
//			else if (Character.digit(s.charAt(i), radix) < 0) { return false; }
//		}
//		return true;
//	}
//
//	public String getUsage(String label)
//	{
//		return '/' + label + ' ' + getName();
//	}
//
//	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
//	{
//		if (!(sender instanceof Player))
//		{
//			sender.sendMessage(ChatColor.RED + "This command is only executable by players.");
//			return true;
//		}
//		if (args.length < 2)
//		{
//			sender.sendMessage(ChatColor.RED + "Invalid usage: /f depositlives <amount>");
//			return true;
//		}
//		if (!isInteger(args[1]))
//		{
//			sender.sendMessage(ChatColor.RED + "Lives must be a number!");
//			return true;
//		}
//		int amount = Integer.parseInt(args[1]);
//		Player player = (Player) sender;
//		PlayerFaction playerFaction = this.plugin.getFactionManager().getPlayerFaction(player);
//		if (playerFaction == null)
//		{
//			sender.sendMessage(ChatColor.RED + "You are not in a faction.");
//			return true;
//		}
//		if ((playerFaction.isRaidable()) && (!this.plugin.getEotwHandler().isEndOfTheWorld()))
//		{
//			sender.sendMessage(ChatColor.RED + "You cannot deposit lives into your faction when raidable.");
//			return true;
//		}
//		FactionMember factionMember = playerFaction.getMember(player);
//
//		playerFaction.setLives(playerFaction.getLives() + amount);
//		sender.sendMessage(ChatColor.GREEN + "You have deposited " + amount + " live(s) into the faction.");
//		playerFaction.broadcast(ConfigurationService.TEAMMATE_COLOUR + sender.getName() + ChatColor.YELLOW + " has deposited " + ChatColor.GRAY + amount + " live(s) " + ChatColor.YELLOW + "into the faction.");
//		return true;
//	}
//}
