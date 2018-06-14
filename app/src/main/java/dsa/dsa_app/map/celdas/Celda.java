package dsa.dsa_app.map.celdas;


import android.graphics.Canvas;
import android.graphics.Rect;

/*@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Arbusto.class, name = "Arbusto"),
    @JsonSubTypes.Type(value = Muro.class, name = "Muro"),
}
)*/
public abstract class Celda{

    public abstract String getNombre();
    public abstract int getResource();
    }
