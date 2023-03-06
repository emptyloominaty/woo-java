package com.woo.game;
import com.badlogic.gdx.Gdx;
import com.woo.game.objects.Item;
import com.woo.game.objects.abilities.Ability;

import java.util.HashMap;

public class GlobalVars {
    public static float delta;
    public static float fps;
    public static float camZoom = 1.0f;

    public static int pxToMeter = 11;
    public static float spellQueueWindow = 0.2f;

    public static boolean characterStats = false;
    public static boolean spellbook = false;
    public static boolean settings = false;
    public static boolean inventory = false;

    public static boolean draggingAbility = false;
    public static String dragAbilityName = "";
    public static Ability dragAbility;

    public static boolean draggingItem = false;
    public static String dragItemName = "";
    public static Item dragItem;

    //TODO:load and store
    public static float spellbookX = 40;
    public static float spellbookY = Gdx.graphics.getHeight()-560;
    public static float characterStatsX = 40;
    public static float characterStatsY = Gdx.graphics.getHeight()-540;
    public static float settingsX = 40;
    public static float settingsY = Gdx.graphics.getHeight()-540;
    //TODO:inventory


    public static void init() {
    }

    public static void reset() {
    }
}
