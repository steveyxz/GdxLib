package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.*;
import me.partlysunny.gdxlib.ecs.component.Mappers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ContactListener extends IteratingSystem implements com.badlogic.gdx.physics.box2d.ContactListener {

    private final Queue<ContactInfo> contactQueue = new ConcurrentLinkedQueue<>();

    public ContactListener(World physicsWorld) {
        super(Family.all(PhysicsContactComponent.class, Box2DPhysicsComponent.class).get());
        physicsWorld.setContactListener(this);
    }

    private static void processContactWithBody(PhysicsContactComponent contactComponent, Box2DPhysicsComponent physicsComponent, ContactInfo contact, Body otherBody) {
        if (physicsComponent.getLinkedBody() == otherBody) {
            switch (contact.getType()) {
                case BEGIN:
                    contactComponent.getContactEnter().accept(contact.getContact());
                    break;
                case END:
                    contactComponent.getContactExit().accept(contact.getContact());
                    break;
                case PRE_SOLVE:
                    contactComponent.getContactPreSolve().accept(contact.getContact());
                    break;
                case POST_SOLVE:
                    contactComponent.getContactPostSolve().accept(contact.getContact());
                    break;
            }
        }
    }

    @Override
    public void beginContact(Contact contact) {
        contactQueue.add(new ContactInfo(contact, ContactInfo.ContactType.BEGIN));
    }

    @Override
    public void endContact(Contact contact) {
        contactQueue.remove(new ContactInfo(contact, ContactInfo.ContactType.END));
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        contactQueue.add(new ContactInfo(contact, ContactInfo.ContactType.PRE_SOLVE));
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        contactQueue.add(new ContactInfo(contact, ContactInfo.ContactType.POST_SOLVE));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsContactComponent contactComponent = Mappers.get(PhysicsContactComponent.class, entity);
        Box2DPhysicsComponent physicsComponent = Mappers.get(Box2DPhysicsComponent.class, entity);
        while (!contactQueue.isEmpty()) {
            ContactInfo contact = contactQueue.poll();
            processContactWithBody(contactComponent, physicsComponent, contact, contact.getContact().getFixtureA().getBody());
            processContactWithBody(contactComponent, physicsComponent, contact, contact.getContact().getFixtureB().getBody());
            ;
        }
    }

    private static final class ContactInfo {

        private final Contact contact;
        private final ContactType type;

        private ContactInfo(Contact contact, ContactType type) {
            this.contact = contact;
            this.type = type;
        }

        public Contact getContact() {
            return contact;
        }

        public ContactType getType() {
            return type;
        }

        enum ContactType {
            BEGIN,
            END,
            PRE_SOLVE,
            POST_SOLVE
        }
    }
}
