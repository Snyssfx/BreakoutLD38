package com.snyssfx.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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
    public String levelName;

    public Level(Vector2 pos, String levelName, Vector2 size, Color color){
        position = pos.cpy();
        this.size = size.cpy();
        this.levelName = levelName;
        this.color = color;
    }

    public void Init(){
        if (b2world != null) b2world.dispose();
        b2world = new World(Vector2.Zero.cpy(), false);
        b2world.setContactListener(new NewContactListener());
        blocks = new Array<Block>();

        playerBlue = new PlayerBlue(size.cpy().scl(-0.5f).add(0, size.y / 2), this);
        playerRed = new PlayerRed(size.cpy().scl(0.5f).sub(0, size.y / 2), this);
        ballBlue = new BallBlue(new Vector2(-0.05f,0)/*size.cpy().scl(-0.5f).add(Constants.BLOCKSIZE.x * 3, 0)*/, this);
        ballRed = new BallRed(new Vector2(0.05f, 0)/*new Vector2(size.x / 2, size.y / 2).sub(Constants.BLOCKSIZE.x * 3, 0)*/, this);

        //blocks.add(new Block(Color.GREEN, this, Vector2.Zero.cpy()));
        makeBound();
        pixmap = new Pixmap(
                (int) ((size.x) * Constants.PXPERMETER),
                (int) ((size.y) * Constants.PXPERMETER),
                Pixmap.Format.RGBA8888
        );
        this.color = color;
        pixmap.setColor(color);
        pixmap.fill();
        tex = new Texture(pixmap);

        createLevelFromPicture(levelName);

    }

    public void createLevelFromPicture(String levelName){
        Pixmap pixmap = new Pixmap(Gdx.files.internal(levelName));
        Vector2 pictureSize = new Vector2(pixmap.getWidth(), pixmap.getHeight());
        for(int y = (int)pictureSize.y - 1; y > 0 ; y--)
            for (int x = 0; x < pictureSize.x; x++){
                int curPixel = pixmap.getPixel(x, y);
                Color curColor = new Color(curPixel);

                //black = blocks, red = playerRed, blue = playerBlue
                Vector2 locPos = new Vector2(pixmap.getWidth() - x, pixmap.getHeight() - y)
                        .scl(Constants.BLOCKSIZE.x)
                        .sub(size.cpy().scl(0.5f));
                if (curColor.equals(Color.BLACK)){
                    blocks.add(new Block(Constants.COLOR_BLOCK, this, locPos.cpy()));
                }
//                if (curColor == Color.BLUE){
//                    playerBlue = new PlayerBlue(locPos.cpy(), this);
//                }
//                if (curColor == Color.RED){
//                    playerRed = new PlayerRed(locPos.cpy(), this);
//                }
            }
    }

    public void makeBound(){
        bounds = new Array<Bound>();
        bounds.add(new Bound(Vector2.Zero.cpy().sub(0, size.y / 2), this, new Vector2(size.x + Constants.BLOCKSIZE.x * 2, Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(0, size.y / 2), this, new Vector2(size.x + Constants.BLOCKSIZE.x * 2, Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().sub(size.x / 2, 0).sub(Constants.BLOCKSIZE.x, 0), this, new Vector2(Constants.EPS, size.y)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(size.x / 2, 0).add(Constants.BLOCKSIZE.x, 0), this, new Vector2(Constants.EPS, size.y)));
        //bounds.get(2).body.getFixtureList().get(0).setSensor(true);
        //bounds.get(3).body.getFixtureList().get(0).setSensor(true);
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
                tex.getWidth(),
                tex.getHeight(),
                false,
                false
        );
        for (Block b: blocks)
            b.render(batch);
        for (Bound b: bounds)
            b.render(batch);
        ballBlue.render(batch);
        playerRed.render(batch);
        playerBlue.render(batch);
        ballRed.render(batch);
        ballBlue.render(batch); //render twice??
    }

    public void Update(float delta){
        b2world.step(Gdx.graphics.getDeltaTime(), 8, 6);
        playerBlue.Update(delta);
        playerRed.Update(delta);
        ballBlue.Update(delta);
        ballRed.Update(delta);
    }

    @Override
    public void dispose(){
        b2world.dispose();
    }

}
