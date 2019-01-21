package server.wenjapvp.hcf.combatlog;

import java.util.UUID;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import server.wenjapvp.hcf.HCF;

public abstract interface LoggerEntity
{
    public abstract void postSpawn(HCF paramHCF);

    public abstract CraftPlayer getBukkitEntity();

    public abstract UUID getUniqueID();

    public abstract void destroy();
}
