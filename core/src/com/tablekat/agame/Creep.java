package com.tablekat.agame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

public class Creep extends Actor {

    private Sprite sprite;
    private boolean isEnemy;
    private World world;

    private float touchStartX;
    private float touchStartY;

    private float moveSpeed = 10;
    private float health = 500;
    private float scale = 0.2f;

    public Creep(World world, boolean isEnemy){
        this.world = world;
        this.isEnemy = isEnemy;
        if(!isEnemy) {
            sprite = new Sprite(new Texture(Gdx.files.internal("Creep.png")));
        }else{
            sprite = new Sprite(new Texture(Gdx.files.internal("CreepEnemy.png")));
        }
        sprite.setOriginCenter();

        setDebug(true);

        float x = sprite.getX();
        float y = sprite.getY();
        setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        setOrigin(Align.center);
        sprite.setScale(scale);
        setScale(scale);

        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                touchStartX = x;
                touchStartY = y;

                MoveByAction mba = new MoveByAction();
                mba.setAmount(100f, 0f);
                mba.setDuration(5f);

                Creep.this.addAction(mba);
                return true;
            }
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer){
                MoveByAction mba = new MoveByAction();
                mba.setAmount(x - touchStartX, y - touchStartY);
                mba.setDuration(0.001f);

                Creep.this.addAction(mba);
            }
        });
    }

    @Override
    protected void positionChanged(){
        float x = getX();
        float y = getY();
        sprite.setPosition(x, y);
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        // go towards enemies in world enenenenenenenenennenenenenenenemismiemsiemsiemsiemismismeimisimesmisemisemisemisemisemiosegrmiosdegrmiosdgfmiogfmio
    }

}
