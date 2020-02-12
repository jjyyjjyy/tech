package me.jy._19_mediator;

/**
 * @author jy
 */
public class Mediator {

    private ConcreteColleagueA colleagueA;
    private ConcreteColleagueB colleagueB;

    public void setColleagueA(ConcreteColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ConcreteColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    public void contact(String message, Colleague colleague) {
        if (colleague == colleagueA) {
            colleagueB.getMessage(message);
        }else {
            colleagueA.getMessage(message);

        }
    }
}
