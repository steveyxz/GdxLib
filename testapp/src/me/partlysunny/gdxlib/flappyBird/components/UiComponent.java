package me.partlysunny.gdxlib.flappyBird.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;

public class UiComponent implements Component, Pool.Poolable {

    private VisLabel scoreLabel;
    private VisImage gameOverImage;
    private VisImage tapToStartImage;

    public UiComponent() {}

    public VisLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(VisLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public VisImage getGameOverImage() {
        return gameOverImage;
    }

    public void setGameOverImage(VisImage gameOverImage) {
        this.gameOverImage = gameOverImage;
    }

    public VisImage getTapToStartImage() {
        return tapToStartImage;
    }

    public void setTapToStartImage(VisImage tapToStartImage) {
        this.tapToStartImage = tapToStartImage;
    }

    @Override
    public void reset() {
        scoreLabel = null;
        gameOverImage = null;
        tapToStartImage = null;
    }
}
