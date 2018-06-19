package dsa.dsa_app.visuals;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Cofre extends Sprite {

    private GameView gameView;
    private int posx;
    private int posy;
    private int height = cofreClosed.getHeight();
    private int width = cofreClosed.getWidth();
    //cofre empieza cerrado
    private boolean open = false;
    private static Bitmap cofreClosed = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cofre_cerrado);
    private static Bitmap cofreOpened = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cofre_abierto);


    public Cofre(GameView gameView, int columna, int fila) {
        this.gameView = gameView;
        this.posx = columna;
        this.posy = fila;
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
        int x = canvas.getWidth()*posx/GameView.getCurrentMap().getAnchura();
        int y = canvas.getHeight()*posy/GameView.getCurrentMap().getAltura();
        Rect dst = new Rect(x,y,x+getWidth(),y+getHeight());
        if(open){
            canvas.drawBitmap(cofreOpened,null,dst,null);
        }
        else {
            canvas.drawBitmap(cofreClosed,null,dst,null);
        }
    }
    @Override
    public void onTouch(GameView gameView, MotionEvent event) {
        int x = gameView.getWidth()*posx/GameView.getCurrentMap().getAnchura();
        int y = gameView.getHeight()*posy/GameView.getCurrentMap().getAltura();
        double distance = Math.sqrt(Math.pow(gameView.getCharacter().getPosx()-x,2)+Math.pow(gameView.getCharacter().getPosy()-y,2));
        if(distance<70) {
            this.setOpen(true);
        }
    }

    @Override
    public boolean isCollition(float x, float y){
        int x2 = (gameView.getWidth()*posx)/GameView.getCurrentMap().getAnchura();
        int y2 = (gameView.getHeight()*posy)/GameView.getCurrentMap().getAltura();
        return
                x2 < x
                        && x2+this.getWidth() > x
                        && y2 < y
                        && y2+this.getHeight()> y ;
    }
}
