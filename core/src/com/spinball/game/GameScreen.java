package com.spinball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


public class GameScreen implements Screen {

    private final SpinBall game;
    private float screenXMax = 450;
    private float screenYMax = 0;
    private OrthographicCamera camera;

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

    }

    @Override
    public void render(float delta) {

        // background color-------------------------------------------------------------------------
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //------------------------------------------------------------------------------------------



        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // get text layout height and width and place it in the middle of the screen----------------
        String gameString = "Game";
        GlyphLayout gameTextLayout = new GlyphLayout(game.font, gameString);
        float gameTextWidth = gameTextLayout.width;
        float gameTextHeight = gameTextLayout.height;
        game.font.draw(game.batch, gameString, screenXMax / 2 - (gameTextWidth / 2),
                screenYMax / 2 - (gameTextHeight / 2));
        //------------------------------------------------------------------------------------------

        game.batch.end();
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
}
