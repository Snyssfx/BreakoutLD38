package com.snyssfx.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT.x, Constants.VIEWPORT.y);
        fitViewport = new FitViewport(Constants.VIEWPORT.x, Constants.VIEWPORT.y, camera);
        levels = new Array<Level>();
        levels.add(new Level(
                new Vector2((Constants.VIEWPORT.x * Constants.METERPERPX - Constants.LEVEL_1_SIZE.x) / 2
                        , Constants.VIEWPORT.y * Constants.METERPERPX * 4 / 5)
                , "test"
                , Constants.LEVEL_1_SIZE));
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height);
        camera.update();
    }

    @Override
    public void render(float delta) {

        levels.get(0).Update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        levels.get(0).Render(batch);
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
