package server.wenjapvp.hcf.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.config.ConfigurationService;
import server.wenjapvp.hcf.pvpclass.archer.ArcherClass;
import server.wenjapvp.hcf.faction.type.PlayerFaction;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerBoard {

    private boolean sidebarVisible = false;
    private SidebarProvider defaultProvider;
    private SidebarProvider temporaryProvider;
    private BukkitRunnable runnable;

    private final AtomicBoolean removed = new AtomicBoolean(false);
    private final Team members;
    private final Team neutrals;
    private final Team allies;
    private final Team archers;
    // private final Team archerTagged;

    private final BufferedObjective bufferedObjective;
    private final Scoreboard scoreboard;
    private final Player player;

    private final HCF plugin;

    public PlayerBoard(HCF plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        this.scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        this.bufferedObjective = new BufferedObjective(scoreboard);

        this.members = scoreboard.registerNewTeam("members");
        this.members.setPrefix(ConfigurationService.TEAMMATE_COLOUR.toString());
        this.members.setCanSeeFriendlyInvisibles(true);

        this.neutrals = scoreboard.registerNewTeam("neutrals");
        this.neutrals.setPrefix(ConfigurationService.ENEMY_COLOUR.toString());

        (this.archers = this.scoreboard.registerNewTeam("archers")).setPrefix(ChatColor.DARK_RED.toString());

        this.allies = scoreboard.registerNewTeam("enemies");
        this.allies.setPrefix(ConfigurationService.ALLY_COLOUR.toString());
        
      //  this.archerTagged = this.scoreboard.registerNewTeam("archerTagged").setPrefix(ChatColor.DARK_RED.toString());

        player.setScoreboard(scoreboard);
    }

    /**
     * Removes this {@link PlayerBoard}.
     */
    public void remove() {
        if (!this.removed.getAndSet(true) && scoreboard != null) {
            for (Team team : scoreboard.getTeams()) {
                team.unregister();
            }

            for (Objective objective : scoreboard.getObjectives()) {
                objective.unregister();
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public boolean isSidebarVisible() {
        return this.sidebarVisible;
    }

    public void setSidebarVisible(boolean visible) {
        this.sidebarVisible = visible;
        this.bufferedObjective.setDisplaySlot(visible ? DisplaySlot.SIDEBAR : null);
    }

    public void setDefaultSidebar(final SidebarProvider provider, long updateInterval) {
        if (provider != this.defaultProvider) {
            this.defaultProvider = provider;
            if (this.runnable != null) {
                this.runnable.cancel();
            }

            if (provider == null) {
                this.scoreboard.clearSlot(DisplaySlot.SIDEBAR);
                return;
            }

            (this.runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (removed.get()) {
                        cancel();
                        return;
                    }

                    if (provider == defaultProvider) {
                        updateObjective();
                    }
                }
            }).runTaskTimerAsynchronously(plugin, updateInterval, updateInterval);
        }
    }

    public void setTemporarySidebar(final SidebarProvider provider, final long expiration) {
        if (this.removed.get()) {
            throw new IllegalStateException("Cannot update whilst board is removed");
        }

        this.temporaryProvider = provider;
        this.updateObjective();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (removed.get()) {
                    cancel();
                    return;
                }

                if (temporaryProvider == provider) {
                    temporaryProvider = null;
                    updateObjective();
                }
            }
        }.runTaskLaterAsynchronously(plugin, expiration);
    }

    private void updateObjective() {
        if (this.removed.get()) {
            throw new IllegalStateException("Cannot update whilst board is removed");
        }

        SidebarProvider provider = this.temporaryProvider != null ? this.temporaryProvider : this.defaultProvider;
        if (provider == null) {
            this.bufferedObjective.setVisible(false);
        } else {
            this.bufferedObjective.setTitle(provider.getTitle());
            this.bufferedObjective.setAllLines(provider.getLines(player));
            this.bufferedObjective.flip();
        }
    }

    public void addUpdate(Player target) {
        this.addUpdates(Collections.singleton(target));
    }

    public void addUpdates(Iterable<? extends Player> updates) {
        if (this.removed.get()) {
            throw new IllegalStateException("Cannot update whilst board is removed");
        }

        new BukkitRunnable() {
            @Override
            public void run()
            {
                PlayerFaction playerFaction = null;
                boolean hasRun = false;
                for (Player update : updates) {
                    if (PlayerBoard.this.player.equals(update))
                    {
                        if (ArcherClass.TAGGED.containsKey(update.getUniqueId())) {
                            PlayerBoard.this.archers.addPlayer(update);
                        }
                        PlayerBoard.this.members.addPlayer(update);
                    }
                    else
                    {
                        if (!hasRun)
                        {
                            playerFaction = PlayerBoard.this.plugin.getFactionManager().getPlayerFaction(PlayerBoard.this.player);
                            hasRun = true;
                        }
                        if (ArcherClass.TAGGED.containsKey(update.getUniqueId()))
                        {
                            PlayerBoard.this.archers.addPlayer(update);
                        }
                        else
                        {
                            PlayerFaction targetFaction;
                            if ((playerFaction == null) || ((targetFaction = PlayerBoard.this.plugin.getFactionManager().getPlayerFaction(update)) == null))
                            {
                                PlayerBoard.this.neutrals.addPlayer(update);
                            }
                            else
                            {
                                if (playerFaction.equals(targetFaction)) {
                                    PlayerBoard.this.members.addPlayer(update);
                                } else if (playerFaction.getAllied().contains(targetFaction.getUniqueID())) {
                                    PlayerBoard.this.allies.addPlayer(update);
                                } else {
                                    PlayerBoard.this.neutrals.addPlayer(update);
                                }
                            }
                        }
                    }
                }
            }
        }

                .runTaskAsynchronously(this.plugin);
    }
}
