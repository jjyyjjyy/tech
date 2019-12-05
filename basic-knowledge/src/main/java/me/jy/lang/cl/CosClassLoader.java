package me.jy.lang.cl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author jy
 */
public class CosClassLoader extends ClassLoader {

    private static final String COS_PATH = "http://resources-1252259164.file.myqcloud.com/";

    private static final String SUFFIX = ".class";

    public CosClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new CosClassLoader(CosClassLoader.class.getClassLoader());
        classLoader.loadClass("HelloWorld").getConstructor().newInstance();
    }

    /**
     * 实现父类委托
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    /**
     * 加载类文件为Class
     *
     * @param name 类权限定名
     * @return Class对象
     * @throws ClassNotFoundException occurs when file not found
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!"HelloWorld".equals(name)) { // 每个类都会先从AppLoader找
            return super.loadClass(name);
        }
        try (InputStream inputStream = new URL(COS_PATH + name + SUFFIX).openStream()) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int red;
            while ((red = inputStream.read()) != -1) {
                bos.write(red);
            }
            return this.defineClass(name, bos.toByteArray(), 0, bos.size());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new ClassNotFoundException();
        }
    }

}
