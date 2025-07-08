package com.gurkenlabs.clone.game.entities;

import com.gurkenlabs.clone.game.abilities.Jump;
import com.gurkenlabs.clone.game.logic.GurkNukemLogic;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;

import java.awt.geom.Rectangle2D;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
    public static final int MAX_ADDITIONAL_JUMPS = 1;

    private static Player instance;
    private final Jump jump;
    private int consecutiveJumps;

    private Player() {
        super("gurknukem");

        // setup the player's abilities
        this.jump = new Jump(this);
    }

    public static Player instance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }

    @Override
    public void update() {
        // reset the number of consecutive jumps when touching the ground
        if (this.isTouchingGround()) {
            this.consecutiveJumps = 0;
        }
    }

    @Override
    protected IMovementController createMovementController() {
        // setup movement controller
        return new PlatformingMovementController<>(this);
    }

    /**
     * Checks whether this instance can currently jump and then performs the <code>Jump</code> ability.
     * <p>
     * <i>Note that the name of this methods needs to be equal to {@link PlatformingMovementController#JUMP_ACTION}} in order for the controller to be able to
     * use this method. <br>
     * Another option is to explicitly specify the <code>Action.name()</code> attribute on the annotation.</i>
     * </p>
     */
    @Action(description = "This performs the jump ability for the player's entity.")
    public void jump() {
        if (this.consecutiveJumps >= MAX_ADDITIONAL_JUMPS || this.jump.isOnCooldown()) {
            return;
        }

        if (this.jump.canCast() && this.isTouchingGround()) {
            this.jump.cast();
            this.consecutiveJumps++;
        }
    }

    private boolean isTouchingGround() {
        // the idea of this ground check is to extend the current collision box by
        // one pixel and see if
        // a) it collides with any static collision box
        Rectangle2D groundCheck = new Rectangle2D.Double(getCollisionBox().getX(), getCollisionBox().getY(), getCollisionBoxWidth(), getCollisionBoxHeight() + 1);

        // b) it collides with the map's boundaries
        if (groundCheck.getMaxY() > Game.physics().getBounds().getMaxY()) {
            GurkNukemLogic.respawn("enter");
            return true;
        }

        return Game.physics().collides(groundCheck, Collision.STATIC);
    }
}
