package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Enums.Direction;
import com.mygdx.fierykirby.gamedev.Utility.Enums.PositionOnPlatform;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_SCALE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_SPEED;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_WIDTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.ENEMY_HEALTH;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public class Enemy {

    long startTime;
    public Vector2 position;
    Direction direction;
    Platform platform;
    float leftBound, rightBound;
    PositionOnPlatform positionOnPlatform;

    public int health;

    public Enemy(Platform platform, float leftBound, float rightBound, PositionOnPlatform positionOnPlatform) {
        this.platform = platform;
        direction = Direction.RIGHT;
        startTime = TimeUtils.nanoTime();
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.positionOnPlatform = positionOnPlatform;

        position = new Vector2(platformXPos(), platform.top);

        health = ENEMY_HEALTH;

    }

    public void update(float delta) {
        switch (direction) {
            case LEFT:
                position.x -= delta * CRAB_SPEED;
                break;
            case RIGHT:
                position.x += delta * CRAB_SPEED;
        }

        // Check bounds of crab; If outside of platform, change direction and patrol the other way
        if (position.x < leftBound) {
            position.x = leftBound;
            direction = Direction.RIGHT;
        } else if (position.x + CRAB_WIDTH > rightBound) {
            position.x = rightBound - CRAB_WIDTH;
            direction = Direction.LEFT;
        }
    }

    // Assigns the crab's x spawn position based on whether we want the enemy to spawn on the left, the right or in middle of the platform
    public float platformXPos(){
        if(positionOnPlatform == PositionOnPlatform.LEFT_MOST){
            return leftBound;
        }else if(positionOnPlatform == PositionOnPlatform.RIGHT_MOST){
            return rightBound;
        }else
            return (rightBound + leftBound)/2;
    }


    public void render(SpriteBatch batch) {

        // Draw crab walk loop
        float elapsedTime = Utils.timeInSecsSince(startTime);
        TextureRegion region = Assets.instance.enemyAssets.crabAnimation.getKeyFrame(elapsedTime);
        Utils.drawTextureRegion(batch, region, position.x, position.y, CRAB_SCALE);
    }
}
