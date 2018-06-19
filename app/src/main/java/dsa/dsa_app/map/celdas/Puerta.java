package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;
import dsa.dsa_app.map.Views;

public class Puerta extends Celda {

    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.puerta);
    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    @Override
    public void onTouch(GameView gameView){
        GameView.changeMapTo(gameView,cammel(GameView.getCurrentMap().getNombre()),"principal");
    }

    private String cammel(String arg){
        return arg.substring(0,1).toUpperCase()+arg.substring(1);
    }

}
