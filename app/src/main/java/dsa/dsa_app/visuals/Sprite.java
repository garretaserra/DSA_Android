package dsa.dsa_app.visuals;

import android.graphics.Canvas;
import android.view.MotionEvent;

import dsa.dsa_app.map.GameView;

public abstract class Sprite {

    public abstract int getHeight();
    public abstract int getWidth();

    //Position of the top left corner of the sprite
    public abstract int getPosx();
    public abstract int getPosy();

    public abstract void draw(Canvas canvas);

    public boolean isCollition(float x, float y){
        return
                this.getPosx() < x
                && this.getPosx()+this.getWidth() > x
                && this.getPosy() < y
                && this.getPosy()+this.getHeight()> y ;
    }

    public abstract void onTouch(GameView gameView, MotionEvent event);
}
