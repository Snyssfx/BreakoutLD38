package com.snyssfx.breakout.Ball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.snyssfx.breakout.Constants;
import com.snyssfx.breakout.Level;

/**
 * Created by Snyss on 4/23/2017.
 */
public class BallBlue extends Ball{
    public static final String TAG = BallBlue.class.getName();

    public BallBlue(Vector2 locPos, Level pLevel){
        super(locPos, pLevel, Color.BLUE);
        body.applyForceToCenter(Constants.BALL_SPEED, 0, true);
    }

    @Override
    public void reset(){
        //body.setTransform(parentLevel.playerBlue.localPosition.cpy().add(Constants.BLOCKSIZE.x * 2, 0), 0);
        body.applyForceToCenter(Constants.BALL_SPEED, 0, true);
    }
}
