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
    private long directionChanged, nextDirectionChange;
    private Direction direction;
    private final EntityNavigator navigator;
    private int distanceFromTarget, rangeOfVision;
    private boolean canSeeTarget;

    public EnemyController(Creature enemy) {
        this.enemy = enemy;
        this.distanceFromTarget = 0;
        rangeOfVision = 100;
        this.canSeeTarget = false;
        this.navigator = new EntityNavigator(this.enemy, new AStarPathFinder(Game.world().environment().getMap()));
    }

    @Override
    public IEntity getEntity() {
        return this.enemy;
    }

    private void chaseTarget(){
        navigator.navigate(Player.instance().getCenter());
    }

    @Override
    public void update() {
        if(this.enemy.isDead()){
            return;
        }

        distanceFromTarget = (int) this.getEntity().getCenter().distance(Player.instance().getCenter());
        canSeeTarget = distanceFromTarget <= rangeOfVision;

        if(canSeeTarget){
            chaseTarget();
        }


    }
}
