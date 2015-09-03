package com.tablekat.agame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.event.InputEvent;
import java.util.ArrayList;

public class AGame extends ApplicationAdapter implements GestureListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private int timer = 0;
    private float elapsedTime = 0;
    private String meme = "";
    private int width = 1280;
    private int height = 720;
    private Stage stage;

    private FPSLogger fpsLogger;

    private World world = new World();

    @Override
	public void create () {
        camera = new OrthographicCamera(width, height);
        //camera = new OrthographicCamera();
        camera.translate(width/2.0f, height/2.0f);
        camera.update();

		batch = new SpriteBatch();
        //skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        /*final TextButton button = new TextButton("nice meme", skin, "default");
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                button.setText("nice ;3");
            }
        });
        stage.addActor(button);*/

        int numSprites = 5;
        for(int i=0; i < numSprites; ++i){
            world.creeps.add(new Creep(world, false));
            stage.addActor(world.creeps.get(i));
            world.creepEnemies.add(new Creep(world, true));
            stage.addActor(world.creepEnemies.get(i));
        }

        float enemyCenterX = 0.8f * width;
        float enemyCenterY = 0.5f * height;
        int numCreepEnemies = world.creepEnemies.size();
        for(int i=0; i < numCreepEnemies; ++i) {
            world.creepEnemies.get(i).setPosition(enemyCenterX + 100 * (float) Math.cos(2.0 * i / numCreepEnemies * Math.PI),
                    enemyCenterY + 100 * (float) Math.sin(2.0 * i / numCreepEnemies * Math.PI));
        }
        float centerX = 0.2f * width;
        float centerY = 0.5f * height;
        int numCreeps = world.creeps.size();
        for(int i=1; i < numCreeps; ++i) {
            world.creeps.get(i).setPosition(centerX + 100 * (float) Math.cos(2.0 * i / numCreeps * Math.PI),
                    centerY + 100 * (float) Math.sin(2.0 * i / numCreeps * Math.PI));
        }
        world.creeps.get(0).setPosition(0, 0);
        world.creepEnemies.get(0).setPosition(width, height);

        font = new BitmapFont();
        font.setColor(Color.RED);

        fpsLogger = new FPSLogger();

        //Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(stage);

        meme = "Hey there nice meme, friend";
	}

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer++;
        elapsedTime += Gdx.graphics.getDeltaTime();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        /*batch.setProjectionMatrix(camera.combined);
		batch.begin();

        for(int i=0; i < numCreepEnemies; ++i) {
            creepEnemies.get(i).draw(batch);
        }
        for(int i=0; i < numCreeps; ++i) {
            creeps.get(i).draw(batch);
        }
        font.draw(batch, meme, 200, 200);
		batch.end();*/

        fpsLogger.log();
	}

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button){
        // does nothing
        meme = String.format("(%.2f, %.2f), %d, %d", x/Gdx.graphics.getWidth(), y/Gdx.graphics.getHeight(), pointer, button);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button){
        // does nothing
        /*Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));
        if(creeps.get(0).getBoundingRectangle().contains(worldCoords.x, worldCoords.y)){
            creeps.get(0).translateX((float)Math.random() * 10 - 5);
            creeps.get(0).translateY((float) Math.random() * 10 - 5);
        }*/
        return false;
    }

    @Override
    public boolean longPress(float x, float y){
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button){
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY){
        // does nothing
        camera.translate(-deltaX, deltaY);
        camera.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button){
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance){
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 InitialPointer2, Vector2 pointer1, Vector2 pointer2){
        return false;
    }
}
