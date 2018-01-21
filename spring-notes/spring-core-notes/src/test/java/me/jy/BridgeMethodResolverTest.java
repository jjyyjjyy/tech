package me.jy;

import me.jy.testsuites.SubGenericObject;
import org.junit.Test;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author jy
 * @date 2018/01/21
 */
public class BridgeMethodResolverTest {

    @Test
    public void testApi() throws NoSuchMethodException {
        // 根据桥接方法(Object)找泛型方法(String)
        Method methodByCompiler = SubGenericObject.class.getDeclaredMethod("get", Object.class);
        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(methodByCompiler);

        assertTrue(!bridgedMethod.isBridge());
        Method[] declaredMethods = SubGenericObject.class.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            System.out.println(method.toString() + " " + method.isBridge());
        });

        assertEquals("s", new DefaultParameterNameDiscoverer().getParameterNames(methodByCompiler)[0]);
    }
}
