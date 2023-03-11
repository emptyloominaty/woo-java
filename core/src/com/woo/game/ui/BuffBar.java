package com.woo.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.woo.game.GameInput;
import com.woo.game.GlobalVars;
import com.woo.game.objects.other.Buff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.*;

public class BuffBar {
    public Table bar;
    public Map<String, ArrayList> bars;
    ArrayList<Boolean> buffsActive = new ArrayList<>();
    ArrayList<Boolean> debuffsActive = new ArrayList<>();
    int n = 8;
    public Table buffTooltip;
    Drawable defaultDrawable;
    public BuffBar() {
        defaultDrawable = uiMain.skin.newDrawable("white", new Color(0.4f,0.4f,0.4f,0f));

        buffTooltip = new Table(uiMain.skin);
        buffTooltip.setBackground(uiMain.skin.newDrawable("white", new Color (0.3f,0.3f,0.3f,0.75f)));
        buffTooltip.setPosition(400,400);
        buffTooltip.align(Align.top);
        buffTooltip.setSize(250,50);
        uiMain.stageTop2.addActor(buffTooltip);
        buffTooltip.setVisible(false);


        bars = new HashMap<>();
        bars.put("buffsImage",new ArrayList<Image>());
        bars.put("buffsLabel",new ArrayList<Label>());
        bars.put("debuffsImage",new ArrayList<Image>());
        bars.put("debuffsLabel",new ArrayList<Label>());

        bar = new Table();

        bar.defaults().right();

        //Buffs
        for (int i = 0; i<n; i++) {
            buffsActive.add(false);
            Table table = new Table();
            Image image = new Image(uiMain.skin.newDrawable("white", new Color(0.4f,0.4f,0.4f,0f)));
            addTooltip(image,i);
            Label label = new Label("",uiMain.skin);
            label.setStyle(uiMain.labelStyle12);

            bars.get("buffsImage").add(image);
            bars.get("buffsLabel").add(label);

            table.add(image).size(32);
            table.row();
            table.add(label);

            bar.add(table).pad(2).size(34,50);

        }
        bar.row();
        bar.add(new Label("",uiMain.skin));
        bar.row();
        //Debuffs
        for (int i = 0; i<n; i++) {
            debuffsActive.add(false);
            Table table = new Table();
            Image image = new Image(uiMain.skin.newDrawable("white", new Color(0.4f,0.4f,0.4f,0f)));
            Label label = new Label("",uiMain.skin);
            label.setStyle(uiMain.labelStyle12);

            bars.get("debuffsImage").add(image);
            bars.get("debuffsLabel").add(label);

            table.add(image).size(32);
            table.row();
            table.add(label);

            bar.add(table).pad(2).size(34,50);

        }

        //bar.setDebug(true);

        uiMain.tableBuffs.add(bar);
        uiMain.stage.addActor(uiMain.tableBuffs);
        uiMain.tableBuffs.setSize(300,48);
        uiMain.tableBuffs.align(Align.bottom);
        uiMain.tableBuffs.setPosition(uiMain.stage.getWidth() - uiMain.tableBuffs.getWidth()-145, uiMain.tableBuffs.getHeight() - uiMain.tableBuffs.getHeight() + Gdx.graphics.getHeight()-145);

    }

    public void update() {

        ArrayList<Boolean> done = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            done.add(false);
        }

        //int ii = n-1;
        for (int i = 0; i<player.buffs.size(); i++) {
            Buff buff = player.buffs.get(i);
            if (!buff.ability.hiddenBuff) {
                buffsActive.set(i,true);
                Label label = (Label)this.bars.get("buffsLabel").get(i);
                label.setText(Math.round(buff.duration)+"s");
                Image image = (Image)this.bars.get("buffsImage").get(i);

                image.setDrawable(buff.ability.textureRegionDrawable);
                done.set(i,true);
                //ii--;
            }
            if (i<=0) {
                break;
            }
        }

        //reset
        for (int i = 0; i<n; i++) {
            if (!done.get(i)) {
                buffsActive.set(i,false);
                Label label = (Label)this.bars.get("buffsLabel").get(i);
                label.setText("");
                ((Image) this.bars.get("buffsImage").get(i)).setDrawable(defaultDrawable);
            }
        }


        //TODO:Debuffs
    }

    public void addTooltip(Image image, final int i) {
        image.addListener(
                new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if (buffsActive.get(i)) {
                            buffTooltip.clear();
                            int ii = i;
                            for (int i = 0; i<5; i++) {
                                if (player.buffs.get(i).ability.hiddenBuff) {
                                    ii++;
                                } else {
                                    break;
                                }
                            }
                            Buff buff = player.buffs.get(ii);
                            Label label = new Label(buff.ability.getBuffTooltip(buff.caster,player,buff),uiMain.skin);

                            Label abilityName = new Label(buff.ability.name,uiMain.skin);
                            abilityName.setStyle(uiMain.labelStyle16);
                            buffTooltip.add(abilityName).align(Align.left);
                            buffTooltip.row();
                            buffTooltip.add(label).align(Align.left);

                            buffTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                            buffTooltip.pack();
                            buffTooltip.setWidth(180);
                            buffTooltip.setVisible(true);
                        }
                    }
                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        buffTooltip.setVisible(false);
                    }
                    @Override
                    public boolean mouseMoved(InputEvent event, float x, float y) {
                        buffTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                        return false;
                    }
                }
        );
    }

}
