package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.map.Views;

public class Rio extends Celda {

    private String letra = "R";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra() {
        return letra;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Rio";
    }
}
