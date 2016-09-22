package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fierykirby.gamedev.Level;
import com.mygdx.fierykirby.gamedev.Sprites.BackgroundSprite;
import com.mygdx.fierykirby.gamedev.Sprites.Cloud;
import com.mygdx.fierykirby.gamedev.Sprites.Enemy;
import com.mygdx.fierykirby.gamedev.Sprites.Platform;
import com.mygdx.fierykirby.gamedev.Utility.Enums.PositionOnPlatform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.Comparator;

import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD1;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.CLOUD2;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_9PATCHES;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_COMPOSITE;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_ENEMY2_TAG;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_ENEMY3_TAG;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_ENEMY_TAG;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_HEIGHT_KEY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_IDENTIFIER_KEY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_IMAGENAME_KEY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_SUPER_COMPOSITES;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_WIDTH_KEY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_X_KEY;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.LEVEL_Y_KEY;


/**
 * Created by galaxywizkid on 8/25/16.
 */
public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String levelName, Viewport viewport) {

        Level level = new Level(viewport);

        String path = Constants.LEVEL_DIR + File.separator + "scenes" + File.separator + levelName + "." + Constants.LEVEL_FILE_EXTENSION;

        FileHandle file = Gdx.files.internal(path);
        JSONParser parser = new JSONParser();

        try {
            JSONObject rootJsonObj = (JSONObject) parser.parse(file.reader());

            JSONObject composite = (JSONObject) rootJsonObj.get(LEVEL_COMPOSITE);
            //JSONArray platforms = (JSONArray) composite.get(LEVEL_IMAGES);
            JSONArray composedPlatforms = (JSONArray) composite.get(LEVEL_SUPER_COMPOSITES);
            JSONArray nonPlatformArr = (JSONArray) composite.get(LEVEL_9PATCHES);


            loadNonPlatformImages(nonPlatformArr, level);
            loadPlatforms(composedPlatforms, level);


        } catch (Exception e) {
            Gdx.app.error(TAG, e.getMessage());

            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);

        }
        return level;
    }

    private static void loadNonPlatformImages(JSONArray array, Level level) {
        Array<Cloud> cloudArray = new Array<Cloud>();
        Array<BackgroundSprite> bgSpritesArr = new Array<BackgroundSprite>();

        for (Object object: array){
            JSONObject cloudObject = (JSONObject) object;

            final float x = safeGetFloat(cloudObject, LEVEL_X_KEY);
            final float y = safeGetFloat(cloudObject, LEVEL_Y_KEY);
            final float width = safeGetFloat(cloudObject, LEVEL_WIDTH_KEY);
            final float height = safeGetFloat(cloudObject, LEVEL_HEIGHT_KEY);

            final String imageName = (String) cloudObject.get(LEVEL_IMAGENAME_KEY);


            // If 9Patch image is a cloud then create it and add it to cloud Array
            // else it must be a background sprite
            if(imageName.equals(CLOUD1) || imageName.equals(CLOUD2)) {
                Cloud cloud = new Cloud(x, y, width, height, imageName);
                cloudArray.add(cloud);
            }else{

                BackgroundSprite bgSprite = new BackgroundSprite(x, y, width, height, imageName);
                bgSpritesArr.add(bgSprite);
            }

        }
        level.getBackgroundSprites().addAll(bgSpritesArr);
        level.getClouds().addAll(cloudArray);
    }


    private static void loadPlatforms(JSONArray array, Level level) {

        Array<Platform> platformArray = new Array<Platform>();
        Array<Enemy> enemyArray = new Array<Enemy>();

        for (Object object : array) {

            JSONObject platformObject = (JSONObject) object;
            final String identifier = (String) platformObject.get(LEVEL_IDENTIFIER_KEY);

            // Get the platform's x and y position
            final float startingXPos = safeGetFloat(platformObject, LEVEL_X_KEY);
            final float y = safeGetFloat(platformObject, LEVEL_Y_KEY);
            final float totWidth = safeGetFloat(platformObject, LEVEL_WIDTH_KEY);


            JSONObject imageObjectComposite = (JSONObject) platformObject.get(LEVEL_COMPOSITE);
            JSONArray imagesArr = (JSONArray) imageObjectComposite.get(LEVEL_9PATCHES);

            // Sorts the platforms in the order left, mid, right
            Comparator<JSONObject> comparator = new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    return ((String) o1.get((LEVEL_IMAGENAME_KEY))).compareTo((String) o2.get(LEVEL_IMAGENAME_KEY));
                }
            };
            //imagesArr.sort(comparator);


            loadImages(imagesArr, startingXPos, y, platformArray);

            // Check if the platform identifier equals the LEVEL_ENEMY_TAG if it does spawn an enemy on top of that platform object
            // Spawn either 1, 2, or 3 enemies based on the platform identifier
            if (identifier != null && identifier.equals(LEVEL_ENEMY_TAG)) {
                Gdx.app.log(TAG, "Loaded 1 enemy on that platform");
                Enemy enemy = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.LEFT_MOST);
                enemyArray.add(enemy);

            } else if (identifier != null && identifier.equals(LEVEL_ENEMY2_TAG)) {
                Gdx.app.log(TAG, "Loaded 1 enemy on that platform");
                Enemy enemy = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.RIGHT_MOST);
                Enemy enemy2 = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.LEFT_MOST);

                enemyArray.add(enemy);
                enemyArray.add(enemy2);

            } else if (identifier != null && identifier.equals(LEVEL_ENEMY3_TAG)) {
                Gdx.app.log(TAG, "Loaded 1 enemy on that platform");
                Enemy enemy = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.RIGHT_MOST);
                Enemy enemy2 = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.LEFT_MOST);
                Enemy enemy3 = new Enemy(platformArray.get(platformArray.size - 1), startingXPos, startingXPos + totWidth, PositionOnPlatform.MIDDLE);

                enemyArray.add(enemy);
                enemyArray.add(enemy2);
                enemyArray.add(enemy3);

            }
        }

        level.getEnemies().addAll(enemyArray);
        level.getPlatforms().addAll(platformArray);

    }

    // Create new platforms using the JSONArray of images and their positions
    private static void loadImages(JSONArray array, float x, float y, Array<Platform> platformArray) {

        for (Object object : array) {

            JSONObject image9PatchObj = (JSONObject) object;

            final float width = safeGetFloat(image9PatchObj, LEVEL_WIDTH_KEY);
            final float height = safeGetFloat(image9PatchObj, LEVEL_HEIGHT_KEY);

            final String platformName = (String) image9PatchObj.get(LEVEL_IMAGENAME_KEY);

            Platform platform = new Platform(x, y, width, height, platformName);
            platformArray.add(platform);
            x += width - 0.1f;

        }

    }


    private static float safeGetFloat(JSONObject object, String key) {
        Number number = (Number) object.get(key);
        return (number == null) ? 0 : number.floatValue();
    }

}
