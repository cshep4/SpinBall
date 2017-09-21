package com.spinball.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Created by CShepherd on 21/09/2017.
 */

public class Ball extends GameObject {
    private int direction;
    private int speed;
    private int gravity;
    private double rotationAngle;

    Ball(Circle circle, Texture texture) {
        super(circle, texture);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
