package com.snyssfx.breakout;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Snyss on 4/23/2017.
 */
public class NewContactListener implements ContactListener {
    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void preSolve (Contact contact, Manifold oldManifold){

    }

    @Override
    public void postSolve (Contact contact, ContactImpulse impulse){

        Object bA = contact.getFixtureA().getBody().getUserData();
        if (bA != null && bA instanceof Block)
            ((Block)bA).setActive(false);

        Object bB = contact.getFixtureB().getBody().getUserData();
        if (bB != null && bB instanceof Block)
            ((Block)bB).setActive(false);
    }
}
