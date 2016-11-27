package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fierykirby.gamedev.Sprites.Kirby;

/**
 * Created by galaxywizkid on 11/27/16.
 */

public class OnScreenControls {

    public static final String TAG = OnScreenControls.class.getName();

    public final Viewport viewport;
    public Kirby kirby;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 moveDownCenter = new Vector2();
    private Vector2 shootCenter = new Vector2();
    private Vector2 jumpCenter = new Vector2();


    public OnScreenControls() {
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE
        );
        recalculateButtonPositions();
    }

    public void render(SpriteBatch batch) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Draw left, right, jump, down, and shoot onScreen buttons
        Utils.drawTextureRegionControls(
                batch,
                Assets.instance.onscreenControlsAssets.moveLeft,
                moveLeftCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegionControls(
                batch,
                Assets.instance.onscreenControlsAssets.moveRight,
                moveRightCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegionControls(
                batch,
                Assets.instance.onscreenControlsAssets.moveDown,
                moveDownCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegionControls(
                batch,
                Assets.instance.onscreenControlsAssets.shoot,
                shootCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegionControls(
                batch,
                Assets.instance.onscreenControlsAssets.jump,
                jumpCenter,
                Constants.BUTTON_CENTER
        );

        batch.end();
    }

    public void recalculateButtonPositions() {
        moveLeftCenter.set(Constants.BUTTON_SIZE * 3 / 4, Constants.BUTTON_SIZE * 3 / 4);
        moveRightCenter.set(Constants.BUTTON_SIZE * 2, Constants.BUTTON_SIZE * 3 / 4);


        shootCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_SIZE * 2f,
                Constants.BUTTON_SIZE * 3 / 4
        );

        jumpCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_SIZE * 3 / 4,
                Constants.BUTTON_SIZE * 1.4f
        );

        moveDownCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_SIZE * 3 / 4,
                Constants.BUTTON_SIZE * 0.7f);


    }
}
