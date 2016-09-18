package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 9/6/16.
 */
public class Cloud {

    public float left;
    public final float bottom;
    public final float width;
    public final float height;
    public final String cloudName;

    public Cloud(float left, float bottom, float width, float height, String cloudName){
        this.left = left;
        this.bottom = bottom;
        this.width = width;
        this.height = height;
        this.cloudName = cloudName;
    }



    public void update(float delta){

        if(cloudName.equals(CLOUD1)) {
            left += delta * CLOUD1_MOVE_SPEED;
        }else{
            left += delta * CLOUD2_MOVE_SPEED;
        }
    }

    public void render(SpriteBatch batch){

        TextureRegion region = Assets.instance.cloudAssets.cloud1;


        if (cloudName.equals(CLOUD1))
            region = Assets.instance.cloudAssets.cloud1;
        else if (cloudName.equals(CLOUD2))
            region = Assets.instance.cloudAssets.cloud2;

        Utils.drawPlatformTextureRegion(batch, region, left, bottom, width, height);

    }
}






















