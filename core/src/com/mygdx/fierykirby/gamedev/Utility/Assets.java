package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.BASE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.BERRY_COIN;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.BROKEN_FENCE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.BUSH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CACTUS;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOSED_MOUTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB10;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB11;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB12;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB13;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB3;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB4;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB5;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB7;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB8;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB9;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CRAB_WALK_LOOP;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.DEAD;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.DIAMOND_COIN;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXIT1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION3;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION4;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION5;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION6;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION7;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION8;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION9;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION_FRAME_DURATION_REG;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION_FRAME_DURATION_SUPER;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.EXPLOSION_LITTLE1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.FIREBALL_SOUND_FILE_PATH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GAME_MUSIC;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GRASS_HALF_LEFT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GRASS_HALF_MID;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.GRASS_HALF_RIGHT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.HILL_SMALL;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.ITEM_PICKUP_SOUND_FILE_PATH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.JUMPING;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.KIRBY_KNOCK_OUT_SOUND_PATH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.OPEN_MOUTH;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.PLANT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.ROCK;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.SAND_HALF_LEFT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.SAND_HALF_MID;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.SAND_HALF_RIGHT;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.STANDING;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.STRAWBERRY_COIN;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.TEXTURE_ATLAS;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK3;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK4;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK5;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK6;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK7;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK8;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.WALK_LOOP_DURATION;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    public KirbyAssets kirbyAssets;
    public PlatformAssets platformAssets;
    public FireBallAssets fireBallAssets;
    public EnemyAssets enemyAssets;
    public ExplosionAssets explosionAssets;
    public CoinAssets coinAssets;
    public SoundAssets soundAssets;
    public CloudAssets cloudAssets;
    public ExitPortalAssets exitPortalAssets;
    public BackgroundAssets backgroundAssets;
    public OnscreenControlsAssets onscreenControlsAssets;

    //public TeleportationAssets teleportationAssets;
    private AssetManager assetManager;


    private Assets() {
    }


    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(TEXTURE_ATLAS);
        kirbyAssets = new KirbyAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        fireBallAssets = new FireBallAssets(atlas);
        enemyAssets = new EnemyAssets(atlas);
        explosionAssets = new ExplosionAssets(atlas);
        coinAssets = new CoinAssets(atlas);
        cloudAssets = new CloudAssets(atlas);
        backgroundAssets = new BackgroundAssets(atlas);
        exitPortalAssets = new ExitPortalAssets(atlas);
        onscreenControlsAssets = new OnscreenControlsAssets(atlas);
        soundAssets = new SoundAssets();

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);

    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class KirbyAssets {

        public final AtlasRegion standing;
        public final AtlasRegion jumping;
        public final AtlasRegion dead;
        public final AtlasRegion stageCleared;
        public final AtlasRegion gameOver;


        //public final Animation firingAnimation;

        public final Animation walkingAnimation;

        public KirbyAssets(TextureAtlas atlas) {
            standing = atlas.findRegion(STANDING);
            jumping = atlas.findRegion(JUMPING);
            dead = atlas.findRegion(DEAD);
            stageCleared = atlas.findRegion(STAGE_CLEARED);
            gameOver = atlas.findRegion(GAME_OVER);

            Array<TextureRegion> walkingFrames = new Array<TextureRegion>();
            walkingFrames.add(atlas.findRegion(WALK1));
            walkingFrames.add(atlas.findRegion(WALK2));
            walkingFrames.add(atlas.findRegion(WALK3));
            walkingFrames.add(atlas.findRegion(WALK4));
            walkingFrames.add(atlas.findRegion(WALK5));
            walkingFrames.add(atlas.findRegion(WALK6));
            walkingFrames.add(atlas.findRegion(WALK7));
            walkingFrames.add(atlas.findRegion(WALK8));
            walkingAnimation = new Animation(WALK_LOOP_DURATION, walkingFrames, PlayMode.LOOP);


            Array<TextureRegion> firingFrames = new Array<TextureRegion>();
            firingFrames.add(atlas.findRegion(OPEN_MOUTH));
            firingFrames.add(atlas.findRegion(CLOSED_MOUTH));

            //firingAnimation = new Animation(0.1f, firingFrames, PlayMode.LOOP);

        }

    }

    public class PlatformAssets {

        public final AtlasRegion platform;
        public final AtlasRegion grassHalfLeft;
        public final AtlasRegion grassHalfMid;
        public final AtlasRegion grassHalfRight;

        public final AtlasRegion sandHalfLeft;
        public final AtlasRegion sandHalfMid;
        public final AtlasRegion sandHalfRight;

        public PlatformAssets(TextureAtlas atlas) {
            platform = atlas.findRegion(BASE);
            grassHalfLeft = atlas.findRegion(GRASS_HALF_LEFT);
            grassHalfMid = atlas.findRegion(GRASS_HALF_MID);
            grassHalfRight = atlas.findRegion(GRASS_HALF_RIGHT);

            sandHalfLeft = atlas.findRegion(SAND_HALF_LEFT);
            sandHalfMid = atlas.findRegion(SAND_HALF_MID);
            sandHalfRight = atlas.findRegion(SAND_HALF_RIGHT);
        }
    }

    public class FireBallAssets {

        public final AtlasRegion fireBall;

        public FireBallAssets(TextureAtlas atlas) {
            fireBall = atlas.findRegion(FIREBALL);
        }
    }

    public class EnemyAssets {

        public final Animation crabAnimation;

        public EnemyAssets(TextureAtlas atlas) {

            Array<TextureRegion> walkingCrabFrames = new Array<TextureRegion>();
            walkingCrabFrames.add(atlas.findRegion(CRAB));
            walkingCrabFrames.add(atlas.findRegion(CRAB1));
            walkingCrabFrames.add(atlas.findRegion(CRAB2));
            walkingCrabFrames.add(atlas.findRegion(CRAB3));
            walkingCrabFrames.add(atlas.findRegion(CRAB4));
            walkingCrabFrames.add(atlas.findRegion(CRAB5));
            walkingCrabFrames.add(atlas.findRegion(CRAB6));
            walkingCrabFrames.add(atlas.findRegion(CRAB7));
            walkingCrabFrames.add(atlas.findRegion(CRAB8));
            walkingCrabFrames.add(atlas.findRegion(CRAB9));
            walkingCrabFrames.add(atlas.findRegion(CRAB10));
            walkingCrabFrames.add(atlas.findRegion(CRAB11));
            walkingCrabFrames.add(atlas.findRegion(CRAB12));
            walkingCrabFrames.add(atlas.findRegion(CRAB13));


            crabAnimation = new Animation(CRAB_WALK_LOOP, walkingCrabFrames, PlayMode.LOOP);
        }
    }


    public class ExplosionAssets {

        public final Animation superExplosionAnimation;
        public final Animation regularExplosionAnimation;
        public ExplosionAssets(TextureAtlas atlas) {

            Array<TextureRegion> superExplosionFrames = new Array<TextureRegion>();

            superExplosionFrames.add(atlas.findRegion(EXPLOSION1));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION2));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION3));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION4));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION5));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION6));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION7));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION8));
            superExplosionFrames.add(atlas.findRegion(EXPLOSION9));
            superExplosionAnimation = new Animation(EXPLOSION_FRAME_DURATION_SUPER, superExplosionFrames, PlayMode.NORMAL);


            Array<TextureRegion> regularExplosionFrames = new Array<TextureRegion>();
            regularExplosionFrames.add(atlas.findRegion(EXPLOSION_LITTLE1));
            regularExplosionAnimation = new Animation(EXPLOSION_FRAME_DURATION_REG, regularExplosionFrames, PlayMode.NORMAL);

        }
    }

    public class CoinAssets {

        public final AtlasRegion diamond;
        public final AtlasRegion apple;
        public final AtlasRegion berry;

        public CoinAssets(TextureAtlas atlas){

            diamond = atlas.findRegion(DIAMOND_COIN);
            berry = atlas.findRegion(BERRY_COIN);
            apple = atlas.findRegion(STRAWBERRY_COIN);
        }
    }

    public class CloudAssets {

        public final AtlasRegion cloud1;
        public final AtlasRegion cloud2;

        public CloudAssets(TextureAtlas atlas){
            cloud1 = atlas.findRegion(CLOUD1);
            cloud2 = atlas.findRegion(CLOUD2);
        }
    }

    public class BackgroundAssets {

        public final AtlasRegion cactus;
        public final AtlasRegion hillSmall;
        public final AtlasRegion plant;
        public final AtlasRegion rock;
        public final AtlasRegion bush;
        public final AtlasRegion fence;


        public BackgroundAssets(TextureAtlas atlas){
            cactus = atlas.findRegion(CACTUS);
            hillSmall = atlas.findRegion(HILL_SMALL);
            plant = atlas.findRegion(PLANT);
            rock = atlas.findRegion(ROCK);
            bush = atlas.findRegion(BUSH);
            fence = atlas.findRegion(BROKEN_FENCE);
        }
    }

    public class ExitPortalAssets{

        public final Animation exitPortalAnimation;

        public ExitPortalAssets(TextureAtlas atlas){

            Array<TextureRegion> exitPortalFrames = new Array<TextureRegion>();
            exitPortalFrames.add(atlas.findRegion(EXIT1));
            exitPortalFrames.add(atlas.findRegion(EXIT2));
            exitPortalFrames.add(atlas.findRegion(EXIT3));
            exitPortalFrames.add(atlas.findRegion(EXIT4));
            exitPortalFrames.add(atlas.findRegion(EXIT5));
            exitPortalFrames.add(atlas.findRegion(EXIT6));
            exitPortalFrames.add(atlas.findRegion(EXIT7));

            exitPortalAnimation = new Animation(0.2f , exitPortalFrames, PlayMode.LOOP);
        }
    }

    public class OnscreenControlsAssets {

        public final AtlasRegion moveRight;
        public final AtlasRegion moveLeft;
        public final AtlasRegion moveDown;
        public final AtlasRegion shoot;
        public final AtlasRegion jump;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(MOVE_LEFT_BUTTON);
            moveDown = atlas.findRegion(MOVE_DOWN_BUTTON);
            shoot = atlas.findRegion(SHOOT_BUTTON);
            jump = atlas.findRegion(JUMP_BUTTON);
        }
    }

    public class SoundAssets {
        public final Sound kirbyKnockOut;
        public final Sound fireBulletSound;
        public final Sound itemPickupSound;
        public final Music gameMusic;
        public Music levelCompleteSound;

        public SoundAssets(){
            kirbyKnockOut =  Gdx.audio.newSound(Gdx.files.internal(KIRBY_KNOCK_OUT_SOUND_PATH));
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal(GAME_MUSIC));
            levelCompleteSound = Gdx.audio.newMusic(Gdx.files.internal(LEVEL_COMPLETE_SOUND));
            fireBulletSound = Gdx.audio.newSound(Gdx.files.internal(FIREBALL_SOUND_FILE_PATH));
            itemPickupSound = Gdx.audio.newSound(Gdx.files.internal(ITEM_PICKUP_SOUND_FILE_PATH));
        }

    }
}













