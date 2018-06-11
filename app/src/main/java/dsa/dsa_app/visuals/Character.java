package dsa.dsa_app.visuals;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.map.GameView;

public class Character extends Sprite {

    int[] DIRECTION_TO_ANIMATION_MAP = { 1, 2, 0, 2 };
    private static final int BMP_ROWS = 3;
    private static final int BMP_COLUMNS = 3;
    private int posx = 0;
    private int posy = 0;
    private int speed = 10;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int direction;


    public Character(GameView gameView, Bitmap bmp) {
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        GameView gameView1 = gameView;
        this.bmp = bmp;
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

    public void draw(Canvas canvas) {
        currentFrame = ++currentFrame % BMP_COLUMNS;
        int srcX = currentFrame * width;
        int srcY = DIRECTION_TO_ANIMATION_MAP[direction] * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(posx, posy, posx + width, posy + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    @Override
    public void onTouch(GameView gameView, MotionEvent event) {
        //Event when Character is touched
    }

    public void move(int direction){
        direction = direction;
        switch (direction){
            case 0:
                posy += speed;
                break;
            case 1:
                posx -= speed;
                break;
            case 2:
                posy -= speed;
                break;
            case 3:
                posx += speed;
                break;
        }
    }
}