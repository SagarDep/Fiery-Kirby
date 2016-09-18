package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fierykirby.gamedev.Utility.Utils;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 9/3/16.
 */
public class CoinAmount {

    private Vector2 position;
    final BitmapFont font;
    private final long startTime;


    public CoinAmount(Vector2 position){
        this.position = position;
        font = new BitmapFont();

        font.getData().setScale(0.3f);
        startTime = TimeUtils.nanoTime();
    }

    public void update(float delta){
        position.y += delta * TEXT_SPEED;
    }

    public void render(SpriteBatch batch, String coinWorthString){

        if(!isFinished()) {
            font.draw(batch, coinWorthString, position.x, position.y);
        }
    }

    public boolean isFinished(){
        float elapsedTime = Utils.timeInSecsSince(startTime);
        return (elapsedTime > TIME_ONSCREEN);
    }
}
