package dsa.dsa_app.map;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import dsa.dsa_app.map.celdas.Celda;
import dsa.dsa_app.visuals.Sprite;

public class Mapa {

    private ArrayList<ArrayList<Celda>> celdas = new ArrayList<>();
    private String nombre;
    private int altura;
    private int anchura;
    private List<Sprite> entities = new ArrayList<>();
    private Logger logger = Logger.getLogger(Mapa.class.getName());

    public Mapa(String nombre, int altura, int anchura){
        this.setNombre(nombre);
        this.setAltura(altura);
        this.setAnchura(anchura);
    }
    public Mapa(){}

    public void guardarMapa() throws IOException {
        /*File f = new File("./maven/src/main/resources/Mapas/");
        if (!f.exists()) {
            f.createNewFile();
            logger.info("File with name: " + this.nombre + ".txt created for map.");
        }*/
        ObjectMapper om = new ObjectMapper();
        try {
            om.writerWithView(Views.Normal.class).writeValue(
                    new File(getNombre() + ".txt"), this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getAltura() {
        return altura;
    }
    private void setAltura(int altura) {
        this.altura = altura;
    }
    public int getAnchura() {
        return anchura;
    }
    private void setAnchura(int anchura) {
        this.anchura = anchura;
    }
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ArrayList<ArrayList<Celda>> getCeldas() {
        return celdas;
    }

    public boolean setCeldas(ArrayList<ArrayList<Celda>> celdas) {
        int columns = celdas.get(0).size();
        for(List<Celda> list : celdas){
            if(list.size()!=columns)
                return false;
        }
        this.celdas = celdas;
        this.setAltura(celdas.size());
        this.setAnchura(celdas.get(0).size());
        return true;
    }

    public List<Sprite> getEntities() {
        return entities;
    }

    public void setEntities(List<Sprite> entities) {
        this.entities = entities;
    }
}
