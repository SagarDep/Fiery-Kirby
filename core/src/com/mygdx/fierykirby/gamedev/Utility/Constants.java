package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by galaxywizkid on 8/23/16.
 */
public interface Constants {

    // World
    Color BACKGROUND_COLOR = Color.SKY;
    float WORLD_SIZE = 200;
    String TEXTURE_ATLAS = "images/Sprites.txt";
    float DEFAULT_SCALE = 1.0f;
    float GROUND_HEIGHT = 12.5f;
    float LEVEL_END_DURATION = 5f;

    // HUD
    float HUD_MARGIN = 15;
    String HUD_SCORE_LABEL = "Score: ";
    float HUD_VIEWPORT_SIZE = 480;
    float KIRBY_HUD_SCALE = 0.7f;
    float SCORE_100 = 100;

    // Kirby
    String OPEN_MOUTH = "open_mouth";
    String CLOSED_MOUTH = "closed_mouth";
    String STANDING = "standing";
    String WALK1 = "walk1";
    String WALK2 = "walk2";
    String WALK3 = "walk3";
    String WALK4 = "walk4";
    String WALK5 = "walk5";
    String WALK6 = "walk6";
    String WALK7 = "walk7";
    String WALK8 = "walk8";
    String FIREBALL = "kirby-bullets";
    String JUMPING = "jump2";
    String DEAD = "dead_kirby";



    String BREATHE_FIRE = "breathe_fire";
    String EXTRA_LIFE = "extra_life";
    String CLOUD_ENEMY = "cloud_enemy";
    float KIRBY_SCALE = 0.7f;
    float KIRBY_FOOT_WIDTH = 15.0f;
    float KIRBY_HEIGHT = 20.0f;

    float KIRBY_MOVE_SPEED = 90f;
    float GRAVITY = 650;
    float JUMP_SPEED = 250;
    float WALK_LOOP_DURATION = 0.1f;
    float KIRBY_FALL_OFFSET = 0.5f;
    int INITIAL_KIRBY_LIVES = 3;
    Vector2 KIRBY_OFFSET_TO_MID_POS = new Vector2(10, 10);

    // Platform
    String BASE = "grass";
    String GRASS_HALF_LEFT= "grassHalfLeft";
    String GRASS_HALF_MID= "grassHalfMid";
    String GRASS_HALF_RIGHT= "grassHalfRight";
    String SAND_HALF_LEFT= "sandHalfLeft";
    String SAND_HALF_MID= "sandHalfMid";
    String SAND_HALF_RIGHT= "sandHalfRight";


    //Enemy
    String CRAB = "crab";
    String CRAB1 = "crab1";
    String CRAB2 = "crab2";
    String CRAB3 = "crab3";
    String CRAB4 = "crab4";
    String CRAB5 = "crab5";
    String CRAB6 = "crab6";
    String CRAB7 = "crab7";
    String CRAB8 = "crab8";
    String CRAB9 = "crab9";
    String CRAB10 = "crab10";
    String CRAB11= "crab11";
    String CRAB12= "crab12";
    String CRAB13= "crab13";

    float CRAB_SPEED = 10f;
    float CRAB_SCALE = 0.8f;
    float CRAB_WIDTH = 20f;
    float CRAB_HEIGHT= 21f;
    float CRAB_WALK_LOOP = 0.1f;
    int ENEMY_HEALTH = 5;
    float ENEMY_DEAD_OFFSET = 6f;



    // Fireball
    float FIREBALL_SPEED = 150f;
    float FIREBALL_SCALE = 0.6f;
    Vector2 FIREBALL_OFFSET_FROM_KIRBY = new Vector2(13, 2);
    float BULLET_LENGTH = 17f;

    // Explosion
    String EXPLOSION1 = "explosion1";
    String EXPLOSION2 = "explosion2";
    String EXPLOSION3 = "explosion3";
    String EXPLOSION4 = "explosion4";
    String EXPLOSION5 = "explosion5";
    String EXPLOSION6 = "explosion6";
    String EXPLOSION7 = "explosion7";
    String EXPLOSION8 = "explosion8";
    String EXPLOSION9 = "explosion9";
    String EXPLOSION_LITTLE1 = "explosion_little1";
    String EXPLOSION_REGULAR = "regular";
    String EXPLOSION_SUPER = "super";
    float EXPLOSION_FRAME_DURATION_SUPER = 0.1f;
    float EXPLOSION_FRAME_DURATION_REG = 0.2f;


    // Coins
    String DIAMOND_COIN = "diamond_coin";
    String BERRY_COIN = "berry";
    String STRAWBERRY_COIN = "strawberry";

    float DIAMOND_SCALE = 0.8f;

    // Coin amounts
    float TIME_ONSCREEN = 0.5f;
    float TEXT_SPEED = 15f;

    // Sounds
    String ITEM_PICKUP_SOUND_FILE_PATH = "sounds/item_pickup_sound.wav";
    String KIRBY_KNOCK_OUT_SOUND_PATH = "sounds/Twow.wav";
    String GAME_MUSIC = "sounds/song1.ogg";
    String LEVEL_COMPLETE_SOUND = "sounds/success.wav";
    String FIREBALL_SOUND_FILE_PATH = "sounds/kirby_spit.wav";
    float FIREBALL_SOUND_VOLUME = 0.3f;
    float COIN_SOUND_VOLUME = 0.6f;


    // Cloud
    float CLOUD1_MOVE_SPEED = 12f;
    float CLOUD2_MOVE_SPEED = 10f;
    String CLOUD1 = "cloud1";
    String CLOUD2 = "cloud2";

    // Exit portal
    String EXIT1 = "exit1";
    String EXIT2 = "exit2";
    String EXIT3 = "exit3";
    String EXIT4 = "exit4";
    String EXIT5 = "exit5";
    String EXIT6 = "exit6";
    String EXIT7 = "exit7";

    Vector2 EXIT_PORTAL_OFFSET_TO_MIDDLE_POS = new Vector2(25, 5);
    float DIST_APART = 15f;
    float EXIT_PORTAL_SPEED = 30f;
    Vector2 INITIAL_POSITION = new Vector2(100, 91);


    // Overlay
    String STAGE_CLEARED = "stage_cleared";
    String GAME_OVER = "the_end";

    Vector2 INITIAL_OVERLAY_POS = new Vector2(300, 50);
    float OVERLAY_SPEED = 200f;
    float OVERLAY_SCALE = 0.4f;

    // Background Sprites - grass, shrubs etc
    String PLANT = "plant";
    String HILL_SMALL = "hillsmall";
    String CACTUS = "cactus";
    String BUSH = "bush";
    String ROCK = "rock";
    String BROKEN_FENCE = "fenceBroken";


    // Level Loading
    String LEVEL_DIR = "levels";
    String LEVEL_FILE_EXTENSION = "dt";
    String LEVEL_COMPOSITE = "composite";
    String LEVEL_9PATCHES = "sImage9patchs";
    String LEVEL_IMAGES = "sImages";
    String LEVEL_SUPER_COMPOSITES = "sComposites";
    String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    String LEVEL_IMAGENAME_KEY = "imageName";
    String LEVEL_X_KEY = "x";
    String LEVEL_Y_KEY = "y";
    String CUSTOM_VARS = "customVars";
    String LEVEL_WIDTH_KEY = "width";
    String LEVEL_HEIGHT_KEY = "height";
    String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    String LEVEL_ENEMY_TAG = "Enemy";
    String LEVEL_ENEMY2_TAG = "Enemy2";
    String LEVEL_ENEMY3_TAG = "Enemy3";


}
