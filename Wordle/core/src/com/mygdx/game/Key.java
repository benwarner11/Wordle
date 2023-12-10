package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Key {

    float x, y;
    int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    Color colour = Color.GRAY;
    char letter;
    FreeTypeFontGenerator fg = new FreeTypeFontGenerator(Gdx.files.internal("Franklin Gothic Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter fp = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;

    public Key(float x, float y, char letter){
        this.x = x;
        this.y = y;
        this.letter = letter;
        initialiseFont();
    }
    void render(ShapeRenderer sr, SpriteBatch batch, OrthographicCamera cam){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(x, y, width/12F, height/16F);
        sr.end();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        font.draw(batch, Character.toString(letter), x + 50, y + 50);
        batch.end();
    }
    void initialiseFont(){
        fp.size = 20;
        fp.color = Color.WHITE;
        font = fg.generateFont(fp);
    }
    void click(){
        if (Gdx.input.getX() > x && Gdx.input.getX() < x + width/12F && height - Gdx.input.getY() > y && height - Gdx.input.getY() < y + height/16F){
            Game.keyPress = letter;
            System.out.println(letter);
        }
    }
    void change(){

    }
}