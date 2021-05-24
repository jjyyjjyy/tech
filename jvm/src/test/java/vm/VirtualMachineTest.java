package vm;

import com.sun.tools.attach.VirtualMachine;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author jy
 */
class VirtualMachineTest {

    @Test
    void testList() {
        VirtualMachine.list()
            .forEach(System.out::println);
    }

    @Test
    @SneakyThrows
    void testAttach() {
        VirtualMachine.list()
            .stream()
            .filter(descriptor -> descriptor.displayName().indexOf("SpringWebDemoApplication") > 0)
            .findAny()
            .ifPresent(descriptor -> {
                try {
                    VirtualMachine virtualMachine = VirtualMachine.attach(descriptor);
                    virtualMachine.loadAgent("/Users/jy/IdeaProjects/tech/framework-demo/java-agent-demo/target/java-agent-demo.jar");
                    virtualMachine.detach();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
