package com.nanyang.app.main.Setting.Pop;

/**
 * Created by Administrator on 2019/5/8.
 */

public class SoundBean implements IString {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;
    String text;

    public SoundBean(String text, int sound,String type) {
        this.text = text;
        this.sound = sound;
        this.type = type;
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
