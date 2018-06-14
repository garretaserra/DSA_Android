package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.R;
import dsa.dsa_app.map.Views;

public class Cofre extends Celda {

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Cofre";
    }

    @Override
    public int getResource() {
        return R.drawable.cofre_cerrado;
    }

    public Cofre(){}
}
