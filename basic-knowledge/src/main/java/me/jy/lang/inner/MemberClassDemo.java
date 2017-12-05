package me.jy.lang.inner;

/**
 * @author jy
 * @date 2017/12/04
 */
public class MemberClassDemo {

    private int modCount =0;

    private class ParentInner {

        private int exceptedModCount = modCount;// reachable
    }

    private class SubInner extends ParentInner {

    }
}
