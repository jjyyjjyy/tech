package me.jy;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * @author jy
 */
public class AgentDemo {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain loaded!");
        doAgent(agentArgs, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain loaded!");
        doAgent(agentArgs, inst);
        Arrays.stream(inst.getAllLoadedClasses())
            .filter(c -> c.getSimpleName().contains("DemoController"))
            .findAny()
            .ifPresent(c -> {
                try {
                    inst.retransformClasses(c);
                } catch (UnmodifiableClassException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    private static void doAgent(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new MyClassFileTransformer(), true);
    }

    public static class MyClassFileTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] bytes) {
            if (!"me/jy/DemoController".equals(className)) {
                return bytes;
            }
            try {
                ClassPool classPool = ClassPool.getDefault();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                CtClass clazz = classPool.makeClass(byteArrayInputStream);
                CtMethod[] methods = clazz.getMethods();
                for (CtMethod method : methods) {
                    if ("check".equals(method.getName())) {
                        System.out.println("Modify method...");
                        return fixMethod(clazz, method.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytes;
        }

        private byte[] fixMethod(CtClass clazz, String methodName) {
            try {
                CtMethod method = clazz.getDeclaredMethod(methodName);
                String bodyStr = "if (true) {return true;}";
                method.insertBefore(bodyStr);
                return clazz.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
