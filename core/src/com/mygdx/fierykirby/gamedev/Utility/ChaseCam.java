package com.mygdx.fierykirby.gamedev.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.fierykirby.gamedev.Sprites.Kirby;
import static com.mygdx.fierykirby.gamedev.Utility.Constants.*;

/**
 * Created by galaxywizkid on 8/29/16.
 */
public class ChaseCam {

    private final Camera camera;
    private final Kirby target;
    private boolean following;

    public ChaseCam(Camera camera, Kirby target) {
        this.camera = camera;
        this.target = target;
        following = true;
    }

    public void update(float delta){

        if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
            if (following) {
                following = false;
            }else {
                following = true;
            }
        }

        if(following){
            camera.position.x = target.getPosition().x;
            camera.position.y = 80;

        }else {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                camera.position.x -= delta * CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.D)) {
                camera.position.x += delta * CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.W)) {
                camera.position.y += delta * CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Keys.S)) {
                camera.position.y -= delta * CHASE_CAM_MOVE_SPEED;
            }
        }
    }
}
