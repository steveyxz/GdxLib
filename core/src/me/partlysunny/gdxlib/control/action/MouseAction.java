package me.partlysunny.gdxlib.control.action;

/**
 * An action that is triggered by a mouse button press
 */
public class MouseAction implements Action {

    //Use magic values from Input.Buttons class
    private final int button;
    private final boolean just;

    public MouseAction(int button) {
        this(button, false);
    }

    public MouseAction(int button, boolean just) {
        this.button = button;
        this.just = just;
    }

    @Override
    public boolean isActive(ActionContext context) {
        if (just) {
            return context.isMouseButtonJustDown(button);
        } else {
            return context.isMouseButtonDown(button);
        }
    }
}
