package dsa.dsa_app.map;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dsa.dsa_app.R;
import dsa.dsa_app.visuals.Arrows;
import dsa.dsa_app.visuals.Character;
import dsa.dsa_app.visuals.Sprite;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private List<Sprite> entities = new ArrayList<>();

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
        entities.add(new Character(this, BitmapFactory.decodeResource(getResources(), R.drawable.maincharacter)));
        entities.add(new Arrows(this, BitmapFactory.decodeResource(getResources(),R.drawable.arrows)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        for(Sprite s : entities) {
            if (s.isCollition(event.getX(), event.getY())) {
                s.onTouch(this, event);
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        //draw map



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