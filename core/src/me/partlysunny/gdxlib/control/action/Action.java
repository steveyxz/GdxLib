package me.partlysunny.gdxlib.control.action;

/**
 * An action that the player can perform.
 */
public interface Action {

    /**
     * Checks if the action is currently active.
     * @param context The context to check the action in.
     * @return True if the action is active, false otherwise.
     */
    boolean isActive(ActionContext context);

}
