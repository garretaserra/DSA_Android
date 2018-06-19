package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {

    //Base URL of server
    //public static final String baseURL = "http://172.20.10.2:8080/myapp/";
    public static final String baseURL = "http://192.168.1.41:8080/myapp/"; //Sara
    //public static final String baseURL = "http://192.168.42.209:8080/myapp/";
    private MapRest mapServices; //modifico con nombre interfaz

    EditText email;
    EditText passw;
    int res;

    String emailinfo;
    String passinfo;

    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);
        this.setTitle("Log in");

        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

        email = (EditText) findViewById (R.id.email); // poner el nombre de la cajita de texto "editText..."
        passw = (EditText) findViewById (R.id.passw);

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz

    }



    public void registerServer(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);


        Intent intent = new Intent(this, Register.class);
        intent.putExtra("email1", email.getText().toString());
        intent.putExtra("passw1", passw.getText().toString());
        startActivity(intent);
    }

    public void game(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }

    public void loginServer(View view){ //editar este campo para conectar con Servidor

        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        emailinfo = email.getText().toString().trim();
        passinfo = passw.getText().toString().trim();

        if (!TextUtils.isEmpty(passinfo) && !TextUtils.isEmpty(emailinfo)) {
                Usuario u = new Usuario(1,""+null, ""+passinfo, ""+emailinfo,""+null, 0);

                //conecto con servidor y hago función register
                mapServices.consultarUsuario(u).enqueue( //funcion que he definido
                        new Callback<Boolean>() { //pongo lo que retorno
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                int statusCode = response.code();

                                //if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit
                                //boolean r = response.body().booleanValue();

                                if (response.code() == 200) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Logged, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                    Intent i = new Intent(getApplicationContext(), UserMain.class);
                                    i.putExtra("email1", email.getText().toString());
                                    startActivity(i);
                                    //al final de la tasca
                                    pb1.setVisibility(ProgressBar.INVISIBLE);
                                }
                                else if (response.code() == 403) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error on login, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                    //al final de la tasca
                                    pb1.setVisibility(ProgressBar.INVISIBLE);
                                }

                                else {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                    //al final de la tasca
                                    pb1.setVisibility(ProgressBar.INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                //Logger
                                Toast a = Toast.makeText(getApplicationContext(), "Error on the connexion", Toast.LENGTH_LONG);
                                a.show();
                                //al final de la tasca
                                pb1.setVisibility(ProgressBar.INVISIBLE);
                            }
                        }
                );
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "Hay campos incompletos", Toast.LENGTH_LONG);
            t.show();
            //al final de la tasca
            pb1.setVisibility(ProgressBar.INVISIBLE);
        }

    }
}
