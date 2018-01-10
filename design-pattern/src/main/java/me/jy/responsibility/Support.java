package me.jy.responsibility;

/**
 * @author jy
 * @date 2018/01/10
 */
public abstract class Support {

    private String name;

    private Support next;

    protected abstract boolean support(Trouble trouble);

    public void solve(Trouble trouble) {
        if (this.support(trouble)) {
            doSolve(trouble);
        } else if (next != null) {
            next.doSolve(trouble);
        } else {
            fail(trouble);
        }
    }

    private void doSolve(Trouble trouble) {
        System.out.println(getClass().getSimpleName() + " has solved " + trouble);
    }

    private void fail(Trouble trouble) {
        System.out.println(trouble + " solved failed!");
    }

    public Support setNext(Support next) {
        this.next = next;
        return this;
    }
}
