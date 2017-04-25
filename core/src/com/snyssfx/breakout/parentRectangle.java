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
import com.badlogic.gdx.physics.box2d.PolygonShape;


/**
 * Created by Snyss on 4/23/2017.
 */
public class ParentRectangle {

    public static final String TAG = ParentRectangle.class.getName();

    public Vector2 localPosition;
    //public Vector2 globalPosition;
    public Level parentLevel;
    public Color color;
    public Vector2 screenSize;
    public Body body;
    public Pixmap pixmap;
    public Texture tex;

    public ParentRectangle(Vector2 locPos
            , Level pLevel
            , Vector2 screenSize
            , Color color
            , BodyDef.BodyType type)
    {
        this.localPosition = new Vector2(locPos);
        this.parentLevel = pLevel;
        this.color = color;
        this.screenSize = new Vector2(screenSize);

        {//screen part
            pixmap = new Pixmap(
                    (int) ((screenSize.x) * Constants.PXPERMETER),
                    (int) ((screenSize.y) * Constants.PXPERMETER),
                    Pixmap.Format.RGBA8888
            );
            pixmap.setColor(color);
            pixmap.fill();
            tex = new Texture(pixmap);
        }

        {//physics part
            BodyDef def = new BodyDef();
            def.type = type;
            def.position.set(localPosition);
            body = parentLevel.b2world.createBody(def);
            body.setFixedRotation(true);
            {
                FixtureDef fdef = new FixtureDef();
                fdef.density = Constants.DENSITY;
                fdef.restitution = 1.0f;
                PolygonShape polygonShape = createShape();
                fdef.shape = polygonShape;
                body.createFixture(fdef);
                polygonShape.dispose();
                body.setUserData(this);
            }
        }
    }

    public PolygonShape createShape(){
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(
                screenSize.x / 2 - Constants.EPS,
                screenSize.y / 2 - Constants.EPS
        );
        return polygonShape;
    }

//    //public final Vector2 getLocalPosition(){
//        return localPosition;
//    }
    public final Vector2 getGlobalPosition(){
        return localPosition.cpy().add(parentLevel.position);
    }

    public void Update(float delta){
        localPosition = body.getPosition();
    }

    public void render(SpriteBatch render) {
        render.draw(tex,
                parentLevel.position.x + localPosition.x - tex.getWidth() / 2 * Constants.METERPERPX,
                parentLevel.position.y + localPosition.y - tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() / 2 * Constants.METERPERPX,
                tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() * Constants.METERPERPX,
                tex.getHeight() * Constants.METERPERPX,
                1, 1,
                0,
                0, 0,
                (int) (screenSize.x * Constants.PXPERMETER),
                (int) (screenSize.y * Constants.PXPERMETER),
                false,
                false
        );
    }

}
