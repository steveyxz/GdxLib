package me.partlysunny.gdxlib.control.action;

public class KeyAction implements Action {

    //Use magic values from Input.Keys class
    private final int keycode;

    public KeyAction(int keycode) {
        this.keycode = keycode;
    }

    @Override
    public boolean isActive(ActionContext context) {
        return context.isKeyDown(keycode);
    }
}
