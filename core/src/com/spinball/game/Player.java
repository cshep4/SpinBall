package com.spinball.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Created by thomaseyre on 21/09/2017.
 */

public class Player {

    private Circle circle;
    private Texture playerTexture;

    Player(Circle circle, Texture playerTexture){
        this.circle = circle;
        this.playerTexture = playerTexture;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }
}

