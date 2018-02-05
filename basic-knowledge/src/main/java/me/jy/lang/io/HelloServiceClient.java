package me.jy.lang.io;

/**
 * @author jy
 * @date 2018/02/05
 */
public class HelloServiceClient {

    public static void main(String[] args) {
        ServiceInterface service = ServiceEntryPoint.remoteCall(ServiceInterface.class, "127.0.0.1", 8080);
        for (int i = 65; i < 91; i++) {

            System.out.println(service.hello(String.valueOf((char) i)));
        }
    }
}
