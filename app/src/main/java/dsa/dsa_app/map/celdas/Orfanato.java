package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Orfanato extends Celda {
    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.orfanato);

    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough(){return false;}

    @Override
    public void onTouch(GameView gameView, MotionEvent event){
        GameView.changeMapTo(gameView,null,"orfanato");
    }
}
