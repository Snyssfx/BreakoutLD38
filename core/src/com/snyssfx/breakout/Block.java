package com.snyssfx.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Snyss on 4/23/2017.
 */

public class Block {
    public static final String TAG = Block.class.getName();

    public int color;
    public Vector2 position;
    public Body body;
    public Pixmap pixmap;
    public Texture tex;
    public boolean isActive;

    public Block(int color, int x, int y, BodyDef.BodyType type, World b2world) {
        isActive = true;
        {//screen part
            this.color = color;
            position = new Vector2(x, y);
            position.scl(Constants.BLOCKSIZE);
            pixmap = new Pixmap(
                    (int) ((Constants.BLOCKSIZE.x) * Constants.PXPERMETER),
                    (int) ((Constants.BLOCKSIZE.y) * Constants.PXPERMETER),
                    Pixmap.Format.RGBA8888
            );
            pixmap.setColor(color);
            pixmap.fill();
            tex = new Texture(pixmap);
        }

        {//physics part
            BodyDef def = new BodyDef();
            def.type = type;
            def.position.set(position);
            body = b2world.createBody(def);
            {
                FixtureDef fdef = new FixtureDef();
                fdef.density = Constants.DENSITY;
                PolygonShape polygonShape = new PolygonShape();
                polygonShape.setAsBox(
                        Constants.BLOCKSIZE.x / 2 - Constants.EPS,
                        Constants.BLOCKSIZE.y / 2 - Constants.EPS
                );
                fdef.shape = polygonShape;
                body.createFixture(fdef);
                polygonShape.dispose();
                body.setUserData(this);
            }
        }

    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
        if (!isActive) {
            color = Color.rgba8888(Color.GRAY);
            pixmap.setColor(color);
            pixmap.fill();
            tex = new Texture(pixmap);
        }
    }

    public void render(SpriteBatch render) {
        render.draw(tex,
                body.getPosition().x - tex.getWidth() / 2 * Constants.METERPERPX,
                body.getPosition().y - tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() / 2 * Constants.METERPERPX,
                tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() * Constants.METERPERPX,
                tex.getHeight() * Constants.METERPERPX,
                1, 1,
                body.getAngle() * MathUtils.radiansToDegrees,
                0, 0,
                (int) (Constants.BLOCKSIZE.x * Constants.PXPERMETER),
                (int) (Constants.BLOCKSIZE.y * Constants.PXPERMETER),
                false,
                false
        );
    }
}
