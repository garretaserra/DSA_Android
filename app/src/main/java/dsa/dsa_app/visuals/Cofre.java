package dsa.dsa_app.visuals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Cofre extends Sprite {

    private int posx;
    private int posy;
    private int height = cofreClosed.getHeight();
    private int width = cofreClosed.getWidth();
    //cofre empieza cerrado
    private boolean open = false;
    private static Bitmap cofreClosed = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cofre_cerrado);
    private static Bitmap cofreOpened = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cofre_abierto);


    public Cofre(int positionX, int positionY) {
        this.posx = positionX;
        this.posy = positionY;
    }


    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean opened) {
        this.open = opened;
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
        Rect dst = new Rect(posx,posy,posx+getWidth(),posy+getHeight());
        if(open){
            canvas.drawBitmap(cofreOpened,null,dst,null);
        }
        else {
            canvas.drawBitmap(cofreClosed,null,dst,null);
        }
    }
    @Override
    public void onTouch(GameView gameView, MotionEvent event) {

    }
}
