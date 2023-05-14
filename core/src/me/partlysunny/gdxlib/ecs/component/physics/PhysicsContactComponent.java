package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Pool;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PhysicsContactComponent implements Component, Pool.Poolable {

    @NotNull
    private BiConsumer<Contact, Entity> contactEnter = (contact, entity) -> {
    };
    @NotNull
    private BiConsumer<Contact, Entity> contactExit = (contact, entity) -> {
    };
    @NotNull
    private BiConsumer<Contact, Entity> contactPreSolve = (contact, entity) -> {
    };
    @NotNull
    private BiConsumer<Contact, Entity> contactPostSolve = (contact, entity) -> {
    };

    @NotNull
    public BiConsumer<Contact, Entity> getContactEnter() {
        return contactEnter;
    }

    public void setContactEnter(@NotNull BiConsumer<Contact, Entity> contactEnter) {
        this.contactEnter = contactEnter;
    }

    @NotNull
    public BiConsumer<Contact, Entity> getContactExit() {
        return contactExit;
    }

    public void setContactExit(@NotNull BiConsumer<Contact, Entity> contactExit) {
        this.contactExit = contactExit;
    }

    @NotNull
    public BiConsumer<Contact, Entity> getContactPreSolve() {
        return contactPreSolve;
    }

    public void setContactPreSolve(@NotNull BiConsumer<Contact, Entity> contactPreSolve) {
        this.contactPreSolve = contactPreSolve;
    }

    @NotNull
    public BiConsumer<Contact, Entity> getContactPostSolve() {
        return contactPostSolve;
    }

    public void setContactPostSolve(@NotNull BiConsumer<Contact, Entity> contactPostSolve) {
        this.contactPostSolve = contactPostSolve;
    }

    @Override
    public void reset() {
        contactEnter = (contact, entity) -> {
        };
        contactExit = (contact, entity) -> {
        };
        contactPreSolve = (contact, entity) -> {
        };
        contactPostSolve = (contact, entity) -> {
        };
    }
}
