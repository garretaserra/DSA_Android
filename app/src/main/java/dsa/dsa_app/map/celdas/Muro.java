package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.map.Views;

public class Muro extends Celda {

    private String letra = "M";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra() {
        return letra;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Muro";
    }

    public void setLetra(String letra){this.letra = letra;}

    public Muro(){}
}
