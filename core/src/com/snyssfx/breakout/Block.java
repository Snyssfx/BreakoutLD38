package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Snyss on 4/23/2017.
 */

public class Block extends ParentRectangle {
    public static final String TAG = Block.class.getName();

    public boolean isActive;

    public Block(Color color, int x, int y
            , World b2world
            , Level pLevel
            , Vector2 localPosition)
    {
        super(localPosition, pLevel, Constants.BLOCKSIZE, color, BodyDef.BodyType.DynamicBody);
        isActive = true;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (!isActive) {
            color = Color.GRAY;
            pixmap.setColor(color);
            pixmap.fill();
            tex = new Texture(pixmap);
            parentLevel.b2world.destroyBody(body);
        }
    }
}
