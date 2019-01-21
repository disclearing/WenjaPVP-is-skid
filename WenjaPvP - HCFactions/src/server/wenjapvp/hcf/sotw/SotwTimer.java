package server.wenjapvp.hcf.sotw;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import server.wenjapvp.hcf.HCF;

public class SotwTimer {

    @Getter
    private SotwRunnable sotwRunnable;

    public boolean cancel() {
        if (this.sotwRunnable != null) {
            this.sotwRunnable.cancel();
            this.sotwRunnable = null;
            return true;
        }

        return false;
    }

    public void start(long millis) {
        if (this.sotwRunnable == null) {
            this.sotwRunnable = new SotwRunnable(this, millis);
            this.sotwRunnable.runTaskLater(HCF.getPlugin(), millis / 50L);
        }
    }

    public SotwRunnable getSotwRunnable() {
        return sotwRunnable;
    }

    public static class SotwRunnable extends BukkitRunnable {

        private SotwTimer sotwTimer;
        private long startMillis;
        private long endMillis;

        public SotwRunnable(SotwTimer sotwTimer, long duration) {
            this.sotwTimer = sotwTimer;
            this.startMillis = System.currentTimeMillis();
            this.endMillis = this.startMillis + duration;
        }

        public long getRemaining() {
            return endMillis - System.currentTimeMillis();
        }

        @Override
        public void run() {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lWenjaPvP - &eSOTW has ended &6&lGOOD LUCK!"));
            this.cancel();
            this.sotwTimer.sotwRunnable = null;
        }
    }
}
