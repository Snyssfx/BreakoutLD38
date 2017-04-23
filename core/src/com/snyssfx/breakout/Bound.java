package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Bound extends ParentRectangle{
    public static final String TAG = Bound.class.getName();

    public Bound(Vector2 locPos, Level pLevel, Vector2 size){
        super(locPos, pLevel, size, new Color(0, 0, 0, 0), BodyDef.BodyType.StaticBody);
    }
}
