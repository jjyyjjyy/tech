package me.jy.state;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

    public static void main(String[] args) {
        Door door = new Door();
        door.operate();
        System.out.println(door.getCurrentDoorState());
        door.operate();
        System.out.println(door.getCurrentDoorState());
        door.operate();
        System.out.println(door.getCurrentDoorState());
        door.operate();
        System.out.println(door.getCurrentDoorState());
    }
}
