package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.BROKEN_FENCE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.BUSH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CACTUS;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.HILL_SMALL;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.PLANT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.ROCK;

/**
 * Created by galaxywizkid on 9/6/16.
 */
public class BackgroundSprite {

    public float left;
    public final float bottom;
    public final float width;
    public final float height;
    public final String imageName;

    public BackgroundSprite(float left, float bottom, float width, float height, String imageName){
        this.left = left;
        this.bottom = bottom;
        this.width = width;
        this.height = height;
        this.imageName = imageName;
    }

    public void render(SpriteBatch batch){

        TextureRegion region = Assets.instance.backgroundAssets.hillSmall;

        if (imageName.equals(HILL_SMALL))
            region = Assets.instance.backgroundAssets.hillSmall;
        else if (imageName.equals(ROCK))
            region = Assets.instance.backgroundAssets.rock;
        else if (imageName.equals(BUSH))
            region = Assets.instance.backgroundAssets.bush;
        else if (imageName.equals(CACTUS))
            region = Assets.instance.backgroundAssets.cactus;
        else if (imageName.equals(PLANT))
            region = Assets.instance.backgroundAssets.plant;
        else if (imageName.equals(BROKEN_FENCE))
            region = Assets.instance.backgroundAssets.fence;


        Utils.drawPlatformTextureRegion(batch, region, left, bottom, width, height);
    }

}
