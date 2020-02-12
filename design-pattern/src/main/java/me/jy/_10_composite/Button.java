package me.jy._10_composite;

/**
 * @author jy
 */
public class Button extends Component {

    public Button(String name) {
        super(name);
    }

    @Override
    public void render() {
        System.out.println("Render button : " + getName());
    }
}
