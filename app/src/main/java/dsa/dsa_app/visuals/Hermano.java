package dsa.dsa_app.visuals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Hermano extends Sprite {

    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.hermano);

    private GameView gameView;
    private int filas;
    private int columnas;
    private int posX;
    private int posY;
    private int height = bmp.getHeight()/4;
    private int width = bmp.getWidth()/3;

    public Hermano (GameView gameView, int filas, int columnas){
        this.gameView = gameView;
        this.filas = filas;
        this.columnas = columnas;
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
        return posX;
    }
    @Override
    public int getPosy() {
        return posY;
    }
    @Override
    public void draw(Canvas canvas) {

    }
    @Override
    public void onTouch(GameView gameView, MotionEvent event) {

    }
}
