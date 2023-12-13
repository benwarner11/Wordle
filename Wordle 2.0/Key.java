package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Key {

    float x, y;
    int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    Color colour = Color.GRAY;
    char letter;
    String display = "";

    public Key(float x, float y, char letter){
        this.x = x;
        this.y = y;
        this.letter = letter;
    }
    void render(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(x, y, width/12F, height/16F);
        sr.end();
    }
    void click(){
        if (Gdx.input.getX() > x && Gdx.input.getX() < x + width/12F && height - Gdx.input.getY() > y && height - Gdx.input.getY() < y + height/16F && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Game.keyPress = letter;
            System.out.println(letter);
        }
    }
}