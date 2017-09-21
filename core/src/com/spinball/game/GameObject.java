package com.spinball.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Created by CShepherd on 21/09/2017.
 */

public class GameObject {
    private Circle circle;
    private Texture texture;

    public GameObject(Circle circle, Texture texture) {
        this.circle = circle;
        this.texture = texture;
    }


    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
