package server.wenjapvp.hcf.faction.type;

import org.bukkit.command.CommandSender;

import server.wenjapvp.hcf.config.ConfigurationService;

import java.util.Map;

/**
 * Represents the {@link WildernessFaction}.
 */
public class WildernessFaction extends Faction {

    public WildernessFaction() {
        super("The Wilderness");
    }

    public WildernessFaction(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getDisplayName(CommandSender sender) {
        return ConfigurationService.WILDERNESS_COLOUR + getName();
    }
}
