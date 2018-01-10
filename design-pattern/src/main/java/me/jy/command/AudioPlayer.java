package me.jy.command;

/**
 * @author jy
 * @date 2018/01/10
 */
public class AudioPlayer implements Player {
    public void play() {
        System.out.println("播放");
    }

    public void stop() {
        System.out.println("停止");
    }

    public void rewind() {
        System.out.println("倒带");
    }
}
