package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.utils.Disposable;

public interface Resource<T> extends Disposable {
    T getInternal();
    Class<T> getType();
}
