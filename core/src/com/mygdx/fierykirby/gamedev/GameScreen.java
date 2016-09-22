package com.mygdx.fierykirby.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Constants;
import com.mygdx.fierykirby.gamedev.Utility.KirbyHUD;
import com.mygdx.fierykirby.gamedev.Utility.LevelLoader;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.WORLD_SIZE;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public class GameScreen extends ScreenAdapter {

    public static final String TAG = GameScreen.class.getName();

    Level level;
    SpriteBatch batch;
    ExtendViewport gameplayViewport;
    private KirbyHUD hud;


    @Override
    public void show() {

        AssetManager am = new AssetManager();
        Assets.instance.init(am);
        gameplayViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        level = LevelLoader.load("level1", gameplayViewport);
        batch = new SpriteBatch();
        hud = new KirbyHUD();

    }


    @Override
    public void resize(int width, int height) {
        hud.viewport.update(width, height, true);
        gameplayViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }

    @Override
    public void render(float delta) {

        level.update(delta);
        gameplayViewport.apply();

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(gameplayViewport.getCamera().combined);
        level.render(batch);
        hud.render(batch, level.getKirby().getLives(), level.score);



    }


}


