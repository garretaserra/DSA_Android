package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.map.Views;

public class Muro extends Celda {

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Muro";
    }

    @Override
    public int getResource() {
        return 0;
    }


}
