package com.woo.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.woo.game.GlobalVars;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.GOControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParticleSystem {
    public static ArrayList<ParticleEffect> particleList;
    public static ArrayList<Integer> particleListFree;
    public static Map<Integer, MovingParticle> movingParticles;
    public static Map<String, String> particleFiles;
    public static ArrayList<Integer> removeMovingParticles;

    public static void init() {
        particleList = new ArrayList<ParticleEffect>();
        particleListFree = new ArrayList<Integer>();
        particleFiles = new HashMap<String, String>();
        particleFiles.put("fire","particles/fire.particle");
        particleFiles.put("fire2","particles/fire2.particle");
        particleFiles.put("fire64","particles/fire64.particle");
        //TODO: particleFiles.put
        movingParticles = new HashMap<Integer, MovingParticle>();
        removeMovingParticles = new ArrayList<Integer>();
    }

    public static int add(String particleFile,int angle, float direction,float x, float y) {
        direction = 270 - direction;
        int id = 0;

        if (particleListFree.size()>0) {
            int idF = particleListFree.size()-1;
            id = particleListFree.get(idF);
            particleListFree.remove(idF);
        } else {
            id = particleList.size();
        }
        if (particleList.size()<=id) {
            particleList.add(new ParticleEffect());
        } else {
            //TODO:REMOVE this + dispose + null??????????????
            //TODO:remove() particleList.get(id).reset();????
            particleList.set(id,new ParticleEffect());
        }

        particleList.get(id).load(Gdx.files.internal(particleFiles.get(particleFile)),Gdx.files.internal("Textures"));
        particleList.get(id).getEmitters().first().setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);

        ParticleEmitter emitter = particleList.get(id).getEmitters().first();
        emitter.getAngle().setHigh(direction-angle,direction+angle);
        emitter.getAngle().setLow(direction);
        particleList.get(id).setPosition(x,y);
        particleList.get(id).start();
        return id;
    }

    public static void remove(int id) {
        if (movingParticles.containsKey(id)) {
            movingParticles.remove(id);
        }
        particleList.get(id).allowCompletion();
        particleList.get(id).dispose();
        particleList.set(id,null);
        particleListFree.add(id);
    }

    public static void stop(int id) {
        particleList.get(id).allowCompletion();
    }

    public static void setPosition(float x, float y, int id) {
        particleList.get(id).setPosition(x,y);
    }

    public static void startMoving(int id, float x, float y, float direction, double velocity) {
        movingParticles.put(id, new MovingParticle(id,x,y,direction,velocity));

    }

    public static void run() {
        for (Map.Entry<Integer, MovingParticle> mp : movingParticles.entrySet()) {
            movingParticles.get(mp.getKey()).move();
        }
        for (int i = 0; i<removeMovingParticles.size();i++) {
            movingParticles.remove(movingParticles.remove(removeMovingParticles.get(i)));
        }
        removeMovingParticles = new ArrayList<Integer>();
    }
}

class MovingParticle {
    float x;
    float y;
    float direction;
    double velocity;
    float life = 1;
    int id;
    MovingParticle(int id, float x, float y, float direction, double velocity) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.velocity = velocity;
        this.id = id;
    }
    void move() {
        this.life -= GlobalVars.delta;
        if (this.life<0) {
            ParticleSystem.removeMovingParticles.add(this.id);
            return;
        }
        double speed = (this.velocity * GlobalVars.pxToMeter) * GlobalVars.delta;
        double angleInRadian = 0;
        angleInRadian = (direction-180) / 180 * Math.PI;
        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;
        this.x += vx;
        this.y += vy;
        ParticleSystem.particleList.get(id).setPosition(this.x,this.y);
    }
}
