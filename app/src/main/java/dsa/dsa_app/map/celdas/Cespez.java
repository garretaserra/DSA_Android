package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dsa.dsa_app.App;
import dsa.dsa_app.R;

public class Cespez extends Celda {
    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cespez);

    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough() {
        return true;
    }
}
