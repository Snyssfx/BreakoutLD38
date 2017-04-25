package com.snyssfx.breakout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.snyssfx.breakout.Ball.Ball;
import com.snyssfx.breakout.Ball.BallBlue;
import com.snyssfx.breakout.Ball.BallRed;

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
        if (bA != null && bA instanceof Bound && ((Bound) bA).screenSize.x <= 12 * Constants.EPS) {
            //((Ball)bB).reset();
//            ((Ball) bB).parentLevel.b2world.destroyBody(((Ball) bB).body);
//            if (bB instanceof BallBlue) {
//                ((Ball) bB).parentLevel.ballBlue = new BallBlue(Vector2.Zero.cpy(), ((Ball) bB).parentLevel);
//            }
//            if (bB instanceof BallRed) {
//                ((Ball) bB).parentLevel.ballRed = new BallRed(Vector2.Zero.cpy(), ((Ball) bB).parentLevel);
//            }
        }
        if (bB != null && bB instanceof Bound && ((Bound) bB).screenSize.x <= 12 * Constants.EPS){
            //((Ball)bA).reset();
//            ((Ball) bA).parentLevel.b2world.destroyBody(((Ball) bA).body);
//            if (bA instanceof BallBlue) {
//                ((Ball) bA).parentLevel.ballBlue = new BallBlue(Vector2.Zero.cpy(), ((Ball) bA).parentLevel);
//            }
//            if (bA instanceof BallRed) {
//                ((Ball) bA).parentLevel.ballRed = new BallRed(Vector2.Zero.cpy(), ((Ball) bA).parentLevel);
//            }
        }
    }
}
