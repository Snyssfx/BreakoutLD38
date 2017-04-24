package com.snyssfx.breakout.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.snyssfx.breakout.Constants;
import com.snyssfx.breakout.Level;
import com.snyssfx.breakout.ParentRectangle;

/**
 * Created by Snyss on 4/23/2017.
 */
public abstract class Ball extends ParentRectangle {
    public static final String TAG = Ball.class.getName();

    public Ball(Vector2 locPos, Level pLevel, Color color){
        super(locPos, pLevel, Constants.BALL_SIZE, color, BodyDef.BodyType.DynamicBody);
        body.setBullet(true);
        //Gdx.app.debug(TAG,);
    }

    public void Update(float delta){
        localPosition = body.getPosition();
    }

    @Override
    public PolygonShape createShape(){
        PolygonShape polygonShape = new PolygonShape();
        Vector2[] vertices = new Vector2[8];
        vertices[0] = new Vector2( screenSize.x / 4, -screenSize.y / 2);
        vertices[1] = new Vector2(-screenSize.x / 4, -screenSize.y / 2);
        vertices[2] = new Vector2(-screenSize.x / 2, -screenSize.y / 4);
        vertices[3] = new Vector2(-screenSize.x / 2, screenSize.y / 4);

        vertices[4] = new Vector2(-screenSize.x / 4, screenSize.y / 2);
        vertices[5] = new Vector2(screenSize.x / 4, screenSize.y / 2);
        vertices[6] = new Vector2(screenSize.x / 2, screenSize.y / 4);
        vertices[7] = new Vector2(-screenSize.x / 2, -screenSize.y / 4);

        polygonShape.set(vertices);
        return polygonShape;
    }

}
