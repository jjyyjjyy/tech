package me.jy._19_mediator;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        ConcreteColleagueA colleagueA = new ConcreteColleagueA(mediator, "A");
        ConcreteColleagueB colleagueB = new ConcreteColleagueB(mediator, "B");
        mediator.setColleagueA(colleagueA);
        mediator.setColleagueB(colleagueB);

        colleagueA.contact("Hello, I am A!");
        colleagueB.contact("Hello, I am B! Nice to see u");
    }
}
