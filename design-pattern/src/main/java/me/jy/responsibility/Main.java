package me.jy.responsibility;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

    public static void main(String[] args) {

        Trouble trouble = new Trouble(2);
        Support noSupport = new NoSupport();
        Support oddSupport = new OddSupport();
        Support evenSupport = new EvenSupport();
        noSupport.setNext(oddSupport).setNext(evenSupport);

        noSupport.solve(trouble);
    }
}
