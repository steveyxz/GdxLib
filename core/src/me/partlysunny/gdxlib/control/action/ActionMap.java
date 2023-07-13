package me.partlysunny.gdxlib.control.action;

import me.partlysunny.gdxlib.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Maps action names to action sets.
 */
public class ActionMap implements Iterable<String> {

    private final Map<String, ActionSet> actionMap = new HashMap<>();

    /**
     * Creates an action map based of a list of action name/action set pairs.
     * @param actions a list of action name/action set pairs
     * @return an action map containing the given actions
     */
    @SafeVarargs
    public static ActionMap of(Pair<String, ActionSet>... actions) {
        ActionMap actionMap = new ActionMap();
        for (Pair<String, ActionSet> action : actions) {
            actionMap.addActions(action.getFirst(), action.getSecond());
        }
        return actionMap;
    }

    /**
     * Adds an action to the action map.
     * @param actionName the name of the action
     * @param actionCodes the user interactions that can trigger the action
     */
    public void addActions(String actionName, Action... actionCodes) {
        if (!hasAction(actionName)) {
            actionMap.put(actionName, new ActionSet());
        }
        for (Action action : actionCodes) {
            actionMap.get(actionName).addAction(action);
        }
    }

    /**
     * Adds an action to the action map.
     * @param actionName the name of the action
     * @param actionSet the user interactions that can trigger the action as an action set
     */
    public void addActions(String actionName, ActionSet actionSet) {
        if (!hasAction(actionName)) {
            actionMap.put(actionName, new ActionSet());
        }
        for (Action action : actionSet.getActions()) {
            actionMap.get(actionName).addAction(action);
        }
    }

    /**
     * Gets the action set associated with the given action name.
     * @param actionName the name of the action
     * @return the action set associated with the given action name
     */
    public ActionSet getActions(String actionName) {
        return actionMap.get(actionName);
    }

    /**
     * Checks if the given action is active in the given context.
     * @param actionName the name of the action
     * @param context the context in which to check if the action is active
     * @return true if the action is active in the given context, false otherwise
     */
    public boolean isActive(String actionName, ActionContext context) {
        return hasAction(actionName) && getActions(actionName).verify(context);
    }

    /**
     * Checks if the action name is being used in the action map.
     * @param actionName the name of the action to check
     * @return true if the action name is being used in the action map, false otherwise
     */
    public boolean hasAction(String actionName) {
        return actionMap.containsKey(actionName);
    }

    /**
     * Removes the action with the given name from the action map.
     * @param actionName the name of the action to remove
     */
    public void removeAction(String actionName) {
        actionMap.remove(actionName);
    }

    /**
     * Removes all actions from the action map.
     */
    public void clear() {
        actionMap.clear();
    }

    /**
     * Merges two ActionMaps together.
     * @param other the ActionMap to merge into this ActionMap
     */
    public void addActions(ActionMap other) {
        for (Map.Entry<String, ActionSet> entry : other.actionMap.entrySet()) {
            addActions(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns an iterator over the action names in the action map.
     * @return an iterator over the action names (the keys) in the action map
     */
    @Override
    public Iterator<String> iterator() {
        return actionMap.keySet().iterator();
    }
}
