package net.polzak.dodger.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.polzak.dodger.config.GameConfig;
import net.polzak.dodger.entity.Player;
import net.polzak.dodger.util.GdxUtils;
import net.polzak.dodger.util.ViewportUtils;
import net.polzak.dodger.util.debug.DebugCameraController;

public class GameScreen implements Screen {

    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private Player player;
    private DebugCameraController cameraController = new DebugCameraController();

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        player = new Player();

        float startX = 3;
        float startY = 5;

        player.setPosition(startX, startY);

        cameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CNETER_Y);

    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        renderDebug(delta);



    }

    private void renderDebug(float delta) {

        cameraController.handleDebugInput(delta);
        cameraController.applyTo(camera);

        renderer.setProjectionMatrix(camera.combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        player.drawDebug(renderer);
        renderer.end();

        ViewportUtils.drawGrid(viewport, renderer);

        player.update();



    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
//        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }




}
