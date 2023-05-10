package me.partlysunny.gdxlib.control.action;

public class MouseAction implements Action {

    //Use magic values from Input.Buttons class
    private final int button;

    public MouseAction(int button) {
        this.button = button;
    }

    @Override
    public boolean isActive(ActionContext context) {
        return context.isMouseButtonDown(button);
    }
}
