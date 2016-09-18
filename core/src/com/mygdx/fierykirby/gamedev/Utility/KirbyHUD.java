package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 9/2/16.
 */


public class KirbyHUD {
    public final Viewport viewport;
    final BitmapFont font;

    public KirbyHUD() {
        this.viewport = new ExtendViewport(HUD_VIEWPORT_SIZE, HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(1);
    }

    public void render(SpriteBatch batch, int lives, int score) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final TextureRegion standing = Assets.instance.kirbyAssets.standing;
        final String hudString = HUD_SCORE_LABEL + score;

        font.draw(batch, hudString, HUD_MARGIN / 6 + standing.getRegionWidth(), viewport.getWorldHeight() - 2 - standing.getRegionHeight());


        for (int i = 1; i <= lives; i++) {
            final Vector2 drawPosition = new Vector2(i * (HUD_MARGIN / 6 + standing.getRegionWidth()),
                    viewport.getWorldHeight() - standing.getRegionHeight()
            );

            Utils.drawFlippedTextureRegion(batch, standing, drawPosition.x, drawPosition.y, KIRBY_HUD_SCALE);
        }
        batch.end();
    }
}
