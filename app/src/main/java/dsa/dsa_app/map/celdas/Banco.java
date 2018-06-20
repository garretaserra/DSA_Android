package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;

public class Banco extends Celda {
    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.banco);

    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    @Override
    public void onTouch(GameView gameView, MotionEvent event){
        /*
        //Trobar on es troba el banc en el mapa
        //comprobar si les coordenades del banc estan a prop de les del personatge
        //en cas positiu canviar el mapa al del banc
         */
        for(int i = 0; i<GameView.getCurrentMap().getCeldas().size();i++){
            for (int j = 0; j<GameView.getCurrentMap().getCeldas().get(i).size(); j++){
                try {
                    if(GameView.getCurrentMap().getCeldas().get(i).get(j).getClass()== Class.forName("dsa.dsa_app.map.celdas.Banco")){
                        if(GameView.getCharacter().isClose(j*gameView.getWidth()/GameView.getCurrentMap().getCeldas().get(0).size(),
                                i*gameView.getHeight()/GameView.getCurrentMap().getCeldas().size())) {
                            GameView.changeMapTo(gameView, null, "banco");
                        }
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }
}
