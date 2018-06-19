package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListOfUsers extends AppCompatActivity {

    public static final String baseURL = "http://192.168.1.41:8080/myapp/";

    private MapRest mapServices;

    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz

        //conecto con servidor y hago función register
        mapServices.listaUsuarios().enqueue( //funcion que he definido
                new Callback<List<Usuario>> () { //pongo lo que retorno
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        int statusCode = response.code();

                        if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit
                        //boolean r = response.body().booleanValue();

                        //if (response.code() == 201) {
                            Toast t = Toast.makeText(getApplicationContext(), "Lista obtenida, code:" + response.code(), Toast.LENGTH_LONG);
                            t.show();

                            List<Usuario> userList = response.body();
                            ListView lv = (ListView) findViewById(R.id.followers_list);
                            UsuarioArrayAdapter uaa = new UsuarioArrayAdapter(getApplicationContext(), userList);
                            lv.setAdapter(uaa);

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
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        //Logger
                        Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexión", Toast.LENGTH_LONG);
                        a.show();
                        //al final de la tasca
                        pb1.setVisibility(ProgressBar.INVISIBLE);
                    }
                }
        );
    }


    public void back(View view){

        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        Intent intentOj = new Intent(ListOfUsers.this, UserMain.class);
        startActivity(intentOj);

        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

    }
}
