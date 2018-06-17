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
        map = new Mapa();
        map.setNombre("Proba");
        ArrayList<ArrayList<Celda>> mapCells = new ArrayList<>();
        ArrayList<Celda> r1 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            r1.add(new CaminoTierra());
        }
        for (int i = 0; i < 6; i++) {
            mapCells.add(r1);
        }
        map.setCeldas(mapCells);
        map.setAnchura(9);
        map.setAltura(6);

        //Dibujar MAPA PRINCIPAL
        principal = new Mapa();
        principal.setNombre("Principal");
        ArrayList<ArrayList<Celda>> celdasColumnas = new ArrayList<>();
        ArrayList<Celda> celdasFila = new ArrayList<>();
        //Elementos de la primera fila
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Muro());
        celdasFila.add(new Gruta());
        celdasFila.add(new Muro());
        //Añadir la primera columna
        celdasColumnas.add(celdasFila);

        //Elementos de la segunda fila
        celdasFila.add(new Arbusto());
        celdasFila.add(new Banco());
        celdasFila.add(new Arbusto());
        celdasFila.add(new Arbusto());
        celdasFila.add(new Orfanato());
        celdasFila.add(new Arbusto());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        //Añadir la segunda columna
        celdasColumnas.add(celdasFila);

        //Elementos de la tercera fila
        celdasFila.add(new Cespez());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new Cespez());
        celdasFila.add(new Cespez());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new Arbol());
        celdasFila.add(new Arbol());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        //Añadir la tercera columna
        celdasColumnas.add(celdasFila);

        //Elementos de la cuarta fila
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        //Añadir la cuarta columna
        celdasColumnas.add(celdasFila);

        //Elementos de la quinta fila
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new Cespez());
        celdasFila.add(new Tienda());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new Perrera());
        celdasFila.add(new Agua());
        celdasFila.add(new Agua());
        celdasFila.add(new CaminoTierra());//espacio `para controles
        celdasFila.add(new CaminoTierra()); //espacio para controles
        //Añadir la quinta columna
        celdasColumnas.add(celdasFila);

        //Elementos de la sexta fila
        celdasFila.add(new Casa());
        celdasFila.add(new Cespez());
        celdasFila.add(new Cespez());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra());
        celdasFila.add(new CaminoTierra()); //espacio para controles
        celdasFila.add(new CaminoTierra());  //espacio para controles
        //Añadir la sexta columna
        celdasColumnas.add(celdasFila);

        principal.setCeldas(celdasColumnas);
        principal.setAnchura(9);
        principal.setAltura(6);


        //Mapa interior de orfanato
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

        orfanato.setCeldas(celdasColumnas2);
        orfanato.setAnchura(9);
         orfanato.setAltura(6);

         //Mapa interior del banco
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

        banco.setCeldas(celdasColumnas3);
        banco.setAnchura(9);
        banco.setAltura(6);


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