package com.snyssfx.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.snyssfx.breakout.Ball.BallBlue;
import com.snyssfx.breakout.Ball.BallRed;
import com.snyssfx.breakout.Player.PlayerBlue;
import com.snyssfx.breakout.Player.PlayerRed;

/**
 * Created by Snyss on 4/23/2017.
 */
public class Level implements Disposable {

    public static final String TAG = Level.class.getName();

    public World b2world;
    public Vector2 position;
    public Color color;
    public PlayerBlue playerBlue;
    public PlayerRed playerRed;
    public BallBlue ballBlue;
    public BallRed ballRed;
    public Array<Block> blocks;

    public Level(Vector2 pos, String levelName, Vector2 size){
        position = pos;
        b2world = new World(Vector2.Zero, false);
        b2world.setContactListener(new NewContactListener());
        blocks = new Array<Block>();
        playerBlue = new PlayerBlue(Constants.BLOCKSIZE, this);

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

    public void Render(SpriteBatch batch){
        //for (Block b: blocks)
        //    b.render(batch);
        playerBlue.render(batch);
        //playerRed.render(batch);
        //ballBlue.render(batch);
        //ballRed.render(batch);
    }

    public void Update(float delta){
        b2world.step(Gdx.graphics.getDeltaTime(), 8, 6);
        playerBlue.Update(delta);
//        playerRed.Update(delta);
//        ballBlue.Update(delta);
//        ballRed.Update(delta);
    }

    @Override
    public void dispose(){
        b2world.dispose();
    }

}
