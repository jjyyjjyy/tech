package me.jy.lang.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author jy
 * @date 2018/02/05
 */
public class ServiceEntryPoint {

    public static void export(Object clazz, int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object result = serverCall(inputStream, clazz);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object serverCall(ObjectInputStream inputStream, Object clazz) {
        try {
            String methodName = inputStream.readUTF();
            Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
            Object[] params = (Object[]) inputStream.readObject();
            Method declaredMethod = clazz.getClass().getDeclaredMethod(methodName, paramTypes);
            System.out.println("accept call: method=[" + methodName + "], params=" + Arrays.toString(params));
            Object result = declaredMethod.invoke(clazz, params);
            System.out.println("send result: " + result);
            return result;
        } catch (ClassNotFoundException | IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public static <T> T remoteCall(Class<T> clazz, String ip, int port) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, (proxy, method, args) -> {
            Socket socket = null;
            try {
                socket = new Socket(ip, port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object result = inputStream.readObject();
                inputStream.close();
                outputStream.close();
                return result;
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }

        });
    }
}
