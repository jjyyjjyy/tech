package me.jy._10_composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class Panel extends Component {

    private List<Component> components = new ArrayList<>();


    public Panel(String name) {
        super(name);
    }

    @Override
    protected void render() {
        System.out.println("Render panel : ");
        components.forEach(Component::render);
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }
}
