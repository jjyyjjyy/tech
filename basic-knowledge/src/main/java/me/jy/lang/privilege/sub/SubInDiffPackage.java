package me.jy.lang.privilege.sub;

import me.jy.lang.privilege.Parent;

/**
 * @author jy
 * @date 2017/12/03
 */
public class SubInDiffPackage extends Parent {

    public static void main(String[] args) {
        Parent parent = new Parent();
        SubInDiffPackage sdp = new SubInDiffPackage();
        sdp.get();
        Parent.staticGet();
//        parent.get(); sad

        SubInDiffPackage2 sip = new SubInDiffPackage2();
        sdp.get();
        SubInDiffPackage.staticGet();
    }
}
