package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpecialKey extends Key {
    float wide;
    public SpecialKey(float x, float y, char letter, float wide, String display) {
        super(x, y, letter);
        this.wide = wide;
        this.display = display;
    }
    void render(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(x, y, wide, height/16F);
        sr.end();
    }
}