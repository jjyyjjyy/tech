package me.jy._12_fly_weight;

/**
 * @author jy
 */
public abstract class FlyWeight {

    private final String internalState;

    public FlyWeight(String internalState) {
        this.internalState = internalState;
    }

    public abstract void operate(String externalState);
}

