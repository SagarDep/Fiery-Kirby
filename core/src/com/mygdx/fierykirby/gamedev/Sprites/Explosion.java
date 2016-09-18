package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 9/2/16.
 */
public class Explosion {

    private final Vector2 position;
    private final long startTime;
    private String explosionType;

    public Explosion(Vector2 position, String explosionType) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
        this.explosionType = explosionType;
    }

    public void render(SpriteBatch batch) {

        // Draw the animation only if the explosion animation has not finished
        // Draw larger explosion when enemy is completely destroyed
        if(!isFinished() && explosionType.equals(EXPLOSION_SUPER))
        Utils.drawTextureRegion(
                batch,
                Assets.instance.explosionAssets.superExplosionAnimation.getKeyFrame(Utils.timeInSecsSince(startTime)),
                position.x, position.y, DEFAULT_SCALE);

        // Draw smaller regular explosion as enemy's health decreases
        if(!isFinishedReg() && explosionType.equals(EXPLOSION_REGULAR))
            Utils.drawTextureRegion(
                    batch,
                    Assets.instance.explosionAssets.regularExplosionAnimation.getKeyFrame(Utils.timeInSecsSince(startTime)),
                    position.x, position.y, DEFAULT_SCALE);
    }

    public boolean isFinished(){
        float elapsedTime = Utils.timeInSecsSince(startTime);
        return Assets.instance.explosionAssets.superExplosionAnimation.isAnimationFinished(elapsedTime);
    }

    public boolean isFinishedReg(){
        float elapsedTime = Utils.timeInSecsSince(startTime);
        return Assets.instance.explosionAssets.regularExplosionAnimation.isAnimationFinished(elapsedTime);
    }
}
