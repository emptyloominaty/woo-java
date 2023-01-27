package com.woo.game.objects;

import java.util.HashMap;
import java.util.Map;

public class Keybinds {
    public static Map<String, String[]> keys;

    public static void init() {
        keys = new HashMap<String, String[]>();

        keys.put("ActionBar0_0", new String[]{"NUM_1", ""});
        keys.put("ActionBar0_1", new String[]{"NUM_2", ""});
        keys.put("ActionBar0_2", new String[]{"NUM_3", ""});
        keys.put("ActionBar0_3", new String[]{"NUM_4", ""});
        keys.put("ActionBar0_4", new String[]{"NUM_5", ""});

        keys.put("ActionBar0_5", new String[]{"F1", ""});
        keys.put("ActionBar0_6", new String[]{"F2", ""});
        keys.put("ActionBar0_7", new String[]{"F3", ""});
        keys.put("ActionBar0_8", new String[]{"F4", ""});

        keys.put("ActionBar0_9", new String[]{"Q", ""});
        keys.put("ActionBar0_10", new String[]{"E", ""});
        keys.put("ActionBar0_11", new String[]{"R", ""});
        keys.put("ActionBar0_12", new String[]{"T", ""});
        keys.put("ActionBar0_13", new String[]{"Y", ""});
        keys.put("ActionBar0_14", new String[]{"Z", ""});

        keys.put("ActionBar1_0", new String[]{"NUM_1", "CONTROL_LEFT"});
        keys.put("ActionBar1_1", new String[]{"NUM_2", "CONTROL_LEFT"});
        keys.put("ActionBar1_2", new String[]{"NUM_3", "CONTROL_LEFT"});
        keys.put("ActionBar1_3", new String[]{"NUM_4", "CONTROL_LEFT"});
        keys.put("ActionBar1_4", new String[]{"NUM_5", "CONTROL_LEFT"});

        keys.put("ActionBar1_5", new String[]{"NUM_1", "SHIFT_LEFT"});
        keys.put("ActionBar1_6", new String[]{"NUM_2", "SHIFT_LEFT"});
        keys.put("ActionBar1_7", new String[]{"NUM_3", "SHIFT_LEFT"});
        keys.put("ActionBar1_8", new String[]{"NUM_4", "SHIFT_LEFT"});
        keys.put("ActionBar1_9", new String[]{"NUM_5", "SHIFT_LEFT"});

        keys.put("ActionBar1_10", new String[]{"Q", "SHIFT_LEFT"});
        keys.put("ActionBar1_11", new String[]{"E", "SHIFT_LEFT"});
        keys.put("ActionBar1_12", new String[]{"R", "SHIFT_LEFT"});
        keys.put("ActionBar1_13", new String[]{"T", "SHIFT_LEFT"});
        keys.put("ActionBar1_14", new String[]{"Y", "SHIFT_LEFT"});

        keys.put("Move_Up", new String[]{"W", ""});
        keys.put("Move_Down", new String[]{"S", ""});
        keys.put("Move_Left", new String[]{"A", ""});
        keys.put("Move_Right", new String[]{"D", ""});

        keys.put("Character Info", new String[]{"C", ""});
        keys.put("SpellBook", new String[]{"P", ""});
        keys.put("Talents", new String[]{"N", ""});
        keys.put("Map", new String[]{"M", ""});


    }

    //TODO:LOAD

    //TODO:STORE
}
