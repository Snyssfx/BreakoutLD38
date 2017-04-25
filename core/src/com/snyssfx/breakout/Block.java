package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Snyss on 4/23/2017.
 */

public class Block extends ParentRectangle {
    public static final String TAG = Block.class.getName();

    public boolean isActive;

    public Block(Color color, Level pLevel, Vector2 localPosition)
    {
        super(localPosition, pLevel, Constants.BLOCKSIZE, color, BodyDef.BodyType.KinematicBody);
        isActive = true;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (!isActive) {
            color = Color.GRAY;
            pixmap.setColor(color);
            pixmap.fill();
            tex = new Texture(pixmap);
            body.getFixtureList().get(0).setSensor(true);
            //body.setActive(false);
            //parentLevel.b2world.destroyBody(body);
        }
    }
//
//    @Override
//    public void render(SpriteBatch render){
//        super.render(render);
//
//    }
}
