package me.partlysunny.gdxlib.control.action;

import java.util.ArrayList;
import java.util.List;

public class ActionSet {

    private final List<Action> actions = new ArrayList<>();

    public static ActionSet of(Action... actions) {
        ActionSet actionSet = new ActionSet();
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

    public boolean verify(ActionContext context) {
        for (Action action : actions) {
            if (!action.isActive(context)) {
                return false;
            }
        }
        return true;
    }

}
