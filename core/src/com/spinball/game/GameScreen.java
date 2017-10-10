package com.spinball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.ArrayList;
import java.util.Random;


public class GameScreen implements Screen {

    private final SpinBall game;
    private float screenXMax = 450;
    private float screenYMax = 0;
    private OrthographicCamera camera;

    private final int LEFT = 1;
    private final int RIGHT = -1;

    //--------------TEXTURES
    Texture ball;
    //----------

    //---------------PLAYER
    Player player;
    Circle playerCircle;
    float player_radius = 30;

    //---------------BALLS
    ArrayList<Ball> balls = new ArrayList<Ball>();
    Circle ballCircle;
    float ball_radius;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    //----------------------

    public GameScreen(final SpinBall game) {
        this.game = game;

        // create the camera and make sure it looks the same across all devices---------------------
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        float ratio = width / height;
        screenYMax = screenXMax / ratio;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenXMax, screenYMax);
        //------------------------------------------------------------------------------------------

        //----------------TEXTURE SETUP
        ball = new Texture(Gdx.files.internal("ball2.png"));
        //-------------------------------

        //---------------------BALL SETUP
        ball_radius = new Random().nextInt(175 - 25) + 25;
        ballCircle = new Circle();
        ballCircle.x = screenXMax / 4;
        ballCircle.y = screenYMax / 2 + ball_radius - 300;
        ballCircle.radius = ball_radius;
        balls.add(new Ball(ballCircle, ball));
        balls.get(0).setRotationAngle(360);
        balls.get(0).setDirection(LEFT);

        ball_radius = new Random().nextInt(175 - 25) + 25;
        ballCircle = new Circle();
        ballCircle.x = (float) (screenXMax * 0.75);
        ballCircle.y = screenYMax / 2 + ball_radius + 100;
        ballCircle.radius = ball_radius;
        balls.add(new Ball(ballCircle, ball));
        balls.get(1).setDirection(RIGHT);

        //---------------------PLAYER SETUP
        playerCircle = new Circle();
        playerCircle.x = ballCircle.x + balls.get(0).getCircle().radius - player_radius;
        playerCircle.y = ballCircle.y - player_radius;

        playerCircle.radius = player_radius;
        player = new Player(playerCircle, new Texture(Gdx.files.internal("ball.png")));
        player.setInOrbit(true);
        player.setOrbitingBall(0);
        //----------------------------------

    }

    @Override
    public void render(float delta) {

        // background color-------------------------------------------------------------------------
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //------------------------------------------------------------------------------------------
        camera.update();
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.setColor(Color.CYAN);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.circle(player.getCircle().x, player.getCircle().y, player.getCircle().radius);
        for (Ball ball : balls) {
            debugRenderer.circle(ball.getCircle().x, ball.getCircle().y, ball.getCircle().radius);

        }
        debugRenderer.end();
        game.batch.setProjectionMatrix(camera.combined);


        processUserInput();

        updateEverything();

        drawEverything();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void drawEverything(){

        game.batch.begin();
        //---------------------------------DRAW BALLS
        for (Ball ball : balls) {
            game.batch.draw(ball.getTexture(), ball.getCircle().x-ball.getCircle().radius,ball.getCircle().y-ball.getCircle().radius,ball.getCircle().radius*2,ball.getCircle().radius*2);
        }
        //---------------------------------DRAW PLAYER
        game.batch.draw(player.getTexture(), player.getCircle().x-player.getCircle().radius,player.getCircle().y-player.getCircle().radius,player.getCircle().radius*2,player.getCircle().radius*2);
        //--------------------------------------------

        game.batch.end();
    }

    public void processUserInput(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                player.setInOrbit(false);
                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {

                return true;
            }
        });
    }

    public void updateEverything(){
        //-------------PLAYER
        if(player.isInOrbit()) {
                for (Ball ball : balls) {
                    ball.setRotationAngle(ball.getRotationAngle() + (2 * ball.getDirection()));
                    if (ball.getRotationAngle() >= 360 && ball.getDirection() == LEFT) {
                        ball.setRotationAngle(0);
                    }
                    if (ball.getRotationAngle() <= 0 && ball.getDirection() == RIGHT) {
                        ball.setRotationAngle(360);
                    }
                }

                double rot = Math.toRadians(balls.get(player.getOrbitingBall()).getRotationAngle());
                double cX = balls.get(player.getOrbitingBall()).getCircle().x;
                double cY = balls.get(player.getOrbitingBall()).getCircle().y;
                double x = cX;
                double y = cY + balls.get(player.getOrbitingBall()).getCircle().radius + player_radius + 2;

                player.getCircle().x = (float) (Math.cos(rot) * (x - cX) - Math.sin(rot) * (y - cY) + cX);
                player.getCircle().y = (float) (Math.sin(rot) * (x - cX) + Math.cos(rot) * (y - cY) + cY);
            }
            else{
                //---
                double rot = Math.toRadians(360 -balls.get(player.getOrbitingBall()).getRotationAngle());

                double xDirection = Math.sin(rot) * (player.getPlayerSpeed() * Gdx.graphics.getDeltaTime());
                double yDirection = Math.cos(rot) * (player.getPlayerSpeed() * Gdx.graphics.getDeltaTime());

                player.getCircle().x += xDirection;
                player.getCircle().y += yDirection;

                player.isCollided(balls);
        }
        //-----------------------


    }
}
