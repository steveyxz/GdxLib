package me.partlysunny.gdxlib.flappyBird.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.flappyBird.GameState;
import me.partlysunny.gdxlib.flappyBird.components.UiComponent;

public class UiUpdater extends IteratingSystem {
    public UiUpdater() {
        super(Family.all(UiComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        UiComponent uiComponent = Mappers.get(UiComponent.class, entity);
        uiComponent.getTapToStartImage().setVisible(!GameState.isGameStarted);
        uiComponent.getGameOverImage().setVisible(GameState.isGameOver);
        uiComponent.getScoreLabel().setText(String.valueOf(GameState.score));
    }
}
