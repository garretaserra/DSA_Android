package dsa.dsa_app.map.celdas;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;
import dsa.dsa_app.map.Views;

public class Arbusto extends Celda {

    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(),R.drawable.arbusto);

    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }
}
