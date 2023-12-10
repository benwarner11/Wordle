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
    FreeTypeFontGenerator fg = new FreeTypeFontGenerator(Gdx.files.internal("Franklin Gothic Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter fp = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;

    public Tile (int x, int y){
        this.x = x;
        this.y = y;
        initialiseFont();
        xpos = width/36F + x * (width/6F + width/36F);
        ypos = height - (y + 1) * width/36F - (y + 1) * width/6F;
    }
    void render(ShapeRenderer sr, SpriteBatch batch, OrthographicCamera cam){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(xpos, ypos, width/6F, width/6F);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(xpos, ypos, width/6F, width/6F);
        sr.end();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        font.draw(batch, Character.toString(letter), xpos + 50, ypos + 50);
        batch.end();
    }
    void initialiseFont(){
        fp.size = 20;
        fp.color = Color.BLACK;
        font = fg.generateFont(fp);
    }
    void flip(){

    }

}