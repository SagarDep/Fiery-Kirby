package com.mygdx.fierykirby.gamedev.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fierykirby.gamedev.Level;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Enums.Direction;
import com.mygdx.fierykirby.gamedev.Utility.Enums.FireState;
import com.mygdx.fierykirby.gamedev.Utility.Enums.JumpState;
import com.mygdx.fierykirby.gamedev.Utility.Enums.SpawnState;
import com.mygdx.fierykirby.gamedev.Utility.Enums.WalkState;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.COIN_SOUND_VOLUME;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_HEIGHT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_WIDTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL_OFFSET_FROM_KIRBY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL_SOUND_VOLUME;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GRAVITY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GROUND_HEIGHT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.INITIAL_KIRBY_LIVES;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.JUMP_SPEED;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_FALL_OFFSET;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_FOOT_WIDTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_HEIGHT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_MOVE_SPEED;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_SCALE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.SCORE_100;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public class Kirby {

    public final static String TAG = Kirby.class.getName();

    public boolean jumpButtonPressed;
    public boolean leftButtonPressed;
    public boolean rightButtonPressed;
    private Vector2 position;
    private Direction direction;
    private Vector2 velocity;
    private boolean isLevelComplete;

    Vector2 lastFramePos;

    private JumpState jumpState;
    private WalkState walkState;
    private SpawnState spawnState;
    private FireState fireState;
    private Vector2 kirbysPosWhenBulletFired;

    private long walkStartTime;
    private long startFiringTime;
    private int lives;


    Vector2 spawnLocation;

    Level level;

    public Kirby(Vector2 spawnLocation, Level level) {
        this.spawnLocation = spawnLocation;
        velocity = new Vector2();
        position = new Vector2();
        lastFramePos = new Vector2();
        kirbysPosWhenBulletFired = new Vector2();
        this.level = level;
        init();
    }

    public void init() {

        lives = INITIAL_KIRBY_LIVES;
        isLevelComplete = false;
        respawn();
    }

    private void respawn() {
        spawnState = SpawnState.SPAWNING;
        position.set(spawnLocation);
        lastFramePos.set(spawnLocation);

        direction = Direction.RIGHT;
        walkState = WalkState.STANDING;
        fireState = FireState.NOTFIRING;
        jumpState = JumpState.FALLING;
        velocity.setZero();
    }

    public void update(float delta, Array<Platform> platforms) {

        lastFramePos.set(position);

        // Scale position and velocity
        velocity.y -= delta * GRAVITY;
        position.mulAdd(velocity, delta);

        if (jumpState != JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }

        // Check to make Kirby doesn't fall through the ground
        if (position.y < GROUND_HEIGHT) {
            jumpState = JumpState.GROUNDED;
            position.y = GROUND_HEIGHT;
            velocity.y = 0;
        }

        // Check every platform to see if Kirby has landed on any of them
        for (Platform platform : platforms) {
            if (landedOnPlatform(platform)) {
                jumpState = JumpState.GROUNDED;
                spawnState = SpawnState.NOT_SPAWNING;
                velocity.y = 0;
                position.y = platform.top;
            }
        }

        // Defines kirby's bounding Rectangle
        Rectangle kirbyBounds = new Rectangle(
                position.x, position.y,
                KIRBY_FOOT_WIDTH, KIRBY_HEIGHT
        );

        ensureKirbyWorldBounds();
        enemyAndKirbyOverlap(kirbyBounds);
        kirbyCoinOverlap(kirbyBounds);

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || jumpButtonPressed) {
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    break;
                case FALLING:
                    break;
            }
        } else {
            endJump();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && position.y != GROUND_HEIGHT) {
            kirbyFallsThroughPlatform();
        } else {
            endJump();
        }

        if(spawnState == SpawnState.NOT_SPAWNING){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButtonPressed ) {
                moveLeft(delta);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButtonPressed) {
                moveRight(delta);
            } else {
                walkState = WalkState.STANDING;
            }
        }


        // Fire fireballs when Spacebar is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            fireBullet();
        } else {
            fireState = FireState.NOTFIRING;
        }

    }

    // Remove coins from screen when kirby and coin overlap
    private void kirbyCoinOverlap(Rectangle kirbyBounds) {
        DelayedRemovalArray<Coin> coins = level.getCoins();
        coins.begin();

        for (int i = 0; i < coins.size; i++) {
            Coin coin = coins.get(i);
            Rectangle coinBounds = new Rectangle(coin.position.x, coin.position.y,
                    Assets.instance.coinAssets.apple.getRegionWidth(),
                    Assets.instance.coinAssets.apple.getRegionHeight());

            if (kirbyBounds.overlaps(coinBounds)) {

                // Add to score
                level.score += SCORE_100;

                level.spawnCoinAmount(level.getEnemyDeadPosition());


                // Play bullet sound
                Assets.instance.soundAssets.itemPickupSound.play(COIN_SOUND_VOLUME);
                coins.removeIndex(i);
            }
        }

        coins.end();
    }

    private void kirbyFallsThroughPlatform() {
        switch (jumpState) {
            case GROUNDED:
                jumpState = JumpState.FALLING;
                position.y -= KIRBY_FALL_OFFSET;
                break;
            case JUMPING:
                break;
            case FALLING:
                break;
        }
    }

    private void enemyAndKirbyOverlap(Rectangle kirbyBounds) {

        // Give each enemy a bounding rectangle
        for (Enemy enemy : level.getEnemies()) {
            Rectangle enemyBounds = new Rectangle(
                    enemy.position.x, enemy.position.y,
                    CRAB_WIDTH, CRAB_HEIGHT
            );

            // Check if Kirby's rectangle and the enemy's rectangle overlap. If they do, its curtains for Kirby
            if (kirbyBounds.overlaps(enemyBounds)) {
                lives--;

                // Play sound when hit by enemy
                Assets.instance.soundAssets.kirbyKnockOut.play();

                if (lives > -1) {
                    respawn();
                }
            }
        }
    }


    // Check whether Kirby has landed on a platform
    private boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePos.y >= platform.top && position.y < platform.top) {
            float leftFoot = position.x;
            float rightFoot = position.x + KIRBY_FOOT_WIDTH;

            leftFootIn = (platform.left < leftFoot && leftFoot < platform.right);
            rightFootIn = (platform.left < rightFoot && rightFoot < platform.right);
            straddle = (leftFoot < platform.left && rightFoot > platform.right);
        }
        return leftFootIn || rightFootIn || straddle;
    }

    public void fireBullet() {

        Vector2 bulletPosition;

        startFiringTime = TimeUtils.nanoTime();
        fireState = FireState.FIRING;
        kirbysPosWhenBulletFired = lastFramePos;
        // Play bullet sound
        Assets.instance.soundAssets.fireBulletSound.play(FIREBALL_SOUND_VOLUME);

        // Change the position in which the fireball spawns from based on direction -> Left or Right
        if (direction == Direction.RIGHT) {

             bulletPosition = new Vector2(position.x + FIREBALL_OFFSET_FROM_KIRBY.x, position.y + FIREBALL_OFFSET_FROM_KIRBY.y);
        } else {
            bulletPosition = new Vector2(position.x - FIREBALL_OFFSET_FROM_KIRBY.x, position.y + FIREBALL_OFFSET_FROM_KIRBY.y);
        }
        level.spawnBullet(bulletPosition, direction);

    }

    // Ensures Kirby cannot escape the boundaries of the world
    private void ensureKirbyWorldBounds() {
        if (position.x + KIRBY_FOOT_WIDTH > level.getViewport().getWorldWidth()) {
            position.x = level.getViewport().getWorldWidth() - KIRBY_FOOT_WIDTH;
        }
        if (position.x < 0) {
            position.x = 0;
        }
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        velocity.y = JUMP_SPEED;
    }

    private void endJump() {
        if (jumpState == JumpState.JUMPING)
            jumpState = JumpState.FALLING;
    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        direction = Direction.LEFT;
        position.x -= delta * KIRBY_MOVE_SPEED;
    }

    private void moveRight(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        direction = Direction.RIGHT;
        position.x += delta * KIRBY_MOVE_SPEED;

    }

    public void render(SpriteBatch batch) {

        if(!isLevelComplete) {
            TextureRegion region = Assets.instance.kirbyAssets.standing;
            if (direction == Direction.LEFT) {
                if (jumpState != JumpState.GROUNDED) {
                    region = Assets.instance.kirbyAssets.jumping;
                } else if (walkState == WalkState.STANDING) {
                    region = Assets.instance.kirbyAssets.standing;

                } else if (walkState == WalkState.WALKING) {
                    float elapsedWalkTime = Utils.timeInSecsSince(walkStartTime);
                    region = Assets.instance.kirbyAssets.walkingAnimation.getKeyFrame(elapsedWalkTime);
                }
                Utils.drawTextureRegion(batch, region, position.x, position.y, KIRBY_SCALE);

            }

            if (direction == Direction.RIGHT) {

                if (jumpState != JumpState.GROUNDED) {
                    region = Assets.instance.kirbyAssets.jumping;

                } else if (walkState == WalkState.STANDING) {
                    region = Assets.instance.kirbyAssets.standing;

                } else if (walkState == WalkState.WALKING) {
                    float elapsedWalkTime = Utils.timeInSecsSince(walkStartTime);
                    region = Assets.instance.kirbyAssets.walkingAnimation.getKeyFrame(elapsedWalkTime);
                }
                Utils.drawFlippedTextureRegion(batch, region, position.x, position.y, KIRBY_SCALE);
            }
        }

    }

    public void setLevelComplete(boolean levelComplete) {
        this.isLevelComplete = levelComplete;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getLives() {
        return lives;
    }
}
