package server.wenjapvp.hcf.timer;

import com.doctordark.util.command.ArgumentExecutor;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.timer.argument.TimerCheckArgument;
import server.wenjapvp.hcf.timer.argument.TimerSetArgument;

/**
 * Handles the execution and tab completion of the timer command.
 */
public class TimerExecutor extends ArgumentExecutor {

    public TimerExecutor(HCF plugin) {
        super("timer");

        addArgument(new TimerCheckArgument(plugin));
        addArgument(new TimerSetArgument(plugin));
    }
}