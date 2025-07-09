package com.gurkenlabs.clone.game.logic;

import com.gurkenlabs.clone.game.entities.Player;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

public final class PlayerInputLogic {
    private PlayerInputLogic(){

    }

    public static void init() {
        // make the game exit upon pressing ESCAPE (by default there is no such key binding and the window needs to be shutdown otherwise, e.g. ALT-F4 on Windows)
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_W, e -> Player.instance().jump());
    }
}
