package com.mygdx.fierykirby.gamedev;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fierykirby.gamedev.Sprites.BackgroundSprite;
import com.mygdx.fierykirby.gamedev.Sprites.Bullet;
import com.mygdx.fierykirby.gamedev.Sprites.Cloud;
import com.mygdx.fierykirby.gamedev.Sprites.Coin;
import com.mygdx.fierykirby.gamedev.Sprites.CoinAmount;
import com.mygdx.fierykirby.gamedev.Sprites.Enemy;
import com.mygdx.fierykirby.gamedev.Sprites.ExitPortal;
import com.mygdx.fierykirby.gamedev.Sprites.Explosion;
import com.mygdx.fierykirby.gamedev.Sprites.GameOverOverlay;
import com.mygdx.fierykirby.gamedev.Sprites.Kirby;
import com.mygdx.fierykirby.gamedev.Sprites.Platform;
import com.mygdx.fierykirby.gamedev.Sprites.VictoryOverlay;
import com.mygdx.fierykirby.gamedev.Utility.Assets;
import com.mygdx.fierykirby.gamedev.Utility.Enums.Direction;
import com.mygdx.fierykirby.gamedev.Utility.LevelLoader;
import com.mygdx.fierykirby.gamedev.Utility.Utils;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.DIST_APART;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.ENEMY_DEAD_OFFSET;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXIT_PORTAL_OFFSET_TO_MIDDLE_POS;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION_SUPER;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_OFFSET_TO_MID_POS;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_END_DURATION;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public class Level {

    public int score;
    private Kirby kirby;
    public boolean gameOver;
    private boolean victory;
    private DelayedRemovalArray<Enemy> enemies;
    private Array<Platform> platforms;
    private DelayedRemovalArray<Bullet> bullets;
    private Viewport viewport;
    private DelayedRemovalArray<Explosion> explosions;
    private DelayedRemovalArray<CoinAmount> coinAmounts;
    private Array<Cloud> clouds;
    private Array<BackgroundSprite> backgroundSprites;
    private Vector2 enemyDeadPosition;
    private ExitPortal exitPortal;
    private VictoryOverlay victoryOverlay;
    private GameOverOverlay gameOverOverlay;
    long levelEndOverlayStartTime;

    private Music music;
    private DelayedRemovalArray<Coin> coins;

    public Level(Viewport viewport) {
        kirby = new Kirby(new Vector2(20, 100), this);
        platforms = new Array<Platform>();
        bullets = new DelayedRemovalArray<Bullet>();
        enemies = new DelayedRemovalArray<Enemy>();
        explosions = new DelayedRemovalArray<Explosion>();
        coins = new DelayedRemovalArray<Coin>();
        coinAmounts = new DelayedRemovalArray<CoinAmount>();
        clouds = new Array<Cloud>();
        backgroundSprites = new Array<BackgroundSprite>();
        gameOver = false;
        victory = false;
        this.viewport = viewport;
        exitPortal = new ExitPortal();
        victoryOverlay = new VictoryOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);

        score = 0;

        music = Assets.instance.soundAssets.gameMusic;
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

    }

    public void update(float delta) {

        // Update the overlays
        victoryOverlay.update(delta);
        gameOverOverlay.update(delta);

        // If Kirby has less than 0 lives, set gameOver to true
        if (kirby.getLives() < 0) {
            gameOver = true;
            kirby.setLevelComplete(true);
        }

        // Keep running game as long as Kirby still has lives
        if (!gameOver && !victory) {
            kirby.update(delta, platforms);

            // Remove enemies once their health runs out and spawn coins
            enemies.begin();
            for (int i = 0; i < enemies.size; i++) {
                Enemy enemy = enemies.get(i);
                enemy.update(delta);
                if (enemy.health < 1) {
                    spawnExplosion(enemy.position, EXPLOSION_SUPER);
                    spawnCoins(enemy.position);

                    enemyDeadPosition = new Vector2(enemy.position);
                    enemyDeadPosition.y += ENEMY_DEAD_OFFSET;

                    enemies.removeIndex(i);

                }
            }
            enemies.end();


            // If all enemies dead, spawn exit portal
            if (enemies.size <= 0) {

                if (kirbyOverlapPortal()) {

                    // Set-in-motion = true, will cause the exit portal's update method to get called,
                    // which will move the position of the portal off-screen giving it the appearance of
                    // flying away
                    exitPortal.setInMotion(true);

                    // Setting level complete to true will cause kirby to no longer be rendered on screen
                    // This gives the appearance of kirby entering the portal and vanishing
                    kirby.setLevelComplete(true);
                }

            }
            exitPortal.update(delta);

            // Once exit portal is off screen, set victory to true
            if (exitPortal.position.y > this.getViewport().getWorldHeight()) {
                victory = true;
            }
            // Remove bullet once it is no longer active
            bullets.begin();
            for (Bullet bullet : bullets) {
                bullet.update(delta);
                if (!bullet.isActive()) {
                    bullets.removeValue(bullet, false);
                }
            }
            bullets.end();

            // Dispose of explosions once they finish rendering
            explosions.begin();
            for (int i = 0; i < explosions.size; i++) {
                if (explosions.get(i).isFinished()) {
                    explosions.removeIndex(i);
                }
            }
            explosions.end();

            // Dispose of coins once they finish rendering
            coinAmounts.begin();
            for (CoinAmount coinAmount : coinAmounts) {
                coinAmount.update(delta);

                if (coinAmount.isFinished()) {
                    coinAmounts.removeValue(coinAmount, false);
                }

            }
            coinAmounts.end();


            // Reposition cloud by moving it back to the start of the viewport when it is no longer in view
            for (int i = 0; i < clouds.size; i++) {
                Cloud cloud = clouds.get(i);
                cloud.update(delta);
                if (cloud.left > viewport.getWorldWidth()) {
                    cloud.left = -1 * cloud.width;
                }
            }
        }

    }

    public boolean kirbyOverlapPortal() {

        Vector2 midExitPortal = new Vector2(
                exitPortal.getPosition().x + EXIT_PORTAL_OFFSET_TO_MIDDLE_POS.x,
                exitPortal.getPosition().y + EXIT_PORTAL_OFFSET_TO_MIDDLE_POS.y);

        Vector2 midKirby = new Vector2(
                kirby.getPosition().x + KIRBY_OFFSET_TO_MID_POS.x,
                kirby.getPosition().y + KIRBY_OFFSET_TO_MID_POS.y);

        return (midExitPortal.dst(midKirby) < DIST_APART);
    }

    public void spawnBullet(Vector2 position, Direction direction) {
        bullets.add(new Bullet(this, position, direction));
    }

    public void spawnExplosion(Vector2 position, String explosionType) {
        explosions.add(new Explosion(position, explosionType));
    }

    public void spawnCoins(Vector2 position) {
        coins.add(new Coin(position));
    }


    public void spawnCoinAmount(Vector2 position) {
        coinAmounts.add(new CoinAmount(position));
    }

    public Vector2 getEnemyDeadPosition() {
        return enemyDeadPosition;
    }

    public void render(SpriteBatch batch) {
        batch.begin();


        for (BackgroundSprite backgroundSprite : backgroundSprites) {
            backgroundSprite.render(batch);
        }

        for (Platform platform : platforms) {
            platform.render(batch);
        }

        // Render kirby after rendering the platforms that way kirby is drawn on a layer on top of the platform
        // This way he appears to jump infront of, and not underneath the platform
        kirby.render(batch);

        for (Cloud cloud : clouds) {
            cloud.render(batch);
        }

        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }

        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }

        for (Coin coin : coins) {
            coin.render(batch);
        }

        for (CoinAmount coinAmount : coinAmounts) {
            coinAmount.render(batch, "100");
        }

        if (enemies.size <= 0) {
            exitPortal.render(batch);
        }

        if (gameOver) {
            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();
            }
            gameOverOverlay.render(batch);

            if (Utils.timeInSecsSince(levelEndOverlayStartTime) > LEVEL_END_DURATION) {
                levelEndOverlayStartTime = 0;
                levelFailed();
            }
        }

        if (gameOver) {
            gameOverOverlay.render(batch);
        }

        if (victory) {
            victoryOverlay.render(batch);
        }

        batch.end();
    }


    private void renderLevelEndOverlays(SpriteBatch batch) {
        if (gameOver) {
            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();
            }
            gameOverOverlay.render(batch);

            if (Utils.timeInSecsSince(levelEndOverlayStartTime) > LEVEL_END_DURATION) {
                levelEndOverlayStartTime = 0;
                levelFailed();
            }
        }
    }


    public void levelFailed() {
        //startNewLevel();
        Level level = new Level(viewport);
        level = LevelLoader.load("level1", viewport);
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public Kirby getKirby() {
        return kirby;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public DelayedRemovalArray<Coin> getCoins() {
        return coins;
    }

    public Array<Cloud> getClouds() {
        return clouds;
    }

    public Array<BackgroundSprite> getBackgroundSprites() {
        return backgroundSprites;
    }

    public boolean getVictory() {
        return victory;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public Viewport getViewport() {
        return viewport;
    }


}
