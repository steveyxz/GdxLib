package me.partlysunny.gdxlib.control.action;

import com.badlogic.gdx.Gdx;

public class GdxContext implements ActionContext {
    @Override
    public boolean isKeyDown(int keyCode) {
        return Gdx.input.isKeyPressed(keyCode);
    }

    @Override
    public boolean isMouseButtonDown(int button) {
        return Gdx.input.isButtonPressed(button);
    }
}
