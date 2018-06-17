package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dsa.dsa_app.App;
import dsa.dsa_app.R;

public class Perrera extends Celda {
    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.perrera);

    @Override
    public Bitmap getResource() {
        return bmp;
    }
}
