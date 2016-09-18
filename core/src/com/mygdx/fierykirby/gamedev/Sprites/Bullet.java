package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fierykirby.gamedev.Level;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Enums.Direction;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.BULLET_LENGTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION_REGULAR;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL_SCALE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL_SPEED;

/**
 * Created by galaxywizkid on 8/29/16.
 */
public class Bullet {

    private Direction direction;
    private Vector2 position;
    private boolean isActive;
    Level level;

    public Bullet(Level level, Vector2 position, Direction direction) {
        this.level = level;
        this.direction = direction;
        this.position = position;
        isActive = true;
    }

    public void update(float delta) {

        // Have the fireball move in the pos and neg x direction based on which direction kirby is facing
        switch (direction) {
            case RIGHT:
                position.x += delta * FIREBALL_SPEED;
                break;
            case LEFT:
                position.x -= delta * FIREBALL_SPEED;
                break;
        }

        // TODO: Make bullet travel only a constant distance
        // Once bullet is offScreen set active to false so it can be removed by the delayedRemoval Array
        /*if (position.x > level.getKirby().kirbysPosWhenBulletFired.x + 40 || position.x < level.getKirby().kirbysPosWhenBulletFired.x - 40) {
            isActive = false;
        }*/

        if (position.x > level.getKirby().lastFramePos.x + 90 || position.x < level.getKirby().lastFramePos.x - 90) {
            isActive = false;
        }

        for (Enemy enemy : level.getEnemies()) {
            if(position.dst(enemy.position) < BULLET_LENGTH){
                enemy.health -= 1;
                level.spawnExplosion(enemy.position, EXPLOSION_REGULAR);
                isActive = false;
            }
        }

    }

    public void render(SpriteBatch batch) {

        // Render the fireball on screen based on what direction Kirby is facing
        if (direction == Direction.RIGHT) {
            Utils.drawTextureRegion(batch, Assets.instance.fireBallAssets.fireBall, position.x, position.y, FIREBALL_SCALE);
        } else if (direction == Direction.LEFT) {
            Utils.drawFlippedTextureRegion(batch, Assets.instance.fireBallAssets.fireBall, position.x, position.y, FIREBALL_SCALE);
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
