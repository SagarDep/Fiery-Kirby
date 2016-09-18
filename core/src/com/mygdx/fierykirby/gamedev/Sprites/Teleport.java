package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by galaxywizkid on 9/9/16.
 */
public class Teleport {

    private Vector2 position;
    private final long startTime;

    public Teleport(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {

        //if (!isFinished()) {
            /*Utils.drawTextureRegion(
                    batch,
                    Assets.instance.teleportationAssets.teleportAnimation.getKeyFrame(Utils.timeInSecsSince(startTime)),
                    position.x, position.y, DEFAULT_SCALE);*/
       // }
    }

   /* public boolean isFinished() {
        float elapsedTime = Utils.timeInSecsSince(startTime);
        return Assets.instance.teleportationAssets.teleportAnimation.isAnimationFinished(elapsedTime);
    }
*/
    public void setPosition(Vector2 position) {
        this.position = position;
    }

}
