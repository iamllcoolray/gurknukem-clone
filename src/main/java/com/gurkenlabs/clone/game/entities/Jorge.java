package com.gurkenlabs.clone.game.entities;


import de.gurkenlabs.litiengine.entities.*;

@EntityInfo(width = 18, height = 18)
@AnimationInfo(spritePrefix = "Jorge")
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
// @MovementInfo(velocity = 70)
public class Jorge extends Creature {
    public Jorge(){
        this.addController(new EnemyController(this));
    }
}
