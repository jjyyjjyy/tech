package me.jy._10_composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class ButtonGroup extends Component {

    private List<Button> buttons = new ArrayList<>();

    public ButtonGroup(String name) {
        super(name);
    }

    @Override
    protected void render() {
        System.out.println("=== Start rendering button group ===");
        this.buttons.forEach(b -> System.out.println("Render button : " + b.getName()));
        System.out.println("=== End rendering button group ===");
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void removeButton(Button button) {
        buttons.remove(button);
    }
}
