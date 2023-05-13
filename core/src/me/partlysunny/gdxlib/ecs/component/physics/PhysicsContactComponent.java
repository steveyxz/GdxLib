package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Pool;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PhysicsContactComponent implements Component, Pool.Poolable {

    @NotNull
    private Consumer<Contact> contactEnter = c -> {
    };
    @NotNull
    private Consumer<Contact> contactExit = c -> {
    };
    @NotNull
    private Consumer<Contact> contactPreSolve = c -> {
    };
    @NotNull
    private Consumer<Contact> contactPostSolve = c -> {
    };

    @NotNull
    public Consumer<Contact> getContactEnter() {
        return contactEnter;
    }

    public void setContactEnter(@NotNull Consumer<Contact> contactEnter) {
        this.contactEnter = contactEnter;
    }

    @NotNull
    public Consumer<Contact> getContactExit() {
        return contactExit;
    }

    public void setContactExit(@NotNull Consumer<Contact> contactExit) {
        this.contactExit = contactExit;
    }

    @NotNull
    public Consumer<Contact> getContactPreSolve() {
        return contactPreSolve;
    }

    public void setContactPreSolve(@NotNull Consumer<Contact> contactPreSolve) {
        this.contactPreSolve = contactPreSolve;
    }

    @NotNull
    public Consumer<Contact> getContactPostSolve() {
        return contactPostSolve;
    }

    public void setContactPostSolve(@NotNull Consumer<Contact> contactPostSolve) {
        this.contactPostSolve = contactPostSolve;
    }

    @Override
    public void reset() {
        contactEnter = c -> {
        };
        contactExit = c -> {
        };
        contactPreSolve = c -> {
        };
        contactPostSolve = c -> {
        };
    }
}
