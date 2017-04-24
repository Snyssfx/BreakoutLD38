package com.snyssfx.breakout.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.snyssfx.breakout.Constants;
import com.snyssfx.breakout.Level;
import com.snyssfx.breakout.ParentRectangle;

/**
 * Created by Snyss on 4/23/2017.
 */
public abstract class Ball extends ParentRectangle {
    public static final String TAG = Ball.class.getName();

    public Ball(Vector2 locPos, Level pLevel, Color color){
        super(locPos, pLevel, Constants.BALL_SIZE, color, BodyDef.BodyType.DynamicBody);
        body.setBullet(true);
        //Gdx.app.debug(TAG,);
    }

    public void Update(float delta){
        localPosition = body.getPosition();
    }

}
