package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.Views;

public class Muro extends Celda {

    private static Bitmap bmp;

    @Override
    public Bitmap getResource() {
        return bmp;
    }
}
