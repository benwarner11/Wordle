package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tile {

    int x, y, width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    float xpos, ypos, rotationFactor = 0 , speed = 1/25F;
    Color colour = Color.WHITE, newColour = Color.WHITE;
    char letter = ' ';
    boolean rotationDirection = true, flipped = false;

    public Tile (int x, int y){
        this.x = x;
        this.y = y;
        xpos = width/36F + x * (width/6F + width/36F);
        ypos = height - (y + 1) * width/36F - (y + 1) * width/6F;
    }
    void render(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(colour);
        sr.rect(xpos, ypos + rotationFactor * width/12F, width/6F, width/6F - rotationFactor * width/6F);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(xpos, ypos + rotationFactor * width/12F, width/6F, width/6F - rotationFactor * width/6F);
        sr.end();
    }
    void flip(){
        if (rotationDirection){
            rotationFactor += speed;
            if (rotationFactor >= 1){
                rotationDirection = false;
            }
        } else {
            colour = newColour;
            rotationFactor -= speed;
            if (rotationFactor <= 0){
                rotationDirection = true;
                flipped = true;
            }
        }
    }
}