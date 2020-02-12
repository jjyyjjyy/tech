package me.jy._15_command;

/**
 * @author jy
 */
public class RewindCommand implements Command {
    @Override
    public void execute(Player player) {
        player.rewind();
    }
}
