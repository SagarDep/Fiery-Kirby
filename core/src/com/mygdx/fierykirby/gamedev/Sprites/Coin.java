package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Utils;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;
/**
 * Created by galaxywizkid on 9/3/16.
 */
public class Coin {

    Vector2 position;

    public Coin(Vector2 position){
        this.position = position;
    }

    public void render(SpriteBatch batch){
        final TextureRegion region = Assets.instance.coinAssets.apple;
        Utils.drawTextureRegion(batch, region, position.x, position.y, DIAMOND_SCALE);
    }
}
