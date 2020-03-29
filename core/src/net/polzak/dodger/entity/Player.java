package net.polzak.dodger.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Logger;

public class Player {

    private static final Logger log = new Logger(Player.class.getName(), Logger.DEBUG);

    private float x;
    private float y;
    private static final float RADIUS = 0.4f;
    private static final float SIZE = 2 * RADIUS;
    private static final float MAX_X_SPEED = 0.025f;

    private Circle bounds;

    public Player() {
        bounds = new Circle(x, y, RADIUS);

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        bounds.setPosition(x, y);
    }

    public void drawDebug(ShapeRenderer renderer) {
        //renderer.circle(x, y, RADIUS, 30);

        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }

    public void update() {
        float xSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xSpeed = MAX_X_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xSpeed = -MAX_X_SPEED;
        }

        x += xSpeed;
        setPosition(x, y);
//        log.debug("x = " + x);
    }




}
