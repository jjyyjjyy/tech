package me.jy.state;

/**
 * @author jy
 * @date 2018/01/10
 */
public enum DoorState {
    CLOSED,
    OPENING,
    OPENED,
    CLOSING;

    protected DoorState nextState(DoorState state) {
        return DoorState.values()[(state.ordinal() + 1) % DoorState.values().length];
    }
}
