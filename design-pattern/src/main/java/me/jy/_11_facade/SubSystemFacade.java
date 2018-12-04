package me.jy._11_facade;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class SubSystemFacade {

    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;

    public void operate() {
        systemA.callA();
        systemB.callB();
        systemC.callC();
    }
}
