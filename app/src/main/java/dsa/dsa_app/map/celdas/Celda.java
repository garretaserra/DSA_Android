package dsa.dsa_app.map.celdas;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import dsa.dsa_app.map.GameView;

/*@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Arbusto.class, name = "Arbusto"),
    @JsonSubTypes.Type(value = Muro.class, name = "Muro"),
}
)*/
public abstract class Celda{

    public abstract Bitmap getResource();
    public abstract boolean canWalkThrough();
    public void onTouch(GameView gameView, MotionEvent event){};

}
