package com.snyssfx.breakout.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.snyssfx.breakout.Constants;
import com.snyssfx.breakout.Level;
import com.snyssfx.breakout.ParentRectangle;

/**
 * Created by Snyss on 4/23/2017.
 */
public abstract class Player extends ParentRectangle {
    public static final String TAG = Player.class.getName();

    public enum Move{
        UP,
        DOWN,
        NONE
    }
    public Move move;

    public Player(Vector2 locPos, Level pLevel, Color color){
        super(locPos, pLevel, Constants.PLAYER_SIZE_ON_START, color, BodyDef.BodyType.KinematicBody);
        move = Move.NONE;
        body.getFixtureList().get(0).setRestitution(1.0f);
    }

    public abstract void updateMove();

    public void Update(float delta){
        updateMove();
        if (move == Move.NONE)
            body.setLinearVelocity(Vector2.Zero);
        if (move == Move.UP)
            body.setLinearVelocity(0, Constants.PLAYER_VELOCITY * delta);
        if (move == Move.DOWN)
            body.setLinearVelocity(0, -Constants.PLAYER_VELOCITY * delta);
        localPosition = body.getPosition();
    }


}
