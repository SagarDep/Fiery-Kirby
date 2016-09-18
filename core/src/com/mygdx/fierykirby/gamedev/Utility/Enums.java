package com.mygdx.fierykirby.gamedev.Utility;

/**
 * Created by galaxywizkid on 8/24/16.
 */
public class Enums {


    public enum Direction {
        LEFT, RIGHT
    }

    public enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED,
    }

    public enum WalkState {
        STANDING,
        WALKING
    }

    public enum FireState {
        FIRING,
        NOTFIRING
    }

    // Spawnstate is used to disable movements when kirby is Spawning
    public enum SpawnState {
        SPAWNING,
        NOT_SPAWNING
    }

    public enum PositionOnPlatform{
        LEFT_MOST, MIDDLE, RIGHT_MOST
    }
}
