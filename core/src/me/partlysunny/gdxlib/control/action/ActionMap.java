package me.partlysunny.gdxlib.control.action;

import me.partlysunny.gdxlib.util.Pair;

import java.util.*;

public class ActionMap implements Iterable<String> {

    @SafeVarargs
    public static ActionMap of(Pair<String, ActionSet>... actions) {
        ActionMap actionMap = new ActionMap();
        for (Pair<String, ActionSet> action : actions) {
            actionMap.addActions(action.getFirst(), action.getSecond());
        }
        return actionMap;
    }

    private final Map<String, ActionSet> actionMap = new HashMap<>();

    public void addActions(String actionName, Action... actionCodes) {
        if (!hasAction(actionName)) {
            actionMap.put(actionName, new ActionSet());
        }
        for (Action action : actionCodes) {
            actionMap.get(actionName).addAction(action);
        }
    }

    public void addActions(String actionName, ActionSet actionSet) {
        if (!hasAction(actionName)) {
            actionMap.put(actionName, new ActionSet());
        }
        for (Action action : actionSet.getActions()) {
            actionMap.get(actionName).addAction(action);
        }
    }

    public ActionSet getActions(String actionName) {
        return actionMap.get(actionName);
    }

    public boolean isActive(String actionName, ActionContext context) {
        return hasAction(actionName) && getActions(actionName).verify(context);
    }

    public boolean hasAction(String actionName) {
        return actionMap.containsKey(actionName);
    }

    public void removeAction(String actionName) {
        actionMap.remove(actionName);
    }

    public void clear() {
        actionMap.clear();
    }

    public void addActions(ActionMap other) {
        for (Map.Entry<String, ActionSet> entry : other.actionMap.entrySet()) {
            addActions(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Iterator<String> iterator() {
        return actionMap.keySet().iterator();
    }
}
