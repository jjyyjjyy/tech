package me.jy._15_command;

/**
 * @author jy
 */
public class PlayCommand implements Command {

    @Override
    public void execute(Player player) {
        player.play();
    }
}
