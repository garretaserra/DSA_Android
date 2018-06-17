package dsa.dsa_app.visuals;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.map.GameView;

public class Character extends Sprite {

    int[] DIRECTION_TO_ANIMATION_MAP = { 1, 3, 2, 0 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int posx = 0;
    private int posy = 0;
    private int speed;
    private final int defSpeed = 15;
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

    public void update(Canvas canvas){
        if(speed!=0) {
            currentFrame = ++currentFrame % BMP_COLUMNS;
        }
        //Change the position depending on the direction of movement
        switch (direction){
            case 0:     //left
                if(posx-speed>0)
                    posx -= speed;
                else
                    posx=0;
                break;
            case 1:     //up
                if(posy-speed>0)
                    posy -= speed;
                else
                    posy=0;
                break;
            case 2:     //right
                if(posx+speed+width<canvas.getWidth())
                    posx += speed;
                else
                    posx = canvas.getWidth()-width;
                break;
            case 3:     //down
                if(posy+speed+height<canvas.getHeight())
                    posy += speed;
                else
                    posy = canvas.getHeight()-height;
                break;
        }
    }

    public void draw(Canvas canvas) {
        update(canvas);
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
        this.direction = direction;
        speed = defSpeed;

    }
    public void stop(){
        speed = 0;
    }
}