package me.jy.lang.io;

/**
 * @author jy
 * @date 2018/02/05
 */
public class HelloServiceServer {

    public static void main(String[] args) {
        HelloService service = new HelloService();
        ServiceEntryPoint.export(service, 8080);
    }
}
