package com.gurkenlabs.clone.game.entities;


import de.gurkenlabs.litiengine.entities.*;

@CollisionInfo(collision = true)
public class Jorge extends Creature {
    public Jorge(){
        super("jorge");
        this.addController(new EnemyController(this));
    }
}
