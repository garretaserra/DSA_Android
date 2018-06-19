package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserMain extends AppCompatActivity {

    TextView n;
    String emailinfo;

    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        this.setTitle("Menu");

        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

        Intent intent = getIntent();
        emailinfo = intent.getStringExtra("email1");

        if (emailinfo != null) {
            n = (TextView) findViewById(R.id.email);
            n.setText("Email: "+emailinfo);
        }
    }

    public void mapList(View view){
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);
        Intent i = new Intent(this, MapList.class);
        startActivity(i);
    }

    public void game(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }

    public void userList(View view){
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);
        Intent i = new Intent(this, ListOfUsers.class);
        startActivity(i);
    }

    public void myPlayerInfo(View view){
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);
        Intent i = new Intent(this,UserInfo.class);
        i.putExtra("email1", n.getText().toString());
        startActivity(i);
    }

    public void anyPlayerInfo(View view){
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);
        Intent i = new Intent(this,UserInfo.class);
        startActivity(i);
    }
}
