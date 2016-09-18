package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 8/26/16.
 */
public class Platform {

    public final float top;
    public final float left;
    public final float bottom;
    public final float right;
    public final float width;
    public final float height;


    public final String platformName;


    public Platform(float left, float bottom, float width, float height, String platformName) {

        this.left = left;
        this.bottom = bottom;
        this.width = width;
        this.height = height;
        this.right = left + width;
        this.top = bottom + height;
        this.platformName = platformName;

    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.platformAssets.platform;

        if (platformName.equals(BASE))
            region = Assets.instance.platformAssets.platform;
        else if (platformName.equals(GRASS_HALF_LEFT))
            region = Assets.instance.platformAssets.grassHalfLeft;
        else if (platformName.equals(GRASS_HALF_RIGHT))
            region = Assets.instance.platformAssets.grassHalfRight;
        else if (platformName.equals(GRASS_HALF_MID))
            region = Assets.instance.platformAssets.grassHalfMid;

        else if (platformName.equals(SAND_HALF_LEFT))
            region = Assets.instance.platformAssets.sandHalfLeft;
        else if (platformName.equals(SAND_HALF_MID))
            region = Assets.instance.platformAssets.sandHalfMid;
        else if (platformName.equals(SAND_HALF_RIGHT))
            region = Assets.instance.platformAssets.sandHalfRight;



        Utils.drawPlatformTextureRegion(batch, region, left, bottom, width, height);
    }
}
