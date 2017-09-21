package com.spinball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Circle;


public class GameScreen implements Screen {

    private final SpinBall game;
    private float screenXMax = 450;
    private float screenYMax = 0;
    private OrthographicCamera camera;

    //--------------TEXTURES
    Texture ball;
    //----------

    //---------------PLAYER
    Player player;
    Circle playerCircle;
    float player_radius = 30;

    //---------------------

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
        ball = new Texture(Gdx.files.internal("ball.png"));
        //-------------------------------

        //---------------------PLAYER SETUP
        playerCircle = new Circle();
        playerCircle.x = screenXMax / 2;
        playerCircle.y = 100;
        playerCircle.radius = player_radius;
        player = new Player(playerCircle, ball);
        //----------------------------------

    }

    @Override
    public void render(float delta) {

        // background color-------------------------------------------------------------------------
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //------------------------------------------------------------------------------------------



        camera.update();
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

        // get text layout height and width and place it in the middle of the screen----------------
        String gameString = "Game";
        GlyphLayout gameTextLayout = new GlyphLayout(game.font, gameString);
        float gameTextWidth = gameTextLayout.width;
        float gameTextHeight = gameTextLayout.height;
        game.font.draw(game.batch, gameString, screenXMax / 2 - (gameTextWidth / 2),
                screenYMax / 2 - 100 - (gameTextHeight / 2));
        //------------------------------------------------------------------------------------------

        //---------------------------------DRAW PLAYER
        game.batch.draw(player.getPlayerTexture(), screenXMax / 2 - (player_radius / 2),screenYMax/2 - (player_radius/2),player_radius,player_radius);
        //--------------------------------------------

        game.batch.end();
    }

    public void processUserInput(){

    }

    public void updateEverything(){

    }
}
