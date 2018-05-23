package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class UserMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_main);
    }

    public void playGame(View view){
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }

    public void mapList(View view){
        Intent i = new Intent(this, MapList.class);
        startActivity(i);
    }

    public void playerInfo(View view){
        Intent i = new Intent(this,UserInfo.class);
        startActivity(i);
    }
}
