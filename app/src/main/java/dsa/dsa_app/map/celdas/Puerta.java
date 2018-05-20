package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.map.Views;

public class Puerta extends Celda {

    Estado estado;
    enum Estado{Abierta, Cerrada}

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra() {
        return "P";
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Puerta";
    }
}
