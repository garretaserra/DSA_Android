package dsa.dsa_app.map.celdas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.widget.Toast;

import dsa.dsa_app.App;
import dsa.dsa_app.R;
import dsa.dsa_app.map.GameView;
import dsa.dsa_app.visuals.Objeto;

public class Casa extends Celda {
    private static Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.casa);

    @Override
    public Bitmap getResource() {
        return bmp;
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }

    @Override
    public void onTouch(GameView gameView, MotionEvent event) {
        for(int i = 0; i<GameView.getCurrentMap().getCeldas().size();i++){
            for (int j = 0; j<GameView.getCurrentMap().getCeldas().get(i).size(); j++){
                try {
                    if(GameView.getCurrentMap().getCeldas().get(i).get(j).getClass()== Class.forName("dsa.dsa_app.map.celdas.Casa")){
                        if(GameView.getCharacter().isClose(gameView.getWidth()/GameView.getCurrentMap().getCeldas().get(0).size(),
                                i*gameView.getHeight()/GameView.getCurrentMap().getCeldas().size())) {
                            if(GameView.getCharacter().quitarObjeto("House Key")){
                                //Tiene la llave
                                Toast.makeText(App.getContext(), "This house is in ruins, visit the bank to be able to rebuild it.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(App.getContext(), "You need a key to enter", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }
}
