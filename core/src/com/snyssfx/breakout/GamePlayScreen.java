package com.snyssfx.breakout;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Snyss on 4/23/2017.
 */
public class GamePlayScreen extends ScreenAdapter {
    OrthographicCamera camera;
    FitViewport fitViewport;
    SpriteBatch batch;
    Array<Level> levels;
    Box2DDebugRenderer renderer;
    int curLevel;
    boolean isEnd = false;

    @Override
    public void show() {
        Gdx.app.setLogLevel(Application.LOG_NONE);

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT.x, Constants.VIEWPORT.y);
        fitViewport = new FitViewport(Constants.VIEWPORT.x, Constants.VIEWPORT.y, camera);
        levels = new Array<Level>();
        levels.add(new Level(
                Constants.LEVEL_1_POS
                , "level1.png"
                , Constants.LEVEL_1_SIZE
                , Constants.COLOR_LEVEL
        ));

        levels.add(new Level(
                Constants.LEVEL_2_POS
                , "level2.png"
                , Constants.LEVEL_2_SIZE
                , Constants.COLOR_LEVEL
        ));

        levels.add(new Level(
                Constants.LEVEL_3_POS
                , "level3.png"
                , Constants.LEVEL_3_SIZE
                , Constants.COLOR_LEVEL
        ));

        levels.add(new Level(
                Constants.LEVEL_4_POS
                , "level4.png"
                , Constants.LEVEL_4_SIZE
                , Constants.COLOR_LEVEL
        ));

        levels.add(new Level(
                Constants.LEVEL_5_POS
                , "level5.png"
                , Constants.LEVEL_5_SIZE
                , Constants.COLOR_LEVEL
        ));

        if (Gdx.app.getLogLevel() == Application.LOG_DEBUG){
            renderer = new Box2DDebugRenderer();
        }
        curLevel = 0;
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height);
        camera.update();
    }

    @Override
    public void render(float delta) {



        Level lvl = levels.get(curLevel);
        if (lvl.pixmap == null) lvl.Init();

        lvl.Update(delta);
        if (isEnd){
            lvl.isWin = false;
        }
        if (lvl.isWin){
            curLevel++;
            if (curLevel == 5) {
                isEnd = true;
                curLevel = 4;
            } else
                lvl.b2world.dispose();
            return;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i <= curLevel; i++) {
            levels.get(i).Render(batch);
        }
        if (isEnd){
            lvl.Render(batch);
        }

        if (Gdx.app.getLogLevel() == Application.LOG_DEBUG){
            renderer.render(levels.get(curLevel).b2world, camera.combined);
        }

        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

}
