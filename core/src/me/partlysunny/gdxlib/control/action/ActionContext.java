package me.partlysunny.gdxlib.control.action;

/**
 * Generic interface for a user action context.
 */
public interface ActionContext {

    /**
     * Returns whether or not the given key is currently pressed.
     *
     * @param keyCode The key code to check. See {@link com.badlogic.gdx.Input.Keys}.
     * @return Whether or not the given key is currently pressed.
     */
    boolean isKeyDown(int keyCode);

    /**
     * Returns whether or not the given mouse button was pressed this frame.
     *
     * @param button The button to check. See {@link com.badlogic.gdx.Input.Buttons}.
     * @return Whether or not the given mouse button was pressed this frame.
     */
    boolean isMouseButtonDown(int button);

}
