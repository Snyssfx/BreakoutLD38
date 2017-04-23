package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.snyssfx.breakout.Ball.BallBlue;
import com.snyssfx.breakout.Ball.BallRed;
import com.snyssfx.breakout.Player.PlayerBlue;
import com.snyssfx.breakout.Player.PlayerRed;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Level implements Disposable {

    public static final String TAG = Level.class.getName();

    public World b2world;
    public Vector2 position;
    public Color color;
    public PlayerBlue playerBlue;
    public PlayerRed playerRed;
    public BallBlue ballBlue;
    public BallRed ballRed;

    public Level(Vector2 pos, String levelName){
        b2world = new World(Vector2.Zero, false);
        b2world.setContactListener(new NewContactListener());
    }

    @Override
    public void dispose(){
        b2world = null;
    }

}
