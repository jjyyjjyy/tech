package me.jy.testsuites;

/**
 * @author jy
 * @date 2018/01/21
 */
public class SubGenericObject extends TestSuperGenericObject<String> {

    @Override
    public String get(String s) {
        return "Test";
    }
}
