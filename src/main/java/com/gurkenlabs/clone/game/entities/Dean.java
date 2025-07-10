package com.gurkenlabs.clone.game.entities;

import de.gurkenlabs.litiengine.entities.*;

@CollisionInfo(collision = true)
public class Dean extends Creature {
    public Dean(){
        super("dean");
        this.addController(new EnemyController(this));
    }
}
