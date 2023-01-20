package com.woo.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParticleSystem {
    public static ArrayList<ParticleEffect> particleList;
    public static ArrayList<Integer> particleListFree;
    public static Map<String, String> particleTextures;

    public static void init() {
        particleList = new ArrayList<ParticleEffect>();
        particleListFree = new ArrayList<Integer>();
        particleTextures = new HashMap<String, String>();
        particleTextures.put("fire","particles/fire.particle");
        particleTextures.put("fire2","particles/fire2.particle");
        //TODO: particleTextures.put

    }

    public static int add(String particleTexture,int angle, float direction,float x, float y) {
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
            particleList.set(id,new ParticleEffect());
        }

        particleList.get(id).load(Gdx.files.internal(particleTextures.get(particleTexture)),Gdx.files.internal("textures"));
        particleList.get(id).getEmitters().first().setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);

        ParticleEmitter emitter = particleList.get(id).getEmitters().first();
        emitter.getAngle().setHigh(direction-angle,direction+angle);
        emitter.getAngle().setLow(direction);
        particleList.get(id).setPosition(x,y);
        particleList.get(id).start();
        return id;
    }

    public static void remove(int id) {
        particleList.get(id).dispose();
        particleList.set(id,null);
        particleListFree.add(id);
    }

    public static void setPosition(float x, float y, int id) {
        particleList.get(id).setPosition(x,y);
    }



}
