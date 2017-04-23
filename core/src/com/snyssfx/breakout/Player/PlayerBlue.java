package com.snyssfx.breakout.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.snyssfx.breakout.Level;

/**
 * Created by Snyss on 4/23/2017.
 */
public class PlayerBlue extends Player {
    public static final String TAG = PlayerBlue.class.getName();

    public PlayerBlue(Vector2 locPos, Level pLevel){
        super(locPos, pLevel, Color.BLUE);
    }

    @Override
    public void updateMove(){
        move = Move.NONE;
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            move = Move.UP;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            move = Move.DOWN;
    }
}
