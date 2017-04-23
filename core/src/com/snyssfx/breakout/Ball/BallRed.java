package com.snyssfx.breakout.Ball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.snyssfx.breakout.Constants;
import com.snyssfx.breakout.Level;

/**
 * Created by Snyss on 4/23/2017.
 */
public class BallRed extends Ball{
    public static final String TAG = BallRed.class.getName();

    public BallRed(Vector2 locPos, Level pLevel){
        super(locPos, pLevel, Color.RED);
        body.applyForceToCenter(-Constants.BALL_SPEED, 0, true);
    }
}
