package com.gurkenlabs.clone.game.logic;

import com.gurkenlabs.clone.game.entities.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.LocationLockCamera;
import de.gurkenlabs.litiengine.resources.Resources;

public final class GurkNukemLogic {
    /**
     * Initializes the game logic for the GURK NUKEM game.
     */
    public static void init() {
        // we'll use a camera in our game that is locked to the location of the player
        Camera camera = new LocationLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);

        // set a basic gravity for all levels.
        Game.world().setGravity(120);

        // add default game logic for when a level was loaded
        Game.world().onLoaded(e -> {
            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("enter");
            if (enter != null) {
                enter.spawn(Player.instance());
            }
        });
    }
}
