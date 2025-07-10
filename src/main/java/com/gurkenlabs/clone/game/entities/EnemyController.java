package com.gurkenlabs.clone.game.entities;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.behavior.AStarPathFinder;
import de.gurkenlabs.litiengine.entities.behavior.EntityNavigator;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;

public class EnemyController implements IBehaviorController {
    private final Creature enemy;
    private final EntityNavigator navigator;
    private int distanceFromTarget;
    private final int rangeOfVision;
    private boolean canSeeTarget;

    public EnemyController(Creature enemy) {
        this.enemy = enemy;
        this.distanceFromTarget = 0;
        this.rangeOfVision = 100;
        this.canSeeTarget = false;
        this.navigator = new EntityNavigator(this.enemy, new AStarPathFinder(Game.world().environment().getMap()));
    }

    @Override
    public IEntity getEntity() {
        return this.enemy;
    }

    private void chaseTarget(){
        this.navigator.navigate(Player.instance().getCenter());
    }

    private void turnToTarget(){
        Direction direction;

        if(Player.instance().getLocation().getX() < this.getEntity().getLocation().getX()){
            direction = Direction.LEFT;
        }else {
            direction = Direction.RIGHT;
        }

        this.getEntity().setAngle(direction.toAngle());
    }

    @Override
    public void update() {
        if(this.enemy.isDead()){
            return;
        }

        this.distanceFromTarget = (int) this.getEntity().getCenter().distance(Player.instance().getCenter());
        this.canSeeTarget = this.distanceFromTarget <= this.rangeOfVision;

        if(this.canSeeTarget){
            this.turnToTarget();
            this.chaseTarget();
        }
    }
}
