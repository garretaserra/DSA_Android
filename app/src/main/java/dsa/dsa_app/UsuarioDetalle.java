package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioDetalle extends AppCompatActivity {

    //public static final String baseURL = "http://192.168.1.41:8080/myapp/";
    public static final String baseURL = "http://192.168.1.41:8080/myapp/"; //Sara

    private MapRest mapServices;

    String emailinfo;
    EditText email;
    ProgressBar pb1;
    List<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalle);

        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

        Intent intent = getIntent();
        emailinfo = intent.getStringExtra("email1");

        email = (EditText) findViewById (R.id.idemail_txt);

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz

        //List<Usuario> userList = response.body();
        List<Usuario> userList = new ArrayList<>();
        Usuario u1 = new Usuario(1, "Sergi","123456", "sergi@gmail.com","http://www.dysconcsa.com/img/user.png",1);
        Usuario u2 = new Usuario(2,"Sara", "123456", "sara@gmail.com","http://www.dysconcsa.com/img/user.png",1);
        userList.add(u1);
        userList.add(u2);

        if (emailinfo != null) {
            //TextView n = (TextView) findViewById(R.id.myemail);
            //n.setText("Email: "+emailinfo);
            email.setText(emailinfo);

            final Button searchU = (Button) findViewById(R.id.searchUser);
            searchU.setEnabled(false); //Asigna valor false.

            final EditText et = (EditText)findViewById(R.id.idemail_txt);
            final String idEmail = et.getText().toString().trim();

            Toast.makeText(UsuarioDetalle.this, "Peticion correcta", Toast.LENGTH_LONG).show();
            Log.d("onResponse", "onResponse. Code" + 200 + "resultado:");

            for(Usuario user: userList){
                if (user.getEmail().equals(idEmail)) {
                    ListView lv = (ListView) findViewById(R.id.followers_list);
                    UsuarioDetalleArrayAdapter uaa = new UsuarioDetalleArrayAdapter(getApplicationContext(), userList);
                    lv.setAdapter(uaa);

                    pb1.setVisibility(ProgressBar.INVISIBLE);//al final de la tasca
                }
            }

//            //conecto con servidor y hago función register
//            mapServices.listaUsuarios().enqueue( //funcion que he definido
//                    new Callback<List<Usuario>>() { //pongo lo que retorno
//                        @Override
//                        public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
//                           // int statusCode = response.code();
//                            int statusCode = 200;
//                           // if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit
//
//                                Toast.makeText(UsuarioDetalle.this, "Peticion correcta", Toast.LENGTH_LONG).show();
//                                Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode) + "resultado:");
//
//                                //List<Usuario> userList = response.body();
//                                List<Usuario> userList = new ArrayList<>();
//                                Usuario u1 = new Usuario(1, "Sergi","123456", "sergi@gmail.com","http://www.dysconcsa.com/img/user.png",1);
//                                Usuario u2 = new Usuario(2,"Sara", "123456", "sara@gmail.com","http://www.dysconcsa.com/img/user.png",1);
//                                userList.add(u1);
//                                userList.add(u2);
//
//                                for(Usuario user: userList){
//                                    if (user.getEmail().equals(idEmail)) {
//                                        ListView lv = (ListView) findViewById(R.id.followers_list);
//                                        UsuarioDetalleArrayAdapter uaa = new UsuarioDetalleArrayAdapter(getApplicationContext(), userList);
//                                        lv.setAdapter(uaa);
//
//                                        pb1.setVisibility(ProgressBar.INVISIBLE);//al final de la tasca
//                                    }
//                                }
//                            }
//                            else {
//                                Toast t = Toast.makeText(getApplicationContext(), "Error, code:" + response.code(), Toast.LENGTH_LONG);
//                                t.show();
//                                //al final de la tasca
//                                pb1.setVisibility(ProgressBar.INVISIBLE);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Usuario>> call, Throwable t) {
//                            //Logger
//                            Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexion", Toast.LENGTH_LONG);
//                            a.show();
//                            //al final de la tasca
//                            pb1.setVisibility(ProgressBar.INVISIBLE);
//                        }
//                    }
//            );
        }
    }

    public void bucarUsuario(View view){
        final EditText et = (EditText)findViewById(R.id.idemail_txt);
        final String idEmail = et.getText().toString().trim();

        //conecto con servidor y hago función register
//        mapServices.listaUsuarios().enqueue( //funcion que he definido
//                new Callback<List<Usuario>>() { //pongo lo que retorno
//                    @Override
//                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
//                        int statusCode = response.code();
//
//                        if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit

                            Toast.makeText(UsuarioDetalle.this, "Peticion correcta", Toast.LENGTH_LONG).show();
                            Log.d("onResponse", "onResponse. Code" + 200 + "resultado:");

                           // List<Usuario> userList = response.body();

                            for(Usuario user: userList){
                                if (user.getEmail().equals(idEmail)) {
                                    ListView lv = (ListView) findViewById(R.id.followers_list);
                                    UsuarioDetalleArrayAdapter uaa = new UsuarioDetalleArrayAdapter(getApplicationContext(), userList);
                                    lv.setAdapter(uaa);

                                    pb1.setVisibility(ProgressBar.INVISIBLE);//al final de la tasca
                                }
                            }
                        }
//                        else {
//                            Toast t = Toast.makeText(getApplicationContext(), "Error, code:" + response.code(), Toast.LENGTH_LONG);
//                            t.show();
//                            //al final de la tasca
//                            pb1.setVisibility(ProgressBar.INVISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
//                        //Logger
//                        Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexion", Toast.LENGTH_LONG);
//                        a.show();
//                        //al final de la tasca
//                        pb1.setVisibility(ProgressBar.INVISIBLE);
//                    }
//                }
//        );
//    }}

    public void back(View view){

        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        Intent intentOj = new Intent(UsuarioDetalle.this, UserMain.class);
        startActivity(intentOj);

    }
}
