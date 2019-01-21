package server.wenjapvp.hcf.scoreboard.provider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import com.doctordark.util.BukkitUtils;
import com.doctordark.util.DurationFormatter;

import gg.vexus.handler.VanishHandler;
import gg.vexus.utils.C;
import server.wenjapvp.hcf.DateTimeFormats;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.eventgame.EventTimer;
import server.wenjapvp.hcf.eventgame.eotw.EotwHandler;
import server.wenjapvp.hcf.eventgame.faction.ConquestFaction;
import server.wenjapvp.hcf.eventgame.faction.EventFaction;
import server.wenjapvp.hcf.eventgame.faction.KothFaction;
import server.wenjapvp.hcf.eventgame.tracker.ConquestTracker;
import server.wenjapvp.hcf.faction.type.PlayerFaction;
import server.wenjapvp.hcf.pvpclass.PvpClass;
import server.wenjapvp.hcf.pvpclass.archer.ArcherClass;
import server.wenjapvp.hcf.pvpclass.archer.ArcherMark;
import server.wenjapvp.hcf.pvpclass.bard.BardClass;
import server.wenjapvp.hcf.pvpclass.type.MinerClass;
import server.wenjapvp.hcf.scoreboard.SidebarEntry;
import server.wenjapvp.hcf.scoreboard.SidebarProvider;
import server.wenjapvp.hcf.sotw.SotwTimer;
import server.wenjapvp.hcf.timer.PlayerTimer;
import server.wenjapvp.hcf.timer.Timer;

public class TimerSidebarProvider implements SidebarProvider
{

	private Map<String, Long> playerChatTimes;
	
	public static final ThreadLocal<DecimalFormat> CONQUEST_FORMATTER = new ThreadLocal<DecimalFormat>()
	{
		@Override
		protected DecimalFormat initialValue()
		{
			return new DecimalFormat("00.0");
		}
	};
	protected static final String STRAIGHT_LINE = BukkitUtils.STRAIGHT_LINE_DEFAULT.substring(0, 14);
	private static final SidebarEntry EMPTY_ENTRY_FILLER = new SidebarEntry(" ", " ", " ");
	private static final Comparator<Map.Entry<UUID, ArcherMark>> ARCHER_MARK_COMPARATOR = (o1, o2) -> o1.getValue().compareTo(o2.getValue());

	private final HCF plugin;

	public TimerSidebarProvider(HCF plugin)
	{
		this.plugin = plugin;
		this.playerChatTimes = new HashMap();
	}

	public SidebarEntry add(String s)
	{
		if (s.length() < 10) { return new SidebarEntry(s); }

		if (s.length() > 10 && s.length() < 20) { return new SidebarEntry(s.substring(0, 10), s.substring(10, s.length()), ""); }

		if (s.length() > 20) { return new SidebarEntry(s.substring(0, 10), s.substring(10, 20), s.substring(20, s.length())); }

		return null;
	}

	@Override
	public String getTitle()
	{
		return ChatColor.GOLD + ChatColor.BOLD.toString() + "WenjaPvP" + ChatColor.GRAY + " [Beta]";
	}

	@Override
	public List<SidebarEntry> getLines(Player player)
	{
		PlayerFaction playerFaction = HCF.getPlugin().getFactionManager().getPlayerFaction(player);
		List<SidebarEntry> lines = new ArrayList<>();
		
		if(player.hasPermission("common.vanish")) {
			lines.add(new SidebarEntry(C.c("&6&lStaff Mode:")));
			lines.add(new SidebarEntry(ChatColor.GRAY + " » " +"Va", "nish: " + (VanishHandler.isVanished(player) ? C.c("&a") + "On" : C.c("&c") + "Off"), null));
			lines.add(new SidebarEntry(ChatColor.GRAY +  " » " + "Game","mode: " + ChatColor.GOLD + StringUtils.capitalise(player.getGameMode().name().toLowerCase()), null));
		}

		EotwHandler.EotwRunnable eotwRunnable = plugin.getEotwHandler().getRunnable();

		lines.add(new SidebarEntry(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Kills: " + ChatColor.GOLD + player.getStatistic(Statistic.PLAYER_KILLS)));
		lines.add(new SidebarEntry(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Deaths: " + ChatColor.GOLD  + player.getStatistic(Statistic.DEATHS)));

		if (eotwRunnable != null)
		{
			long remaining = eotwRunnable.getMillisUntilStarting();
			if (remaining > 0L)
			{
				lines.add(new SidebarEntry(ChatColor.RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.RED + " starts", " in " + ChatColor.BOLD + DurationFormatter.getRemaining(remaining, true)));
			}
			else if ((remaining = eotwRunnable.getMillisUntilCappable()) > 0L)
			{
				lines.add(new SidebarEntry(ChatColor.RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.RED + " cappable", " in " + ChatColor.BOLD + DurationFormatter.getRemaining(remaining, true)));
			}
		}

		SotwTimer.SotwRunnable sotwRunnable = plugin.getSotwTimer().getSotwRunnable();
		if (sotwRunnable != null)
		{
			lines.add(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.BOLD, "SOTW " + ChatColor.GREEN + "ends in ", ChatColor.BOLD + DurationFormatter.getRemaining(sotwRunnable.getRemaining(), true)));
		}
		EventTimer eventTimer = plugin.getTimerManager().getEventTimer();
		List<SidebarEntry> conquestLines = null;

		EventFaction eventFaction = eventTimer.getEventFaction();
		if (eventFaction instanceof KothFaction)
		{
			lines.add(new SidebarEntry(eventTimer.getScoreboardPrefix(), eventFaction.getScoreboardName() + ChatColor.GRAY, ": " + ChatColor.GOLD + DurationFormatter.getRemaining(eventTimer.getRemaining(), true)));
		}
		else if (eventFaction instanceof ConquestFaction) {
			final ConquestFaction conquestFaction = (ConquestFaction)eventFaction;
			final DecimalFormat format = TimerSidebarProvider.CONQUEST_FORMATTER.get();
			conquestLines = new ArrayList<SidebarEntry>();
			conquestLines.add(new SidebarEntry(ChatColor.GOLD.toString() + ChatColor.BOLD + "Conquest", ChatColor.GOLD.toString() + ChatColor.BOLD + " Event", ChatColor.GRAY + ":"));
			conquestLines.add(new SidebarEntry("  " + ChatColor.RED.toString() + conquestFaction.getRed().getScoreboardRemaining(), ChatColor.RESET + " : ", String.valueOf(ChatColor.YELLOW.toString()) + conquestFaction.getYellow().getScoreboardRemaining()));
			conquestLines.add(new SidebarEntry("  " + ChatColor.GREEN.toString() + conquestFaction.getGreen().getScoreboardRemaining(), ChatColor.RESET + " : " + ChatColor.RESET, String.valueOf(ChatColor.AQUA.toString()) + conquestFaction.getBlue().getScoreboardRemaining()));
			final ConquestTracker conquestTracker = (ConquestTracker)conquestFaction.getEventType().getEventTracker();
			int count = 0;
			for (final Map.Entry<PlayerFaction, Integer> entry : conquestTracker.getFactionPointsMap().entrySet()) {
				String factionName = entry.getKey().getName();
				if (factionName.length() > 11) {
					factionName = factionName.substring(0, 11);
				}
				//conquestLines.add(new SidebarEntry (ChatColor.GOLD.toString() + ChatColor.BOLD + "Points"));
				conquestLines.add(new SidebarEntry(ChatColor.GOLD, " »" + ChatColor.RED + factionName, ChatColor.GRAY + ": " + ChatColor.WHITE + entry.getValue()));
				if (++count == 3) {
					break;
				}
			}
		}

		// Show the current PVP Class statistics of the player.
		PvpClass pvpClass = plugin.getPvpClassManager().getEquippedClass(player);
		if (pvpClass != null)
		{
			lines.add(new SidebarEntry(ChatColor.GREEN.toString() + ChatColor.BOLD, "Active Class", ChatColor.GRAY + ": " + ChatColor.GOLD + pvpClass.getName()));
			if (pvpClass instanceof BardClass)
			{
				BardClass bardClass = (BardClass) pvpClass;
				lines.add(new SidebarEntry(ChatColor.AQUA.toString() + ChatColor.BOLD, "Bard Energy", ChatColor.GRAY + ": " + ChatColor.GOLD + handleBardFormat(bardClass.getEnergyMillis(player), true)));

				long remaining = bardClass.getRemainingBuffDelay(player);
				if (remaining > 0)
				{
					lines.add(new SidebarEntry(ChatColor.AQUA.toString() + ChatColor.BOLD, "Buff Delay", ChatColor.GRAY + ": " + ChatColor.GOLD + DurationFormatter.getRemaining(remaining, true)));
				}
			}
			if ((pvpClass instanceof MinerClass))
			{
				MinerClass minerClass = (MinerClass) pvpClass;
			}
			if ((pvpClass instanceof ArcherClass))
			{
				UUID uuid = player.getUniqueId();
				ArcherClass archerClass = (ArcherClass) pvpClass;
				long timestamp = ArcherClass.archerSpeedCooldowns.get(uuid);
				long millis = System.currentTimeMillis();
				long remaining = timestamp == ArcherClass.archerSpeedCooldowns.getNoEntryValue() ? -1L : timestamp - millis;
				long remaining11 = timestamp == ArcherClass.archerJumpCooldowns.getNoEntryValue() ? -1L : timestamp - millis;

				if (remaining > 0L)
				{
					lines.add(new SidebarEntry(" " + ChatColor.GOLD + ChatColor.BOLD, "» Delay", ChatColor.GRAY + ": " + ChatColor.GOLD + DurationFormatter.getRemaining(remaining, true)));
				}
			}
		}

		Collection<Timer> timers = plugin.getTimerManager().getTimers();
		for (Timer timer : timers)
		{
			if (timer instanceof PlayerTimer)
			{
				PlayerTimer playerTimer = (PlayerTimer) timer;
				long remaining = playerTimer.getRemaining(player);
				if (remaining <= 0)
					continue;

				String timerName = playerTimer.getName();
				if (timerName.length() > 14)
					timerName = timerName.substring(0, timerName.length());
				lines.add(new SidebarEntry(playerTimer.getScoreboardPrefix(), timerName + ChatColor.GRAY, ": " + ChatColor.GOLD + DurationFormatter.getRemaining(remaining, true)));
			}
		}

		if (conquestLines != null && !conquestLines.isEmpty())
		{
			if (!lines.isEmpty())
			{
				conquestLines.add(new SidebarEntry("", "", ""));
			}

			conquestLines.addAll(lines);
			lines = conquestLines;
		}
		if (!lines.isEmpty())
		{
			lines.add(0, new SidebarEntry(ChatColor.GRAY, STRAIGHT_LINE, STRAIGHT_LINE));
			lines.add(lines.size(), new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + STRAIGHT_LINE, STRAIGHT_LINE));
		}

		return lines;
	}

	private static String handleBardFormat(long millis, boolean trailingZero)
	{
		return (trailingZero ? DateTimeFormats.REMAINING_SECONDS_TRAILING : DateTimeFormats.REMAINING_SECONDS).get().format(millis * 0.001);
	}

	public static String translate(String input)
	{
		return ChatColor.translateAlternateColorCodes('&', input);
	}
}
