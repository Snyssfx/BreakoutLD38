package com.snyssfx.breakout.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.snyssfx.breakout.Level;

/**
 * Created by Snyss on 4/23/2017.
 */
public class PlayerRed extends Player{
    public static final String TAG = PlayerRed.class.getName();

    public PlayerRed(Vector2 locPos, Level pLevel){
        super(locPos, pLevel, Color.RED);
        Gdx.app.log(TAG, getGlobalPosition() + "");
    }

    @Override
    public void updateMove(){
        move = Player.Move.NONE;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            move = Player.Move.UP;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            move = Player.Move.DOWN;
    }
}
