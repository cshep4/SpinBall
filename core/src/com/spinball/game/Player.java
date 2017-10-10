package com.spinball.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;

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

    public void isCollided(ArrayList<Ball> balls) {
        Circle tempBall;
        for (int i=0; i<balls.size(); i++) {
            if (i != this.getOrbitingBall()) {
                tempBall = new Circle(balls.get(i).getCircle().x, balls.get(i).getCircle().y, balls.get(i).getCircle().radius);
                if (this.getCircle().overlaps(tempBall)) {
                    this.setOrbitingBall(i);
                    this.setInOrbit(true);

                    double rotationAngle = balls.get(i).calcRotationAngle(this.getCircle().x, this.getCircle().y);
                    balls.get(i).setRotationAngle(rotationAngle);
                }
            }
        }
    }
}

