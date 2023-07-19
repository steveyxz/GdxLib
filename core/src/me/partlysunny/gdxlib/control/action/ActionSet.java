package me.partlysunny.gdxlib.control.action;

import java.util.ArrayList;
import java.util.List;

/**
 * A set of different user actions
 */
public class ActionSet {

    private final List<Action> actions = new ArrayList<>();
    private final VerificationType verificationType;

    public ActionSet() {
        this(VerificationType.ANY);
    }

    public ActionSet(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    /**
     * Creates an action set based of a list of actions.
     *
     * @param verification the verification type
     * @param actions a list of actions
     * @return an action set containing the given actions
     */
    public static ActionSet of(VerificationType verification, Action... actions) {
        ActionSet actionSet = new ActionSet(verification);
        for (Action action : actions) {
            actionSet.addAction(action);
        }
        return actionSet;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public void clear() {
        actions.clear();
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public Iterable<Action> getActions() {
        return actions;
    }

    /**
     * Checks if the action set is active based on the given action context.
     *
     * @param context the action context
     * @return true if the action set is active, false otherwise
     */
    public boolean verify(ActionContext context) {
        if (verificationType == VerificationType.ANY) {
            return verifyAny(context);
        } else if (verificationType == VerificationType.ALL) {
            return verifyAll(context);
        }
        return false;
    }

    private boolean verifyAll(ActionContext context) {
        for (Action action : actions) {
            if (!action.isActive(context)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyAny(ActionContext context) {
        for (Action action : actions) {
            if (action.isActive(context)) {
                return true;
            }
        }
        return false;
    }

    public enum VerificationType {
        ALL, ANY
    }

}
