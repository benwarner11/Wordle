package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tile {

    int x, y, width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    float xpos, ypos;
    Color colour = Color.WHITE;
    char letter = ' ';

    public Tile (int x, int y){
        this.x = x;
        this.y = y;
        xpos = width/36F + x * (width/6F + width/36F);
        ypos = height - (y + 1) * width/36F - (y + 1) * width/6F;
    }
    void render(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(xpos, ypos, width/6F, width/6F);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(xpos, ypos, width/6F, width/6F);
        sr.end();
    }
    void flip(){

    }

}
