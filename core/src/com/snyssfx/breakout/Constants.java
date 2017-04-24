package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Snyss on 4/23/2017.
 */

public class Constants {
    public static final Vector2 BLOCKSIZE = new Vector2(0.5f, 1.0f);
    public static final Color COLOR_BLOCK = Color.BROWN;
    public static final Color COLOR_LEVEL = Color.WHITE;
    public static final Color COLOR_BOUNDS = new Color(0, 0, 0, 0);

    public static final float DENSITY = 1 / (BLOCKSIZE.x * 10);
    public static final float PLAYER_VELOCITY = 1000.0f * BLOCKSIZE.x;
    public static final Vector2 PLAYER_SIZE_ON_START = new Vector2(BLOCKSIZE.x, BLOCKSIZE.x * 4);
    public static final Vector2 BALL_SIZE = new Vector2(BLOCKSIZE.x, BLOCKSIZE.x);
    public static final float BALL_SPEED = 40.0f * BLOCKSIZE.x;

    public static final Vector2 LEVEL_1_SIZE = new Vector2(70 * BLOCKSIZE.x, 8 * BLOCKSIZE.y);
    public static final Vector2 LEVEL_2_SIZE = new Vector2(55 * BLOCKSIZE.x, 7 * BLOCKSIZE.y);
    public static final Vector2 LEVEL_3_SIZE = new Vector2(40 * BLOCKSIZE.x, 5 * BLOCKSIZE.y);
    public static final Vector2 LEVEL_4_SIZE = new Vector2(25 * BLOCKSIZE.x, 3 * BLOCKSIZE.y);
    public static final Vector2 LEVEL_5_SIZE = new Vector2(8 * BLOCKSIZE.x, 2 * BLOCKSIZE.y);

    public static final Vector2 LEVEL_1_POS = new Vector2(0, 20.0f * BLOCKSIZE.x);
    public static final Vector2 LEVEL_2_POS = new Vector2(0, 10.0f * BLOCKSIZE.x);
    public static final Vector2 LEVEL_3_POS = new Vector2(0, 0.0f * BLOCKSIZE.x);
    public static final Vector2 LEVEL_4_POS = new Vector2(0, -18.0f * BLOCKSIZE.x);
    public static final Vector2 LEVEL_5_POS = new Vector2(0, -30.0f * BLOCKSIZE.x);

    public static final float PXPERMETER = 10 / BLOCKSIZE.x;
    public static final float METERPERPX = 1f / PXPERMETER;

    public static final float EPS = BLOCKSIZE.x / 50f;

    public static final Vector2 VIEWPORT = new Vector2(800 * METERPERPX, 600 * METERPERPX);
    //public static final Vector2 GRAVITY = new Vector2(0, 0);
}
