package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import dsa.dsa_app.rest.MapRest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CambioPassw extends AppCompatActivity {

    //Base URL of server
    //public static final String baseURL = "http://172.20.10.2:8080/myapp/";
    public static final String baseURL = "http://192.168.1.41:8080/myapp/"; //Sara
    //public static final String baseURL = "http://192.168.42.209:8080/myapp/";
    private MapRest mapServices; //modifico con nombre interfaz

    EditText email;
    String emailinfo;
    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_passw);
        this.setTitle("Change of password");

        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

        email = (EditText) findViewById (R.id.email);

        Intent intent = getIntent();
        emailinfo = intent.getStringExtra("email1");

        if (emailinfo != null) {
            email.setText(emailinfo);
        }

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz
    }


    public void volverLogIn(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        Intent intent = new Intent(this, UserMain.class);
        startActivity(intent);
    }
}