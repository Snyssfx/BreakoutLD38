package com.snyssfx.breakout;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Constants {
    public static final Vector2 BLOCKSIZE = new Vector2(0.05f, 0.1f);
    public static final float DENSITY = 1 / (BLOCKSIZE.x * 10);
    public static final float PLAYER_VELOCITY = 100.0f;
    public static final Vector2 PLAYER_SIZE_ON_START = new Vector2(0.05f, 0.2f);
    public static final Vector2 BALL_SIZE = new Vector2(0.05f, 0.05f);
    public static final float BALL_SPEED = 40.0f;

    public static final Vector2 LEVEL_1_SIZE = new Vector2(3.5f, 0.6f);

    public static final float PXPERMETER = 10 / BLOCKSIZE.x;
    public static final float METERPERPX = 1f / PXPERMETER;

    public static final float EPS = BLOCKSIZE.x / 50f;

    public static final Vector2 VIEWPORT = new Vector2(800 * METERPERPX, 600 * METERPERPX);
    //public static final Vector2 GRAVITY = new Vector2(0, 0);
}
