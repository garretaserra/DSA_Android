package dsa.dsa_app.visuals;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.map.GameView;

public class Arrows extends Sprite {

    private Bitmap bmp;
    private GameView gameView;
    private final int sideLength = 300;  //Length of the side of the arrows image
    private int posx;
    private int posy;
    private int width = sideLength;
    private int height = sideLength;

    public Arrows(GameView gameView, Bitmap bmp){
        this.bmp = bmp;
        this.gameView = gameView;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getPosx() {
        return posx;
    }

    @Override
    public int getPosy() {
        return posy;
    }

    @Override
    public void draw(Canvas canvas) {
        posx = gameView.getWidth()-sideLength;
        posy = gameView.getHeight()-sideLength;

        Rect dst = new Rect(gameView.getWidth()-sideLength,
                gameView.getHeight()-sideLength,
                gameView.getWidth(),
                gameView.getHeight());
        canvas.drawBitmap(bmp, null, dst, null);
    }

    @Override
    public void onTouch(GameView gameView, MotionEvent event) {
        int ctrx = posx + width/2;
        int ctry = posy + height/2;
        float x = event.getX()-ctrx;
        float y = event.getY()-ctry;
        int direction = (int) Math.round(Math.atan2(y,x)/Math.PI/2 + 2) % 4;
        gameView.getCharacter().move(direction);
    }
}
