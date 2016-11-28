package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fierykirby.gamedev.Sprites.Kirby;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.BUTTON_SIZE;

/**
 * Created by galaxywizkid on 11/27/16.
 */

public class OnScreenControls extends InputAdapter {

    public static final String TAG = OnScreenControls.class.getName();

    public final Viewport viewport;
    public Kirby kirby;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 moveDownCenter = new Vector2();
    private Vector2 shootCenter = new Vector2();
    private Vector2 jumpCenter = new Vector2();
    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;
    private int moveDownPointer;



    public OnScreenControls() {
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);

        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        moveDownCenter = new Vector2();
        shootCenter = new Vector2();
        jumpCenter = new Vector2();

        recalculateButtonPositions();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(shootCenter) < BUTTON_SIZE) {

            kirby.fireBullet();

        } else if (viewportPosition.dst(jumpCenter) < BUTTON_SIZE) {

            // TODO: Save the jumpPointer and set kirby.jumpButtonPressed = true
            jumpPointer = pointer;
            kirby.jumpButtonPressed = true;

        } else if (viewportPosition.dst(moveLeftCenter) < BUTTON_SIZE) {

            // TODO: Save the moveLeftPointer, and set kirby.leftButtonPressed = true
            moveLeftPointer = pointer;
            kirby.leftButtonPressed = true;

        } else if (viewportPosition.dst(moveRightCenter) < BUTTON_SIZE) {

            // TODO: Save the moveRightPointer, and set kirby.rightButtonPressed = true
            moveRightPointer = pointer;
            kirby.rightButtonPressed = true;

        }else if (viewportPosition.dst(moveDownCenter) < BUTTON_SIZE) {

            // TODO: Save the moveRightPointer, and set kirby.rightButtonPressed = true
            moveDownPointer = pointer;
            kirby.downButtonPressed = true;

        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < BUTTON_SIZE) {

            // TODO: Handle the case where the left button touch has been dragged to the right button
            // Inform kirby that the left button is no longer pressed
            kirby.leftButtonPressed = false;

            // Inform kirby that the right button is now pressed
            kirby.rightButtonPressed = true;

            // Zero moveLeftPointer
            moveLeftPointer = 0;

            // Save moveRightPointer
            moveRightPointer = pointer;
        }

        if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < BUTTON_SIZE) {

            // TODO: Handle the case where the right button touch has been dragged to the left button
            kirby.rightButtonPressed = false;
            kirby.leftButtonPressed = true;
            moveRightPointer = 0;
            moveLeftPointer = pointer;

        }

        return super.touchDragged(screenX, screenY, pointer);
    }

    public void render(SpriteBatch batch) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(jumpPointer)) {
            kirby.jumpButtonPressed = false;
            jumpPointer = 0;
        }

        // TODO: If the moveLeftPointer is no longer touched, inform Kirby and zero moveLeftPointer
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            kirby.leftButtonPressed = false;
            moveLeftPointer = 0;
        }

        // TODO: Do the same for moveRightPointer
        if (!Gdx.input.isTouched(moveRightPointer)) {
            kirby.rightButtonPressed = false;
            moveRightPointer = 0;
        }
        if (!Gdx.input.isTouched(moveDownPointer)) {
            kirby.downButtonPressed = false;
            moveRightPointer = 0;
        }


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
        moveLeftCenter.set(BUTTON_SIZE * 3 / 4, BUTTON_SIZE * 3 / 4);
        moveRightCenter.set(BUTTON_SIZE * 2, BUTTON_SIZE * 3 / 4);


        shootCenter.set(
                viewport.getWorldWidth() - BUTTON_SIZE * 2f,
                BUTTON_SIZE * 3 / 4
        );

        jumpCenter.set(
                viewport.getWorldWidth() - BUTTON_SIZE * 3 / 4,
                BUTTON_SIZE * 1.4f
        );

        moveDownCenter.set(
                viewport.getWorldWidth() - BUTTON_SIZE * 3 / 4,
                BUTTON_SIZE * 0.7f);


    }
}
