package me.jy.lang.privilege;

/**
 * @author jy
 * @date 2017/12/03
 */
public class SubInSamePackage extends Parent {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.get();
        Parent.staticGet();

        SubInSamePackage ssp = new SubInSamePackage();
        ssp.get();
        SubInSamePackage.staticGet();
    }
}
