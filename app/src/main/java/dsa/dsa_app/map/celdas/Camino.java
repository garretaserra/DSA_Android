package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.R;
import dsa.dsa_app.map.Views;

public class Camino extends Celda {

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Camino";
    }

    @Override
    public int getResource() {
        return R.drawable.camino_de_tierra;
    }


}
