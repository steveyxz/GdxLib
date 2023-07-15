package me.partlysunny.gdxlib.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;
import java.util.Map;

public class Mappers {

    private static final Map<Class<? extends Component>, ComponentMapper<? extends Component>> mappers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Component> ComponentMapper<T> getComponentMapper(Class<T> componentClass) {
        ComponentMapper<T> mapper = (ComponentMapper<T>) mappers.get(componentClass);
        if (mapper == null) {
            mapper = ComponentMapper.getFor(componentClass);
            mappers.put(componentClass, mapper);
        }
        return mapper;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> ComponentMapper<T> getComponentMapper(T component) {
        return (ComponentMapper<T>) getComponentMapper(component.getClass());
    }

    public static <T extends Component> T get(Class<T> clazz, Entity entity) {
        return getComponentMapper(clazz).get(entity);
    }

    public static <T extends Component> boolean has(Class<T> clazz, Entity entity) {
        return getComponentMapper(clazz).has(entity);
    }
}
