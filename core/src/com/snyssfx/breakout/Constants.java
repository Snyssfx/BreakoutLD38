package com.snyssfx.breakout;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Constants {
    public static final Vector2 BLOCKSIZE = new Vector2(0.05f, 0.1f);
    public static final float DENSITY = 1 / (BLOCKSIZE.x * 10);
    public static final float PXPERMETER = 10 / BLOCKSIZE.x;
    public static final float METERPERPX = 1f / PXPERMETER;

    public static final float EPS = BLOCKSIZE.x / 10f;

    public static final Vector2 VIEWPORT = new Vector2(800 * METERPERPX, 600 * METERPERPX);
    public static final Vector2 GRAVITY = new Vector2(0, 0);
}
