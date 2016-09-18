package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXIT_PORTAL_SPEED;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.INITIAL_POSITION;

/**
 * Created by galaxywizkid on 9/9/16.
 */
public class ExitPortal {

    public final static String TAG = ExitPortal.class.getName();

    public Vector2 position;
    private long startTime;
    private boolean isInMotion;

    public ExitPortal(){
        startTime = TimeUtils.nanoTime();
        isInMotion = false;
        position = INITIAL_POSITION;
    }

    public void update(float delta){
        if(isInMotion){
            position.y += delta * EXIT_PORTAL_SPEED;
        }
    }

    public void render(SpriteBatch batch){
        final float elapsedTime = Utils.timeInSecsSince(startTime);
        TextureRegion region = Assets.instance.exitPortalAssets.exitPortalAnimation.getKeyFrame(elapsedTime);
        Utils.drawTextureRegion(batch, region, position.x, position.y, 1);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setInMotion(boolean inMotion) {
        this.isInMotion = inMotion;
    }

}
