package me.jy.command;

/**
 * @author jy
 * @date 2018/01/10
 */
public class PlayCommand implements Command {

    @Override
    public void execute(Player player) {
        player.play();
    }
}
