package server.wenjapvp.hcf;

import java.util.Map;
import java.util.Random;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.doctordark.internal.com.doctordark.base.BasePlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import lombok.Getter;
import server.wenjapvp.hcf.combatlog.CustomEntityRegistration;
import server.wenjapvp.hcf.economy.EconomyCommand;
import server.wenjapvp.hcf.economy.EconomyManager;
import server.wenjapvp.hcf.economy.PayCommand;
import server.wenjapvp.hcf.eventgame.CaptureZone;
import server.wenjapvp.hcf.eventgame.EventExecutor;
import server.wenjapvp.hcf.eventgame.crate.KeyListener;
import server.wenjapvp.hcf.eventgame.crate.KeyManager;
import server.wenjapvp.hcf.eventgame.eotw.EotwCommand;
import server.wenjapvp.hcf.eventgame.eotw.EotwHandler;
import server.wenjapvp.hcf.eventgame.eotw.EotwListener;
import server.wenjapvp.hcf.eventgame.faction.CapturableFaction;
import server.wenjapvp.hcf.eventgame.faction.ConquestFaction;
import server.wenjapvp.hcf.faction.FactionExecutor;
import server.wenjapvp.hcf.faction.FactionManager;
import server.wenjapvp.hcf.faction.FactionMember;
import server.wenjapvp.hcf.faction.FlatFileFactionManager;
import server.wenjapvp.hcf.faction.argument.FactionClaimChunkArgument;
import server.wenjapvp.hcf.faction.claim.ClaimHandler;
import server.wenjapvp.hcf.faction.claim.ClaimWandListener;
import server.wenjapvp.hcf.faction.claim.Subclaim;
import server.wenjapvp.hcf.faction.type.EndPortalFaction;
import server.wenjapvp.hcf.faction.type.Faction;
import server.wenjapvp.hcf.faction.type.RoadFaction;
import server.wenjapvp.hcf.faction.type.SpawnFaction;
import server.wenjapvp.hcf.pvpclass.PvpClassManager;
import server.wenjapvp.hcf.pvpclass.bard.EffectRestorer;
import server.wenjapvp.hcf.scoreboard.ScoreboardHandler;
import server.wenjapvp.hcf.sotw.SotwCommand;
import server.wenjapvp.hcf.sotw.SotwListener;
import server.wenjapvp.hcf.timer.TimerExecutor;
import server.wenjapvp.hcf.timer.TimerManager;
import server.wenjapvp.hcf.user.FactionUser;
import server.wenjapvp.hcf.user.UserManager;
import server.wenjapvp.hcf.visualise.VisualiseHandler;
import server.wenjapvp.hcf.visualise.WallBorderListener;
import server.wenjapvp.hcfold.MapKitCommand;
import server.wenjapvp.hcf.combatlog.CombatLogListener;
import server.wenjapvp.hcf.command.LogoutCommand;
import server.wenjapvp.hcf.command.ServerTimeCommand;
import server.wenjapvp.hcf.config.ConfigurationService;
import server.wenjapvp.hcf.economy.FlatFileEconomyManager;
import server.wenjapvp.hcf.economy.ShopSignListener;
import server.wenjapvp.hcf.eventgame.EventScheduler;
import server.wenjapvp.hcf.eventgame.conquest.ConquestExecutor;
import server.wenjapvp.hcf.eventgame.faction.KothFaction;
import server.wenjapvp.hcf.eventgame.koth.KothExecutor;
import server.wenjapvp.hcf.faction.argument.FactionManagerArgument;
import server.wenjapvp.hcf.faction.claim.Claim;
import server.wenjapvp.hcf.faction.type.ClaimableFaction;
import server.wenjapvp.hcf.faction.type.PlayerFaction;
import server.wenjapvp.hcf.listener.BorderListener;
import server.wenjapvp.hcf.listener.ChatListener;
import server.wenjapvp.hcf.listener.CoreListener;
import server.wenjapvp.hcf.listener.CrowbarListener;
import server.wenjapvp.hcf.listener.DeathListener;
import server.wenjapvp.hcf.listener.DeathMessageListener;
import server.wenjapvp.hcf.listener.DeathSignListener;
import server.wenjapvp.hcf.listener.FactionListener;
import server.wenjapvp.hcf.listener.ProtectionListener;
import server.wenjapvp.hcf.listener.SignSubclaimListener;
import server.wenjapvp.hcf.listener.WorldListener;
import server.wenjapvp.hcf.sotw.SotwTimer;
import server.wenjapvp.hcf.visualise.ProtocolLibHook;
import server.wenjapvp.hcfold.EndListener;
import server.wenjapvp.hcfold.EventSignListener;

public class HCF extends JavaPlugin {

	public static HCF plugin;
	@Getter
	private Random random = new Random();
	@Getter
	private ClaimHandler claimHandler;
	@Getter
	private EconomyManager economyManager;
	@Getter
	private EffectRestorer effectRestorer;
	@Getter
	private EotwHandler eotwHandler;
	@Getter
	private EventScheduler eventScheduler;
	@Getter
	private FactionManager factionManager;
	@Getter
	private PvpClassManager pvpClassManager;
	@Getter
	private ScoreboardHandler scoreboardHandler;
	@Getter
	private SotwTimer sotwTimer;
	@Getter
	public static TimerManager timerManager;
	@Getter
	private KeyManager keyManager;
	@Getter
	private UserManager userManager;
	@Getter
	private VisualiseHandler visualiseHandler;
	@Getter
	private WorldEditPlugin worldEdit;
	@Getter
	private CombatLogListener combatLogListener;

	public static HCF getPlugin() {
		return plugin;
	}

	public TimerManager getTimerManager() {
		return timerManager;
	}

	@Override
	public void onEnable() {
		Cooldowns.createCooldown("medic_cooldown");

		HCF.plugin = this;
		BasePlugin.getPlugin().init(this);

		ProtocolLibHook.hook(this);

		Plugin wep = getServer().getPluginManager().getPlugin("WorldEdit");
		this.worldEdit = wep instanceof WorldEditPlugin && wep.isEnabled() ? (WorldEditPlugin) wep : null;
		CustomEntityRegistration.registerCustomEntities();

//		ConfigurationService.init(this);
		this.effectRestorer = new EffectRestorer(this);
		this.registerConfiguration();
		this.registerCommands();

		this.claimHandler = new ClaimHandler(this);
		this.economyManager = new FlatFileEconomyManager(this);
		this.eotwHandler = new EotwHandler(this);
		this.eventScheduler = new EventScheduler(this);
		this.factionManager = new FlatFileFactionManager(this);
		this.keyManager = new KeyManager(this);
		this.pvpClassManager = new PvpClassManager(this);
		this.sotwTimer = new SotwTimer();
		this.timerManager = new TimerManager(this); // needs to be registered
													// before ScoreboardHandler
		this.scoreboardHandler = new ScoreboardHandler(this);
		this.userManager = new UserManager(this);
		this.visualiseHandler = new VisualiseHandler();

		this.registerListeners();
	}

	private void saveData() {
		this.combatLogListener.removeCombatLoggers();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.keyManager.saveKeyData();
		this.timerManager.saveTimerData();
		this.userManager.saveUserData();
	}

	@Override
	public void onDisable() {
		this.combatLogListener.removeCombatLoggers();
		this.pvpClassManager.onDisable();
		this.scoreboardHandler.clearBoards();
		this.factionManager.saveFactionData();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.keyManager.saveKeyData();
		this.timerManager.saveTimerData();
		this.userManager.saveUserData();
		this.saveData();

		HCF.plugin = null; // always initialise last
	}

	private void registerConfiguration() {
		ConfigurationSerialization.registerClass(CaptureZone.class);
		ConfigurationSerialization.registerClass(Claim.class);
		ConfigurationSerialization.registerClass(Subclaim.class);
		ConfigurationSerialization.registerClass(FactionUser.class);
		ConfigurationSerialization.registerClass(ClaimableFaction.class);
		ConfigurationSerialization.registerClass(ConquestFaction.class);
		ConfigurationSerialization.registerClass(CapturableFaction.class);
		ConfigurationSerialization.registerClass(KothFaction.class);
		ConfigurationSerialization.registerClass(EndPortalFaction.class);
		ConfigurationSerialization.registerClass(Faction.class);
		ConfigurationSerialization.registerClass(FactionMember.class);
		ConfigurationSerialization.registerClass(PlayerFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.class);
		ConfigurationSerialization.registerClass(SpawnFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.NorthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.EastRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.SouthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.WestRoadFaction.class);
	}

	private void registerListeners() {
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(this.combatLogListener = new CombatLogListener(this), this);
		manager.registerEvents(new FactionManagerArgument(this), this);
		manager.registerEvents(new FactionClaimChunkArgument(this), this);
		manager.registerEvents(new BorderListener(), this);
		manager.registerEvents(new ChatListener(this), this);
		manager.registerEvents(new ClaimWandListener(this), this);
		manager.registerEvents(new CoreListener(this), this);
		manager.registerEvents(new CrowbarListener(this), this);
		manager.registerEvents(new DeathListener(this), this);
		manager.registerEvents(new DeathMessageListener(this), this);
		manager.registerEvents(new DeathSignListener(this), this);
		manager.registerEvents(new EndListener(), this);
		manager.registerEvents(new EotwListener(this), this);
		manager.registerEvents(new EventSignListener(), this);
		manager.registerEvents(new FactionListener(this), this);
		manager.registerEvents(new KeyListener(this), this);
		manager.registerEvents(new ProtectionListener(this), this);
		manager.registerEvents(new SignSubclaimListener(this), this);
		manager.registerEvents(new ShopSignListener(this), this);
		manager.registerEvents(new SotwListener(this), this);
		manager.registerEvents(new WallBorderListener(this), this);
		manager.registerEvents(new WorldListener(this), this);
	}

	private void registerCommands() {
		getCommand("conquest").setExecutor((CommandExecutor) new ConquestExecutor(this));
		getCommand("economy").setExecutor(new EconomyCommand(this));
		getCommand("eotw").setExecutor(new EotwCommand(this));
		getCommand("event").setExecutor((CommandExecutor) new EventExecutor(this));
		getCommand("faction").setExecutor((CommandExecutor) new FactionExecutor(this));
		getCommand("koth").setExecutor((CommandExecutor) new KothExecutor(this));
		getCommand("logout").setExecutor(new LogoutCommand(this));
		getCommand("mapkit").setExecutor(new MapKitCommand(this));
		getCommand("pay").setExecutor(new PayCommand(this));
		getCommand("servertime").setExecutor(new ServerTimeCommand());
		getCommand("sotw").setExecutor(new SotwCommand(this));
		getCommand("timer").setExecutor((CommandExecutor) new TimerExecutor(this));

		Map<String, Map<String, Object>> map = getDescription().getCommands();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			PluginCommand command = getCommand(entry.getKey());
			command.setPermission("" + entry.getKey());
			command.setPermissionMessage(ChatColor.RED + "You do not have permissions to execute this command.");
		}
	}

	private void registerCooldowns() {
		Cooldowns.createCooldown("medic_cooldown");
	}

	public EotwHandler getEotwHandler() {
		return eotwHandler;
	}

	public FactionManager getFactionManager() {
		return factionManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public Random getRandom() {
		return random;
	}

	public ScoreboardHandler getScoreboardHandler() {
		return scoreboardHandler;
	}

	public SotwTimer getSotwTimer() {
		return sotwTimer;
	}

	public PvpClassManager getPvpClassManager() {
		return pvpClassManager;
	}

	public EffectRestorer getEffectRestorer() {
		return effectRestorer;
	}

	public EconomyManager getEconomyManager() {
		return economyManager;
	}

	public VisualiseHandler getVisualiseHandler() {
		return visualiseHandler;
	}
}
