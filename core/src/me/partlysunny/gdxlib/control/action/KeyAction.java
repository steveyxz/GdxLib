package me.partlysunny.gdxlib.control.action;

/**
 * An action that is triggered by a key press
 */
public class KeyAction implements Action {

    //Use magic values from Input.Keys class
    private final int keycode;
    private final boolean just;

    public KeyAction(int keycode) {
        this(keycode, false);
    }

    public KeyAction(int keycode, boolean just) {
        this.keycode = keycode;
        this.just = just;
    }

    @Override
    public boolean isActive(ActionContext context) {
        if (just) {
            return context.isKeyButtonJustDown(keycode);
        } else {
            return context.isKeyDown(keycode);
        }
    }
}
