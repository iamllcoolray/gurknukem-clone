package com.gurkenlabs.clone.game;

import com.gurkenlabs.clone.game.logic.GurkNukemLogic;
import com.gurkenlabs.clone.game.logic.PlayerInputLogic;
import com.gurkenlabs.clone.game.screens.InGameScreen;
import com.gurkenlabs.clone.game.screens.TitleScreen;
import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import de.gurkenlabs.litiengine.resources.Resources;

/**
*
* @see <a href="https://litiengine.com/docs/">LITIENGINE Documentation</a>
*
*/

public class Main {
    public static void main(String[] args) {
        // set meta information about the game
        Game.info().setName("GurkNukemClone");
        Game.info().setSubTitle("");
        Game.info().setVersion("v0.0.1");
        Game.info().setWebsite("link to game");
        Game.info().setDescription("A 2D Game made in the LITIENGINE");

        // set game window to fullscreenu
        Game.config().graphics().setDisplayMode(DisplayMode.FULLSCREEN);

        // init the game infrastructure
        Game.init(args);

        // set the icon for the game (this has to be done after initialization because the ScreenManager will not be present otherwise)
        Game.window().setIcon(Resources.images().get("sprites/icon.png"));
        Game.graphics().setBaseRenderScale(6f);

        // load data from the utiLITI game file
        Resources.load("game.litidata");

        PlayerInputLogic.init();
        GurkNukemLogic.init();

        Game.screens().add(new TitleScreen());
        Game.screens().add(new InGameScreen());

        // load the first level (resources for the map were implicitly loaded from the game file)
        Game.world().loadEnvironment("level1");
        Game.start();
    }
}
