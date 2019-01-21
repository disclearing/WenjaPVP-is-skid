package server.wenjapvp.hcf.timer;

import lombok.Data;
import lombok.Getter;
import com.doctordark.util.Config;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import server.wenjapvp.hcf.eventgame.EventTimer;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.timer.type.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class TimerManager implements Listener {

    @Getter
	public
    final CombatTimer combatTimer;

    @Getter
    private final LogoutTimer logoutTimer;

    @Getter
    private final EnderPearlTimer enderPearlTimer;

    @Getter
    private final EventTimer eventTimer;

    @Getter
    private final AppleTimer appleTimer;

    @Getter
	public
    final GappleTimer gappleTimer;

    @Getter
    private final InvincibilityTimer invincibilityTimer;

    @Getter
    public final ArcherTimer archerTimer;

    @Getter
    private final ClassLoad pvpClassWarmupTimer;

    @Getter
    private final StuckTimer stuckTimer;

    @Getter
	public
    final TeleportTimer teleportTimer;

    @Getter
    private final Set<Timer> timers = new LinkedHashSet<>();

    private final JavaPlugin plugin;
    private Config config;

    public TimerManager(HCF plugin) {
        (this.plugin = plugin).getServer().getPluginManager().registerEvents(this, plugin);
        this.registerTimer(this.enderPearlTimer = new EnderPearlTimer(plugin));
        this.registerTimer(this.logoutTimer = new LogoutTimer());
        this.registerTimer(this.gappleTimer = new GappleTimer(plugin));
        this.registerTimer(this.stuckTimer = new StuckTimer());
        this.registerTimer(this.invincibilityTimer = new InvincibilityTimer(plugin));
        this.registerTimer(this.combatTimer = new CombatTimer(plugin));
        this.registerTimer(this.teleportTimer = new TeleportTimer(plugin));
        this.registerTimer(this.eventTimer = new EventTimer(plugin));
        this.registerTimer(this.pvpClassWarmupTimer = new ClassLoad(plugin));
        this.registerTimer(this.archerTimer = new ArcherTimer(plugin));
        this.registerTimer(this.appleTimer = new AppleTimer(plugin));
        this.reloadTimerData();
    }

    public void registerTimer(Timer timer) {
        this.timers.add(timer);
        if (timer instanceof Listener) {
            this.plugin.getServer().getPluginManager().registerEvents((Listener) timer, this.plugin);
        }
    }

    public void unregisterTimer(Timer timer) {
        this.timers.remove(timer);
    }

    /**
     * Reloads the {@link Timer} data from storage.
     */
    public void reloadTimerData() {
        this.config = new Config((HCF) plugin, "timers");
        for (Timer timer : this.timers) {
            timer.load(this.config);
        }
    }

    /**
     * Saves the {@link Timer} data to storage.
     */
    public void saveTimerData() {
        for (Timer timer : this.timers) {
            timer.onDisable(this.config);
        }

        this.config.save();
    }

    public GappleTimer getGappleTimer() {
        return gappleTimer;
    }

    public LogoutTimer getLogoutTimer() {
        return logoutTimer;
    }

    public InvincibilityTimer getInvincibilityTimer() {
        return invincibilityTimer;
    }

    public Set<Timer> getTimers() {
        return timers;
    }

    public EventTimer getEventTimer() {
        return eventTimer;
    }

    public EnderPearlTimer getEnderPearlTimer() {
        return enderPearlTimer;
    }

    public CombatTimer getCombatTimer() {
        return combatTimer;
    }

    public TeleportTimer getTeleportTimer() {
        return teleportTimer;
    }
}
