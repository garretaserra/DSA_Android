package dsa.dsa_app.map;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dsa.dsa_app.R;
import dsa.dsa_app.map.celdas.*;
import dsa.dsa_app.visuals.Arrows;
import dsa.dsa_app.visuals.Character;
import dsa.dsa_app.visuals.Sprite;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private List<Sprite> entities = new ArrayList<>();
    private String currentMap;
    private Mapa map;
    private Mapa principal;
    private Mapa orfanato;
    private Mapa banco;
    private Canvas mapBackground = new Canvas();

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }


        });

        //Adding entities
        entities.add(new Character(this, BitmapFactory.decodeResource(getResources(), R.drawable.protagonista)));
        entities.add(new Arrows(this, BitmapFactory.decodeResource(getResources(), R.drawable.arrows)));

        //Dibujar MAPA PRINCIPAL 12x18
        principal = new Mapa();
        principal.setNombre("Principal");
        ArrayList<ArrayList<Celda>> celdasColumnas = new ArrayList<>();
        ArrayList<Celda> principalFila1 = new ArrayList<>();
        ArrayList<Celda> principalFila2 = new ArrayList<>();
        ArrayList<Celda> principalFila3 = new ArrayList<>();
        ArrayList<Celda> principalFila4 = new ArrayList<>();
        ArrayList<Celda> principalFila5 = new ArrayList<>();
        ArrayList<Celda> principalFila6 = new ArrayList<>();
        ArrayList<Celda> principalFila7 = new ArrayList<>();
        ArrayList<Celda> principalFila8 = new ArrayList<>();
        ArrayList<Celda> principalFila9 = new ArrayList<>();
        ArrayList<Celda> principalFila10 = new ArrayList<>();
        ArrayList<Celda> principalFila11 = new ArrayList<>();
        ArrayList<Celda> principalFila12 = new ArrayList<>();

        //Elementos de la primera fila
        for (int i =0; i<16; i++){
        principalFila1.add(new Muro());
        }
        principalFila1.add(new Gruta());
        principalFila1.add(new Muro());
        //Añadir la primera columna
        celdasColumnas.add(principalFila1);

        //Elementos de la segunda fila
        for (int i =0; i<5; i++){
            principalFila2.add(new Arbusto());
        }
        for (int i =0; i<4; i++){
            principalFila2.add(new Montaña());
        }
        for (int i =0; i<7; i++){
            principalFila2.add(new Arbol());
        }
        principalFila2.add(new CaminoTierra());
        principalFila2.add(new Muro());
        //Añadir la segunda columna
        celdasColumnas.add(principalFila2);

        //Elementos de la tercera fila
        principalFila3.add(new Arbusto());
        principalFila3.add(new Arbusto());
        principalFila3.add(new Banco());
        for (int i =0; i<3; i++){
            principalFila3.add(new Arbusto());
        }
        principalFila3.add(new Orfanato());
        for (int i =0; i<3; i++){
            principalFila3.add(new Montaña());
        }
        for (int i =0; i<6; i++){
            principalFila3.add(new Arbol());
        }
        principalFila3.add(new CaminoTierra());
        principalFila3.add(new Muro());
        //Añadir la tercera columna
        celdasColumnas.add(principalFila3);

        //Elementos de la cuarta fila
        principalFila4.add(new Arbol());
        principalFila4.add(new Arbol());
        principalFila4.add(new CaminoTierra());
        for (int i =0; i<3; i++){
            principalFila4.add(new Cespez());
        }
        for (int i =0; i<8; i++){
            principalFila4.add(new CaminoTierra());
        }
        principalFila4.add(new Cespez());
        principalFila4.add(new Agua());
        principalFila4.add(new CaminoTierra());
        principalFila4.add(new Muro());
        //Añadir la cuarta columna
        celdasColumnas.add(principalFila4);

        //Elementos de la quinta fila
        principalFila5.add(new Arbusto());
        principalFila5.add(new CaminoTierra());
        principalFila5.add(new CaminoTierra());
        for (int i =0; i<2; i++){
            principalFila5.add(new Cespez());
        }
        for (int i =0; i<4; i++){
            principalFila5.add(new CaminoTierra());
        }
        for (int i =0; i<6; i++){
            principalFila5.add(new Cespez());
        }
        principalFila5.add(new Agua());
        principalFila5.add(new CaminoTierra());
        principalFila5.add(new Muro());
        //Añadir la quinta columna
        celdasColumnas.add(principalFila5);

        //Elementos de la sexta fila
        principalFila6.add(new Arbusto());
        principalFila6.add(new CaminoTierra());
        principalFila6.add(new CaminoTierra());
        for (int i =0; i<2; i++){
            principalFila6.add(new Cespez());
        }
        for (int i =0; i<4; i++){
            principalFila6.add(new CaminoTierra());
        }
        principalFila6.add(new Cespez());
        principalFila6.add(new Cespez());
        principalFila6.add(new Agua());
        principalFila6.add(new Cespez());
        principalFila6.add(new Cespez());
        principalFila6.add(new Cespez());
        principalFila6.add(new Agua());
        principalFila6.add(new CaminoTierra());
        principalFila6.add(new Muro());
        //Añadir la sexta columna
        celdasColumnas.add(principalFila6);

        //Elementos de la septima fila
        principalFila7.add(new Arbusto());
        principalFila7.add(new CaminoTierra());
        principalFila7.add(new CaminoTierra());
        for (int i =0; i<2; i++) {
            principalFila7.add(new Cespez());
        }
        for (int i =0; i<4; i++){
            principalFila7.add(new CaminoTierra());
        }
        principalFila7.add(new Cespez());
        principalFila7.add(new Cespez());
        for (int i =0; i<5; i++) {
            principalFila7.add(new Agua());
        }
        principalFila7.add(new CaminoTierra());
        principalFila7.add(new Muro());
        //Añadir la septima columna
        celdasColumnas.add(principalFila7);

        //Elementos de la octava fila
        principalFila8.add(new Arbusto());
        principalFila8.add(new CaminoTierra());
        principalFila8.add(new CaminoTierra());
        for (int i =0; i<2; i++) {
            principalFila8.add(new Cespez());
        }
        for (int i =0; i<5; i++){
            principalFila8.add(new CaminoTierra());
        }
        principalFila8.add(new Cespez());
        for (int i =0; i<5; i++) {
            principalFila8.add(new Agua());
        }
        principalFila8.add(new CaminoTierra());
        principalFila8.add(new Muro());
        //Añadir la octava columna
        celdasColumnas.add(principalFila8);

        //Elementos de la novena fila
        principalFila9.add(new Arbusto());
        principalFila9.add(new CaminoTierra());
        principalFila9.add(new CaminoTierra());
        principalFila9.add(new Cespez());
        principalFila9.add(new Tienda());
        for (int i =0; i<5; i++){
            principalFila9.add(new CaminoTierra());
        }
        principalFila9.add(new Cespez());
        for (int i =0; i<5; i++) {
            principalFila9.add(new Agua());
        }
        principalFila9.add(new CaminoTierra());
        principalFila9.add(new Muro());
        //Añadir la novena columna
        celdasColumnas.add(principalFila9);

        //Elementos de la decima fila
        principalFila10.add(new Casa());
        for (int i =0; i<6; i++){
            principalFila10.add(new CaminoTierra());
        }
        principalFila10.add(new Cespez());
        for (int i =0; i<4; i++){
            principalFila10.add(new CaminoTierra());
        }
        for (int i =0; i<4; i++) {
            principalFila10.add(new Agua());
        }
        principalFila10.add(new CaminoTierra());
        principalFila10.add(new Muro());
        //Añadir la decima columna
        celdasColumnas.add(principalFila10);

        //Elementos de la undecima fila
        principalFila11.add(new Muro());
        for (int i =0; i<7; i++){
            principalFila11.add(new Cespez());
        }
        for (int i =0; i<9; i++){
            principalFila11.add(new CaminoTierra());
        }
        principalFila11.add(new Muro());
        //Añadir la undecima columna
        celdasColumnas.add(principalFila11);

        //Elementos de la duodecima fila
        for (int i =0; i<18; i++){
            principalFila12.add(new Muro());
        }
        //Añadir la duodecima columna
        celdasColumnas.add(principalFila12);
        //Añadir las columnas al mapa
        principal.setCeldas(celdasColumnas);

        //Dibujar MAPA ORFANATO
        orfanato = new Mapa();
        orfanato.setNombre("orfanato");
        ArrayList<ArrayList<Celda>> celdasColumnas2 = new ArrayList<>();
        ArrayList<Celda> celdasFila2 = new ArrayList<>();
        //Elementos de la primera fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        //Añadir la primera columna
        celdasColumnas2.add(celdasFila2);

        celdasFila2.clear();
        //Elementos de la segunda fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new Cama());
        celdasFila2.add(new Cama());
        celdasFila2.add(new Muro());
        //Añadir la segunda columna
        celdasColumnas2.add(celdasFila2);

        celdasFila2.clear();
        //Elementos de la tercera fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new Cama());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new Cama());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new Muro());
        //Añadir la tercera columna
        celdasColumnas2.add(celdasFila2);

        celdasFila2.clear();
        //Elementos de la cuarta fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new Cama());
        celdasFila2.add(new Cama());
        celdasFila2.add(new Muro());
        //Añadir la cuarta columna
        celdasColumnas2.add(celdasFila2);

        celdasFila2.clear();
        //Elementos de la quinta fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new Cama());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new Cama());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());
        celdasFila2.add(new CaminoInterior());//espacio para controles
        celdasFila2.add(new Muro()); //espacio para controles
        //Añadir la quinta columna
        celdasColumnas2.add(celdasFila2);

        celdasFila2.clear();
        //Elementos de la sexta fila
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(null); //para entrar y salir de la habitacion
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro());
        celdasFila2.add(new Muro()); ///espacio para controles
        celdasFila2.add(new Muro());//espacio para controles
        //Añadir la sexta columna
        celdasColumnas2.add(celdasFila2);
        //Añadir las columnas al mapa
        orfanato.setCeldas(celdasColumnas2);
        orfanato.setAnchura(9);
         orfanato.setAltura(6);

         //Dibujar MAPA BANCO
        banco = new Mapa();
        banco.setNombre("banco");
        ArrayList<ArrayList<Celda>> celdasColumnas3 = new ArrayList<>();
        ArrayList<Celda> celdasFila3 = new ArrayList<>();
        //Elementos de la primera fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        //Añadir la primera columna
        celdasColumnas3.add(celdasFila3);

        celdasFila3.clear();
        //Elementos de la segunda fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Muro());
        //Añadir la segunda columna
        celdasColumnas3.add(celdasFila3);

        celdasFila3.clear();
        //Elementos de la tercera fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new CaminoInterior());
        //Añadir la tercera columna
        celdasColumnas3.add(celdasFila3);

        celdasFila3.clear();
        //Elementos de la cuarta fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Muro());
        //Añadir la cuarta columna
        celdasColumnas3.add(celdasFila3);

        celdasFila3.clear();
        //Elementos de la quinta fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Gris());
        celdasFila3.add(new Mesa());
        celdasFila3.add(new Gris());//espacio para controles
        celdasFila3.add(new Muro()); //espacio para controles
        //Añadir la quinta columna
        celdasColumnas3.add(celdasFila3);

        celdasFila3.clear();
        //Elementos de la sexta fila
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(null); //para entrar y salir de la habitacion
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro());
        celdasFila3.add(new Muro()); ///espacio para controles
        celdasFila3.add(new Muro());//espacio para controles
        //Añadir la sexta columna
        celdasColumnas3.add(celdasFila3);
        //Añadir columnas al mapa
        banco.setCeldas(celdasColumnas3);
        banco.setAnchura(9);
        banco.setAltura(6);

        map = principal;

    }
        @Override
        public boolean onTouchEvent (MotionEvent event){
            for (Sprite s : entities) {
                if (s.isCollition(event.getX(), event.getY())) {
                    s.onTouch(this, event);
                    break;
                }
            }
            return true;
        }


        @Override
        public void draw (Canvas canvas){
            super.draw(canvas);
            canvas.drawColor(Color.BLACK);

            //Check if map is already loaded
            if (!map.getNombre().equals(currentMap)) {
                //If the map is not loaded, load the map from storage
                //map = Mapa.cargarMapa(currentMap);
            }

            int height = canvas.getHeight() / map.getCeldas().size();
            int width = canvas.getWidth() / map.getCeldas().get(0).size();
            //Iteration over all cells to draw each one
            for (int i = 0; i < map.getCeldas().size(); i++) {
                for (int j = 0; j < map.getCeldas().get(i).size(); j++) {
                    Rect r = new Rect(width * j, height * i, width * (j + 1), height * (i + 1));
                    Bitmap bmp = map.getCeldas().get(i).get(j).getResource();
                    canvas.drawBitmap(bmp, null, r, null);
                }
            }

            //draw entities
            for (Sprite s : entities.stream().filter(x -> x.getClass() == Character.class).collect(Collectors.toList())) {
                s.draw(canvas);
            }

            //draw arrows
            for (Sprite s : entities.stream().filter(x -> x.getClass() == Arrows.class).collect(Collectors.toList())) {
                s.draw(canvas);
            }
        }


        public Character getCharacter () {
            return (Character) entities.stream().filter(x -> x.getClass() == Character.class).collect(Collectors.toList()).get(0);
        }

}