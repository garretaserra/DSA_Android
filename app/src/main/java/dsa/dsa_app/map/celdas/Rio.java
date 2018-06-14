package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.R;
import dsa.dsa_app.map.Views;

public class Rio extends Celda {

    private String letra = "R";

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Rio";
    }

    @Override
    public int getResource() {
        return R.drawable.agua;
    }
}
