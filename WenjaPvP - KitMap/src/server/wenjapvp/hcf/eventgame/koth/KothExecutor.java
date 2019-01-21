package server.wenjapvp.hcf.eventgame.koth;

import com.doctordark.util.command.ArgumentExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.eventgame.koth.argument.KothHelpArgument;
import server.wenjapvp.hcf.eventgame.koth.argument.KothScheduleArgument;
import server.wenjapvp.hcf.eventgame.koth.argument.KothSetCapDelayArgument;
import server.wenjapvp.hcf.eventgame.koth.argument.KothNextArgument;

/**
 * Command used to handle KingOfTheHills.
 */
public class KothExecutor extends ArgumentExecutor {

    private final KothScheduleArgument kothScheduleArgument;

    public KothExecutor(HCF plugin) {
        super("koth");

        addArgument(new KothHelpArgument(this));
        addArgument(new KothNextArgument(plugin));
        addArgument(this.kothScheduleArgument = new KothScheduleArgument(plugin));
        addArgument(new KothSetCapDelayArgument(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            this.kothScheduleArgument.onCommand(sender, command, label, args);
            return true;
        }

        return super.onCommand(sender, command, label, args);
    }
}
