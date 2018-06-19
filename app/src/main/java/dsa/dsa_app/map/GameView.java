package dsa.dsa_app.map;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
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
import dsa.dsa_app.visuals.Cofre;
import dsa.dsa_app.visuals.Sonsoles;
import dsa.dsa_app.visuals.Sprite;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private static List<Sprite> entities = new ArrayList<>();
    private static Mapa map;
    public static Mapa principal = new Mapa();
    public static Mapa orfanato = new Mapa();
    public static Mapa banco = new Mapa();
    private int height;
    private int width;
    private static boolean changingMap = false;

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
        principal.setNombre("Principal");
        ArrayList<ArrayList<Celda>> principalColumnas = new ArrayList<>();
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
        principalColumnas.add(principalFila1);

        //Elementos de la segunda fila
        for (int i =0; i<5; i++){
            principalFila2.add(new Arbusto());
        }
        for (int i =0; i<4; i++){
            principalFila2.add(new Montana());
        }
        for (int i =0; i<7; i++){
            principalFila2.add(new Arbol());
        }
        principalFila2.add(new CaminoTierra());
        principalFila2.add(new Muro());
        //Añadir la segunda columna
        principalColumnas.add( principalFila2);

        //Elementos de la tercera fila
        principalFila3.add(new Arbusto());
        principalFila3.add(new Arbusto());
        principalFila3.add(new Banco());
        for (int i =0; i<3; i++){
            principalFila3.add(new Arbusto());
        }
        principalFila3.add(new Orfanato());
        for (int i =0; i<3; i++){
            principalFila3.add(new Montana());
        }
        for (int i =0; i<4; i++){
            principalFila3.add(new Arbol());
        }
        for (int i =0; i<3; i++){
            principalFila3.add(new CaminoInterior());
        }
        principalFila3.add(new Muro());
        //Añadir la tercera columna
        principalColumnas.add(principalFila3);

        //Elementos de la cuarta fila
        principalFila4.add(new Arbol());
        principalFila4.add(new Arbol());
        principalFila4.add(new CaminoTierra());
        for (int i =0; i<3; i++){
            principalFila4.add(new Cespez());
        }
        for (int i =0; i<11; i++){
            principalFila4.add(new CaminoTierra());
        }
        principalFila4.add(new Muro());
        //Añadir la cuarta columna
        principalColumnas.add(principalFila4);

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
        principalFila5.add(new Arbusto());
        principalFila5.add(new Muro());
        //Añadir la quinta columna
        principalColumnas.add(principalFila5);

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
        principalFila6.add(new Arbusto());
        principalFila6.add(new Muro());
        //Añadir la sexta columna
        principalColumnas.add(principalFila6);

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
        principalFila7.add(new Arbusto());
        principalFila7.add(new Muro());
        //Añadir la septima columna
        principalColumnas.add( principalFila7);

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
        principalFila8.add(new Arbusto());
        principalFila8.add(new Muro());
        //Añadir la octava columna
        principalColumnas.add( principalFila8);

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
        principalFila9.add(new Arbusto());
        principalFila9.add(new Muro());
        //Añadir la novena columna
        principalColumnas.add( principalFila9);

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
        principalFila10.add(new Arbusto());
        principalFila10.add(new Muro());
        //Añadir la decima columna
        principalColumnas.add(principalFila10);

        //Elementos de la undecima fila
        principalFila11.add(new Muro());
        for (int i =0; i<7; i++){
            principalFila11.add(new Cespez());
        }
        for (int i =0; i<9; i++){
            principalFila11.add(new Agua());
        }
        principalFila11.add(new Muro());
        //Añadir la undecima columna
        principalColumnas.add(principalFila11);

        //Elementos de la duodecima fila
        for (int i =0; i<18; i++){
            principalFila12.add(new Muro());
        }
        //Añadir la duodecima columna
        principalColumnas.add(principalFila12);
        //Añadir las columnas al mapa
        principal.setCeldas(principalColumnas);
        principal.getEntities().add(new Cofre(this,11,9,null));
        principal.getEntities().add(new Cofre(this, 1,4, "Llave Casa"));


        //Dibujar MAPA ORFANATO
        orfanato.setNombre("orfanato");
        ArrayList<ArrayList<Celda>> orfanatoColumnas = new ArrayList<>();
        ArrayList<Celda> orfanatoFila1 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila2 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila3 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila4 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila5 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila6 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila7 = new ArrayList<>();
        ArrayList<Celda> orfanatoFila8 = new ArrayList<>();

        //Elementos de la primera fila
        for( int i =0; i<9; i++ ) {
            orfanatoFila1.add(new Muro());
        }
        //Añadir la primera columna
        orfanatoColumnas.add(orfanatoFila1);

        //Elementos de la segunda fila
        orfanatoFila2.add(new Muro());
        for( int i =0; i<5; i++ ) {
            orfanatoFila2.add(new CaminoInterior());
        }
        orfanatoFila2.add(new Cama());
        orfanatoFila2.add(new Cama());
        orfanatoFila2.add(new Muro());
        //Añadir la segunda columna
        orfanatoColumnas.add(orfanatoFila2);

        //Elementos de la tercera fila
        orfanatoFila3.add(new Muro());
        orfanatoFila3.add(new Cama());
        orfanatoFila3.add(new CaminoInterior());
        orfanatoFila3.add(new Cama());
        for (int i = 0; i<4; i++) {
            orfanatoFila3.add(new CaminoInterior());
        }
        orfanatoFila3.add(new Muro());
        //Añadir la tercera columna
        orfanatoColumnas.add( orfanatoFila3);

        //Elementos de la cuarta fila
        orfanatoFila4.add(new Muro());
        for (int i = 0; i<5; i++) {
            orfanatoFila4.add(new CaminoInterior());
        }
        orfanatoFila4.add(new Cama());
        orfanatoFila4.add(new Cama());
        orfanatoFila4.add(new Muro());
        //Añadir la cuarta columna
        orfanatoColumnas.add(orfanatoFila4);

        //Elementos de la quinta fila
        orfanatoFila5.add(new Muro());
        orfanatoFila5.add(new Cama());
        orfanatoFila5.add(new CaminoInterior());
        orfanatoFila5.add(new Cama());
        for (int i = 0; i<4; i++) {
            orfanatoFila5.add(new CaminoInterior());
        }
        orfanatoFila5.add(new Muro()); //espacio para controles
        //Añadir la quinta columna
        orfanatoColumnas.add(orfanatoFila5);

        //Elementos de la sexta fila
        for (int i = 0; i<4; i++) {
            orfanatoFila6.add(new Muro());
        }
        orfanatoFila6.add(new Puerta()); //para entrar y salir de la habitacion
        for (int i = 0; i<4; i++) {
            orfanatoFila6.add(new Muro());
        }
        //Añadir la sexta columna
        orfanatoColumnas.add(orfanatoFila6);
        //Añadir las columnas al mapa
        orfanato.setCeldas(orfanatoColumnas);
        orfanato.getEntities().add(new Sonsoles(this, 1,1));

         //Dibujar MAPA BANCO
        banco.setNombre("banco");
        ArrayList<ArrayList<Celda>> bancoColumnas = new ArrayList<>();
        ArrayList<Celda> bancoFila1 = new ArrayList<>();
        ArrayList<Celda> bancoFila2= new ArrayList<>();
        ArrayList<Celda> bancoFila3 = new ArrayList<>();
        ArrayList<Celda> bancoFila4 = new ArrayList<>();
        ArrayList<Celda> bancoFila5 = new ArrayList<>();
        ArrayList<Celda> bancoFila6 = new ArrayList<>();
        ArrayList<Celda> bancoFila7 = new ArrayList<>();
        ArrayList<Celda> bancoFila8 = new ArrayList<>();

        //Elementos de la primera fila
        for (int i = 0; i<9; i++) {
            bancoFila1.add(new Muro());
        }
        //Añadir la primera columna
        bancoColumnas.add(bancoFila1);

        //Elementos de la segunda fila
        for (int i = 0; i<9; i++) {
            bancoFila2.add(new Gris());
        }
        //Añadir la segunda columna
        bancoColumnas.add(bancoFila2);

        //Elementos de la tercera fila
        bancoFila3.add(new Muro());
        bancoFila3.add(new Gris());
        bancoFila3.add(new Mesa());
        bancoFila3.add(new Gris());
        bancoFila3.add(new Mesa());
        bancoFila3.add(new Gris());
        bancoFila3.add(new Mesa());
        bancoFila3.add(new Gris());
        bancoFila3.add(new Muro());
        //Añadir la quinta columna
        bancoColumnas.add(bancoFila3);;

        //Elementos de la cuarta fila
        bancoFila4.add(new Muro());
        for (int i = 0; i<7; i++) {
            bancoFila4.add(new Gris());
        }
        bancoFila4.add(new Muro());
        //Añadir la cuarta columna
        bancoColumnas.add(bancoFila4);

        //Elementos de la quinta fila
        bancoFila5.add(new Muro());
        bancoFila5.add(new Gris());
        bancoFila5.add(new Mesa());
        bancoFila5.add(new Gris());
        bancoFila5.add(new Mesa());
        bancoFila5.add(new Gris());
        bancoFila5.add(new Mesa());
        bancoFila5.add(new Gris());//espacio para controles
        bancoFila5.add(new Muro()); //espacio para controles
        //Añadir la quinta columna
        bancoColumnas.add(bancoFila5);

        //Elementos de la sexta fila
        for (int i = 0; i<7; i++) {
            bancoFila6.add(new Muro());
        }
        bancoFila6.add(new Puerta());
        //Añadir la sexta columna
        bancoColumnas.add(bancoFila4);

        //Añadir columnas al mapa
        banco.setCeldas(bancoColumnas);


        map = principal;

    }
        @Override
        public boolean onTouchEvent (MotionEvent event){
            if(changingMap) {
                changingMap = false;
                return true;
            }
            map.getCeldas().get((int)event.getY()*map.getAltura()/height).get((int) event.getX()*map.getAnchura()/width).onTouch(this);
            for (Sprite s : map.getEntities()) {
                if (s.isCollition(event.getX(), event.getY())) {
                    s.onTouch(this, event);
                    break;
                }
            }
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
            height = canvas.getHeight();
            width = canvas.getWidth();

            int height = canvas.getHeight() / map.getCeldas().size();
            int width = canvas.getWidth() / map.getCeldas().get(0).size();
            //Iteration over all cells to draw each one
            for (int i = 0; i < map.getCeldas().size(); i++) {
                for (int j = 0; j < map.getCeldas().get(i).size() && i < map.getCeldas().size(); j++) {
                    Rect r = new Rect(width * j, height * i, width * (j + 1), height * (i + 1));
                    Bitmap bmp = map.getCeldas().get(i).get(j).getResource();
                    canvas.drawBitmap(bmp, null, r, null);
                }
            }

            //draw map objects
            for (Sprite s : map.getEntities()) {
                s.draw(canvas);
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

    public static Character getCharacter () {
        return (Character) entities.stream().filter(x -> x.getClass() == Character.class).collect(Collectors.toList()).get(0);
    }

    public static Mapa getCurrentMap() {
        return map;
    }
    public static void changeMapTo(GameView gameView, @Nullable String claseOrigen, String mapaDestino){
        changingMap = true;
        switch (mapaDestino){
            case "principal":{
                map = principal;
                for(int i = 0; i<principal.getCeldas().size();i++){
                    for (int j = 0; j<principal.getCeldas().get(i).size(); j++){
                        try {
                            if(principal.getCeldas().get(i).get(j).getClass()== Class.forName("dsa.dsa_app.map.celdas."+claseOrigen)){
                                getCharacter().move(j*gameView.getWidth()/principal.getCeldas().get(0).size(),
                                        i*gameView.getHeight()/principal.getCeldas().size());
                            }
                        } catch (ClassNotFoundException e) {
                        }
                    }

                }
                break;
            }
            case "orfanato":{
                map = orfanato;

                break;
            }
            case "banco":{
                map = banco;
                break;
            }
        }
    }
}