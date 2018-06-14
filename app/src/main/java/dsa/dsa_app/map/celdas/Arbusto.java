package dsa.dsa_app.map.celdas;

import com.fasterxml.jackson.annotation.JsonView;

import dsa.dsa_app.R;
import dsa.dsa_app.map.Views;

public class Arbusto extends Celda {

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Arbusto";
    }

    @Override
    public int getResource() {
        return R.drawable.arbusto;
    }
}
