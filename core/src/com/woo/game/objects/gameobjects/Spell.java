package com.woo.game.objects.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.woo.game.GlobalFunctions;
import com.woo.game.GlobalVars;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Spell extends GameObject{
    public double life = 1.8;
    public double moveSpeed = 18;
    public float spellSizeX = 35f;
    public float spellSizeY = 35f;
    public boolean newP = false;

    public Creature caster;
    public int particleId;
    public boolean particle;
    public Ability ability;
    //Visual
    public Spell(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, float direction,int particleId,boolean particle, double moveSpeed, double life, Ability ability) {
        super(name, description, solid, interactable, x, y, spritePath,"Spell",direction);
        this.particleId = particleId;
        this.particle = particle;
        this.moveSpeed = moveSpeed;
        this.life = life;
        this.ability = ability;

        ParticleSystem.particleList.get(particleId).getEmitters().get(0).addParticles(25);
    }

    public void main() {
        life -= GlobalVars.delta;
        move(moveSpeed);
        if (particle) {
            ParticleSystem.setPosition(this.x,this.y,particleId);
        }
        if (life<0) {
            if (particle) {
                ParticleSystem.stop(particleId);
                ParticleSystem.startMoving(particleId,x,y,direction,moveSpeed);
            }
            destroy();
            return;
        }


        //TODO: FIX OPTIMIZE???
        for (WorldObject worldObject : GOControl.worldObjects) {
            if (!worldObject.canStopSpells && !worldObject.destroyed) {
                if (GlobalFunctions.checkCollision(this.x,this.y,spellSizeX,spellSizeY,worldObject.x,worldObject.y,worldObject.sizeX,worldObject.sizeY)) {
                    collision(worldObject);
                    break;
                }
            }
        }

        float spellSizeXR = spellSizeX/2;
        for (Creature creature : GOControl.creatures) {
            float distance = 999f;
            if (!creature.destroyed && creature!=caster && GlobalFunctions.checkEnemy(caster,creature)) {
                distance = GlobalFunctions.getDistance(this.x,this.y,creature.x,creature.y);
            }
            if (distance<(((creature.sizeX/2)+spellSizeXR)/GlobalVars.pxToMeter)) {
                collision(creature);
                break;
            }
        }


    }

    public void collision(Creature target) {
        ability.execute(caster,target,x,y);
        if (particle) {
            if (!newP) {
                ParticleSystem.stop(particleId);
                ParticleSystem.startMoving(particleId,x,y,direction,moveSpeed);
            } else {
                ParticleSystem.startMoving(particleId,x,y,direction,moveSpeed,0.13f,true);
            }

        }
        destroy();
    }
    public void collision(WorldObject target) {
        //TODO:
        if (particle) {
            ParticleSystem.stop(particleId);
            ParticleSystem.startMoving(particleId,x,y,direction,moveSpeed);
        }
        destroy();
    }

    public void draw(ShapeDrawer shapeDrawer) {
        if (!destroyed) {
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledCircle(x, y, 5);
        }
    }

}
