package com.snyssfx.breakout;

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
    public boolean isWin;

    public Level(Vector2 pos, String levelName, Vector2 size, Color color){
        position = pos.cpy();
        this.size = size.cpy();
        this.levelName = levelName;
        this.color = color;
    }

    public void Init(){
        isWin = false;
        if (b2world != null) b2world.dispose();
        b2world = new World(Vector2.Zero.cpy(), false);
        b2world.setContactListener(new NewContactListener());
        blocks = new Array<Block>();

        playerBlue = new PlayerBlue(size.cpy().scl(-0.5f).add(0, size.y / 2), this);
        playerRed = new PlayerRed(size.cpy().scl(0.5f).sub(0, size.y / 2), this);
        ballBlue = new BallBlue(new Vector2(-0.05f,0), this);
        ballRed = new BallRed(new Vector2(0.05f, 0), this);

        makeBound();
        pixmap = new Pixmap(
                (int) ((size.x) * Constants.PXPERMETER),
                (int) ((size.y) * Constants.PXPERMETER),
                Pixmap.Format.RGBA8888
        );
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

                //black = blocks
                Vector2 locPos = new Vector2(pixmap.getWidth() - x, pixmap.getHeight() - y)
                        .scl(Constants.BLOCKSIZE.x)
                        .sub(size.cpy().scl(0.5f));
                if (curColor.equals(Color.BLACK)){
                    Color rColor = new Color(MathUtils.random(0.2f, 0.4f), MathUtils.random(0.5f, 1), MathUtils.random(0.1f, 0.2f), 1);
                    blocks.add(new Block(rColor, this, locPos.cpy()));
                }
            }
    }

    public void makeBound(){
        bounds = new Array<Bound>();
        bounds.add(new Bound(Vector2.Zero.cpy().sub(0, size.y / 2), this, new Vector2(size.x + Constants.BLOCKSIZE.x * 2, 10 * Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(0, size.y / 2), this, new Vector2(size.x + Constants.BLOCKSIZE.x * 2, 10 * Constants.EPS)));
        bounds.add(new Bound(Vector2.Zero.cpy().sub(size.x / 2, 0).sub(Constants.BLOCKSIZE.x, 0), this, new Vector2(10 * Constants.EPS, size.y)));
        bounds.add(new Bound(Vector2.Zero.cpy().add(size.x / 2, 0).add(Constants.BLOCKSIZE.x, 0), this, new Vector2(10 * Constants.EPS, size.y)));
    }

    public void checkWin(){
        isWin = true;
        for (Block b: blocks) {
            if (b.isActive){
                isWin = false;
                break;
            }
        }
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
        if (!isWin){
            ballBlue.render(batch);
            playerRed.render(batch);
            playerBlue.render(batch);
            ballRed.render(batch);
            ballBlue.render(batch); //render twice??
        }
    }

    public void Update(float delta){
        b2world.step(Gdx.graphics.getDeltaTime(), 8, 6);
        playerBlue.Update(delta);
        playerRed.Update(delta);
        ballBlue.Update(delta);
        ballRed.Update(delta);
        checkWin();
    }

    @Override
    public void dispose(){
        b2world.dispose();
    }
}
