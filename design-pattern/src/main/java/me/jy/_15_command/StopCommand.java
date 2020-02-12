package me.jy._15_command;

/**
 * @author jy
 */
public class StopCommand implements Command {
    @Override
    public void execute(Player player) {
        player.stop();
    }
}
