package com.tablekat.agame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class AGame extends ApplicationAdapter, GestureDetector.GestureListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture img;
    private Texture creepTex;
    private Texture creepEnemyTex;
    private Sprite creepSprite;
    private Sprite creepEnemySprite;
	private BitmapFont font;
    private int timer = 0;
    private float elapsedTime = 0;

	@Override
	public void create () {
        camera = new OrthographicCamera(1280, 720);

		batch = new SpriteBatch();

		img = new Texture("badlogic.jpg");
        creepTex = new Texture("Creep.png");
        creepEnemyTex = new Texture("CreepEnemy.png");
        creepSprite = new Sprite(creepTex);
        creepEnemySprite = new Sprite(creepEnemyTex);

        font = new BitmapFont();
        font.setColor(Color.RED);

        Gdx.input.setInputProcessor(new GestureDetector(this));
	}

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
        creepTex.dispose();
        creepEnemyTex.dispose();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer++;
        elapsedTime += Gdx.graphics.getDeltaTime();

        creepEnemySprite.setPosition(100 * (float) Math.cos(timer / 10.0), 100 * (float) Math.sin(timer / 10.0));

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
                creepSprite.translateX(-1f);
            }else{
                creepSprite.translateX(-10f);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
                creepSprite.translateX(1f);
            }else{
                creepSprite.translateX(10f);
            }
        }

        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(img, 0, 0);
        //batch.draw(creepTex, 100, 100);
        //batch.draw(creepEnemyTex, 200, 200);
        creepSprite.draw(batch);
        creepEnemySprite.draw(batch);
        font.draw(batch, "Hey there nice meme, friend", 200, 200);
		batch.end();
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
        // memes
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button){
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
        camera.translate(deltaX, 0);
        camera.update();
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
