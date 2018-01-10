package me.jy.state;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Door {

    private DoorState currentDoorState;

    public Door() {
        this.currentDoorState = DoorState.CLOSED;
    }

    public void operate() {
        currentDoorState = currentDoorState.nextState(currentDoorState);
    }

    public DoorState getCurrentDoorState() {
        return currentDoorState;
    }
}
