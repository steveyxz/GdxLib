package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.ashley.core.Component;

public class VisibilityComponent implements Component {

    private boolean visible;

    public VisibilityComponent(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
