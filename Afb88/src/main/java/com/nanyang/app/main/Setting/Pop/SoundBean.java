package com.nanyang.app.main.Setting.Pop;

/**
 * Created by Administrator on 2019/5/8.
 */

public class SoundBean implements IString {
    String text;

    public SoundBean(String text, int sound) {
        this.text = text;
        this.sound = sound;
    }

    public int getSound() {
        return sound;
    }

    int sound;

    @Override
    public String getText() {
        return text;
    }
}
