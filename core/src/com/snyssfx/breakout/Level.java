package com.snyssfx.breakout;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.snyssfx.breakout.Ball.BallBlue;
import com.snyssfx.breakout.Ball.BallRed;
import com.snyssfx.breakout.Player.PlayerBlue;
import com.snyssfx.breakout.Player.PlayerRed;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Level implements Disposable {

    public static final String TAG = Level.class.getName();

    public World b2world;
    public Vector2 position;
    public Vector2 size;
    public Color color;
    public PlayerBlue playerBlue;
    public PlayerRed playerRed;
    public BallBlue ballBlue;
    public BallRed ballRed;
    public Array<Block> blocks;
    public Array<Bound> bounds;
    public Pixmap pixmap;
    public Texture tex;
    public Box2DDebugRenderer renderer;

    public Level(Vector2 pos, String levelName, Vector2 size, Color color){
        position = pos;
        this.size = new Vector2(size);
        b2world = new World(Vector2.Zero, false);
        b2world.setContactListener(new NewContactListener());
        blocks = new Array<Block>();
        playerBlue = new PlayerBlue(size.cpy().scl(-0.5f).add(Constants.BLOCKSIZE).add(0, size.y / 2), this);
        playerRed = new PlayerRed(size.cpy().scl(0.5f).sub(Constants.BLOCKSIZE).sub(0, size.y / 2), this);
        ballBlue = new BallBlue(size.cpy().scl(-0.5f).add(Constants.BLOCKSIZE.scl(3.0f)), this);

        blocks.add(new Block(Color.GREEN, 0, 0, this, Vector2.Zero));
        makeBound();
        //pos.scl(size);
        pixmap = new Pixmap(
                (int) ((size.x) * Constants.PXPERMETER),
                (int) ((size.y) * Constants.PXPERMETER),
                Pixmap.Format.RGBA8888
        );
        this.color = color;
        pixmap.setColor(color);
        pixmap.fill();
        tex = new Texture(pixmap);

        //Init(levelName);

    }

    public void Init(String levelName){

        //create level from picture
        Pixmap pixmap = new Pixmap(Gdx.files.internal(levelName));
        Vector2 pictureSize = new Vector2(pixmap.getWidth(), pixmap.getHeight());
        for(int y = 0; y < pictureSize.y; y++)
            for (int x = 0; x < pictureSize.x; x++){
                int curPixel = pixmap.getPixel(x, y);
                Color curColor = null;
                Color.rgba8888ToColor(curColor, curPixel);

                //yellow = blocks, red = playerRed, blue = playerBlue

            }
    }

    public void makeBound(){
        bounds = new Array<Bound>();
        bounds.add(new Bound(Vector2.Zero.cpy().sub(0, size.y / 2), this, new Vector2(size.x, Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(0, size.y / 2), this, new Vector2(size.x, Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().sub(size.x / 2, 0), this, new Vector2(Constants.EPS, size.y)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(size.x / 2, 0), this, new Vector2(Constants.EPS, size.y)));
    }

    public void Render(SpriteBatch batch){
        batch.draw(tex,
                position.x - tex.getWidth() / 2 * Constants.METERPERPX,
                position.y - tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() / 2 * Constants.METERPERPX,
                tex.getHeight() / 2 * Constants.METERPERPX,
                tex.getWidth() * Constants.METERPERPX,
                tex.getHeight() * Constants.METERPERPX,
                1, 1,
                0,
                0, 0,
                (int) tex.getWidth(),
                (int) tex.getHeight(),
                false,
                false
        );
        for (Block b: blocks)
            b.render(batch);
        for (Bound b: bounds)
            b.render(batch);
        playerBlue.render(batch);
        playerRed.render(batch);
        ballBlue.render(batch);
        //ballRed.render(batch);
    }

    public void Update(float delta){
        b2world.step(Gdx.graphics.getDeltaTime(), 8, 6);
        playerBlue.Update(delta);
        playerRed.Update(delta);
        ballBlue.Update(delta);
//        ballRed.Update(delta);
    }

    @Override
    public void dispose(){
        b2world.dispose();
    }

}
