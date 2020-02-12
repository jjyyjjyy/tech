package me.jy._20_memento;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        // 保存状态
        originator.setState("状态0");
        careTaker.addMemento(originator.saveToMemento());
        originator.setState("状态1");
        careTaker.addMemento(originator.saveToMemento());

        // 恢复状态0
        originator.setState(careTaker.get(0).getState());
    }
}
