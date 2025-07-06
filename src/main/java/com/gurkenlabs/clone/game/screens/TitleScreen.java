package com.gurkenlabs.clone.game.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleScreen extends Screen {
    final String startText = "START";
    ImageComponent startComponent;
    BufferedImage startButton;

    public TitleScreen() {
        super("TITLE-SCREEN");
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.drawImage(Resources.images().get("sprites/gurknukem-logo.png"), 0, 0, Game.window().getWidth(), Game.window().getHeight(), null);

        startComponent.render(g);
    }

    @Override
    public void prepare() {
        super.prepare();

        Game.audio().playMusic("audio/titletrack.ogg");
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();

        startButton = Resources.images().get("sprites/grey_button06.png");

        startComponent = new ImageComponent((Game.window().getWidth() - startButton.getWidth()) / 2f, (Game.window().getHeight() - startButton.getHeight()) - 100,
                startButton);

        startComponent.setText(startText);

        startComponent.onClicked(e -> {
            suspend();

            Game.screens().display("INGAME-SCREEN");
        });

        getComponents().add(startComponent);
    }
}
