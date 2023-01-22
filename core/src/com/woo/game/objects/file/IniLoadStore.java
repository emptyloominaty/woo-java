package com.woo.game.objects.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;
import java.util.Map;

public class IniLoadStore {
    public static Map<String,String> load(String path, String defaultData) {
        FileHandle file = Gdx.files.local(path);
        if (!Gdx.files.local(path).exists()) {
            file.writeString(defaultData, false);
        }
        String text = file.readString();
        String[] text2 = text.split("\n");
        Map<String,String> text3 = new HashMap<String, String>();
        for (int i = 0; i<text2.length; i++) {
            String[] a = text2[i].split("=");
            if (a.length>1) {
                text3.put(a[0],a[1]);
            }
        }
        return text3;
    }

    public static void reset(String path, String defaultData) {
        FileHandle file = Gdx.files.local(path);
        file.writeString(defaultData, false);
    }

    public static void store(String path, String data, Boolean append) {
        FileHandle file = Gdx.files.local(path);
        file.writeString(data, append);
    }

}
