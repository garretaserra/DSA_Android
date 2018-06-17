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
import dsa.dsa_app.map.celdas.Camino;
import dsa.dsa_app.map.celdas.Celda;
import dsa.dsa_app.map.celdas.Muro;
import dsa.dsa_app.map.celdas.Rio;
import dsa.dsa_app.visuals.Arrows;
import dsa.dsa_app.visuals.Character;
import dsa.dsa_app.visuals.Sprite;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private List<Sprite> entities = new ArrayList<>();
    private String currentMap;
    private Mapa map;
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
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }


        });

        //Adding entities
        entities.add(new Character(this, BitmapFactory.decodeResource(getResources(), R.drawable.protagonista)));
        entities.add(new Arrows(this, BitmapFactory.decodeResource(getResources(),R.drawable.arrows)));
        map = new Mapa();
        map.setNombre("Proba");
        ArrayList<ArrayList<Celda>> mapCells = new ArrayList<>();
        ArrayList<Celda> r1 = new ArrayList<>();
        for(int i = 0; i<9;i++) {
            r1.add(new Camino());
        }
        for(int i = 0; i<6; i++){
            mapCells.add(r1);
        }
        map.setCeldas(mapCells);
        map.setAnchura(9);
        map.setAltura(6);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        for(Sprite s : entities) {
            if (s.isCollition(event.getX(), event.getY())) {
                s.onTouch(this, event);
                break;
            }
        }
        return true;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);

        //Check if map is already loaded
        if(!map.getNombre().equals(currentMap)) {
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
        for(Sprite s : entities.stream().filter(x->x.getClass()==Character.class).collect(Collectors.toList())){
            s.draw(canvas);
        }

        //draw arrows
        for(Sprite s : entities.stream().filter(x->x.getClass()==Arrows.class).collect(Collectors.toList())){
            s.draw(canvas);
        }
    }


    public Character getCharacter(){
        return (Character) entities.stream().filter(x->x.getClass()==Character.class).collect(Collectors.toList()).get(0);
    }

}