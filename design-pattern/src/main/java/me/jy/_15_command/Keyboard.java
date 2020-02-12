package me.jy._15_command;

/**
 * @author jy
 */
public class Keyboard implements Player {

    private final Player player;
    private PlayCommand playCommand = new PlayCommand();
    private StopCommand stopCommand = new StopCommand();
    private RewindCommand rewindCommand = new RewindCommand();

    public Keyboard(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        playCommand.execute(this.player);
    }

    @Override
    public void stop() {
        stopCommand.execute(this.player);
    }

    @Override
    public void rewind() {
        rewindCommand.execute(this.player);
    }
}
