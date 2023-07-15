package me.partlysunny.gdxlib;

import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.ScreenTransition;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;
import me.partlysunny.gdxlib.flappyBird.FlappyBirdScene;
import me.partlysunny.gdxlib.testOne.TestOneScene;

public class MainGame extends GdxGame {
    @Override
    public void initializeScreens(ScreenManager<ManagedScreen, ScreenTransition> screenManager) {
        screenManager.addScreen("test1", new TestOneScene(this));
        screenManager.addScreen("flappybird", new FlappyBirdScene(this));
    }

    @Override
    public void initializeTransitions(ScreenManager<ManagedScreen, ScreenTransition> transitionFactory) {
        screenManager.addScreenTransition("fade", new BlendingTransition(batchSet.getSpriteBatch(), 1));
    }

    @Override
    protected String getInitialScreen() {
        return "flappybird";
    }

    @Override
    protected String getInitialTransition() {
        return "fade";
    }
}
