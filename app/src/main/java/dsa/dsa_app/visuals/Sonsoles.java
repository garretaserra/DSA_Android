package dsa.dsa_app.visuals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Sonsoles extends Sprite{

    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(),R.drawable.sonsoles);

    private int posX;
    private int posY;

    public Sonsoles(int fila, int columna){
        this.posY = fila;
        this.posY = columna;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    @Override
    public int getWidth() {
        return 0;
    }
    @Override
    public int getPosx() {
        return 0;
    }
    @Override
    public int getPosy() {
        return 0;
    }
    @Override
    public void draw(Canvas canvas) {
        int width = bmp.getWidth()/3;
        int height = bmp.getHeight()/4;

        Rect origen = new Rect(width*1,height*0,width*2,height*1);
        
    }
    @Override
    public void onTouch(GameView gameView, MotionEvent event) {

    }
}
