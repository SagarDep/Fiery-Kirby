package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by galaxywizkid on 8/24/16.
 */
public class Utils {

    public static void drawTextureRegionControls(SpriteBatch batch, TextureRegion region, Vector2 position, Vector2 offset) {
        drawTextureRegion(batch, region, position.x - offset.x, position.y - offset.y, 0.5f);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float scale) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                scale,
                scale,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public static void drawFlippedTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float scale) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                scale,
                scale,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                true,
                false);
    }

    public static void drawPlatformTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float width, float height) {

        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                width,
                height,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public static float timeInSecsSince(long startTime) {
        return (TimeUtils.nanoTime() - startTime) * MathUtils.nanoToSec;
    }
}
