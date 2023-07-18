package me.partlysunny.gdxlib.control.action;

import com.badlogic.gdx.Gdx;

/**
 * Action context implementation for LibGDX
 */
public class GdxContext implements ActionContext {
    @Override
    public boolean isKeyDown(int keyCode) {
        return Gdx.input.isKeyPressed(keyCode);
    }

    @Override
    public boolean isMouseButtonDown(int button) {
        return Gdx.input.isButtonPressed(button);
    }

    @Override
    public boolean isMouseButtonJustDown(int button) {
        return Gdx.input.isButtonJustPressed(button);
    }

    @Override
    public boolean isKeyButtonJustDown(int keyCode) {
        return Gdx.input.isKeyJustPressed(keyCode);
    }
}
