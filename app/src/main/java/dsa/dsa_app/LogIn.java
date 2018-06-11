package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {

    //Base URL of server
    public static final String baseURL = "http://192.168.42.209:8080/myapp/";
    private MapRest mapServices; //modifico con nombre interfaz

    EditText email;
    EditText passw;
    int res;
    String email1;
    String passw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);
        this.setTitle("Log in");

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
        Intent intent = new Intent(this, Register.class);
        intent.putExtra("email2", email1);
        intent.putExtra("passw2", passw1);
        startActivity(intent);
    }

    public void loginServer(View view){ //editar este campo para conectar con Servidor

        mapServices.getLogIn().enqueue( //funcion que he definido
                new Callback<Usuario>(){ //pongo lo que retorno
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response){
                        if(response.isSuccessful()){ //pongo toda la funcion cuando me funciona el retrofit
                            //EDITAR CON LA RESPUESTA DE DANI
                            Intent i = new Intent(getApplicationContext(), UserMain.class);
                            startActivity(i);
                        }
                        else{
                            //Logger
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t){
                        //Logger
                    }
                }
        );
    }
}
