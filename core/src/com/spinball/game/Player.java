package com.spinball.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Created by thomaseyre on 21/09/2017.
 */

public class Player extends GameObject {
    private boolean inOrbit;
    private int orbitingBall;
    private int lives = 3;
    private int playerSpeed = 300;

    Player(Circle circle, Texture playerTexture){
        super(circle, playerTexture);
    }

    public boolean isInOrbit() {
        return inOrbit;
    }

    public void setInOrbit(boolean inOrbit) {
        this.inOrbit = inOrbit;
    }

    public int getOrbitingBall() {
        return orbitingBall;
    }

    public void setOrbitingBall(int orbitingBall) {
        this.orbitingBall = orbitingBall;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}

