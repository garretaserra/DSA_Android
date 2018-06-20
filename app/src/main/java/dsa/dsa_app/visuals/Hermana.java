package dsa.dsa_app.visuals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Hermana extends Sprite {

    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.hermana);

    private GameView gameView;
    private int filas;
    private int columnas;
    private int posX;
    private int posY;
    private int height = bmp.getHeight()/4;
    private int width = bmp.getWidth()/3;

    public Hermana(GameView gameView, int filas, int columnas){
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
        posX = columnas*gameView.getWidth()/GameView.principal.getAnchura();
        posY = filas*gameView.getHeight()/GameView.principal.getAltura();
        int width = bmp.getWidth()/3;
        int height = bmp.getHeight()/4;
        Rect origen = new Rect(width*1,height*0,width*2,height*1);
        Rect dest = new Rect(posX, posY, posX+width, posY+height);
        canvas.drawBitmap(bmp, origen,dest,null);

    }
    @Override
    public void onTouch(GameView gameView, MotionEvent event) {
        if(gameView.getCharacter().isClose((int)event.getX(),(int)event.getY())){
            Toast.makeText(App.getContext(), "I'm your sister, finally I have found you.", Toast.LENGTH_SHORT).show();
        }
    }
}
