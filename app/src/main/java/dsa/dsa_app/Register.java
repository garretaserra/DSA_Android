package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    //Base URL of server
    public static final String baseURL = "http://192.168.1.41:8080/myapp/";
    //public static final String baseURL = "http://192.168.42.209:8080/myapp/";
    private MapRest mapServices; //modifico con nombre interfaz

    EditText name;
    EditText email;
    EditText passw;
    EditText passw2;

    String emailinfo;
    String passinfo;

    String res;

    String nameinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");

        name = (EditText) findViewById (R.id.nom);
        email = (EditText) findViewById (R.id.email); // poner el nombre de la cajita de texto "editText..."
        passw = (EditText) findViewById (R.id.passw);
        passw2 = (EditText) findViewById (R.id.passw2);

        Intent intent = getIntent();
        emailinfo = intent.getStringExtra("email1");
        passinfo = intent.getStringExtra("passw1");

        if (emailinfo != null) {
            //TextView n = (TextView) findViewById(R.id.myemail);
            //n.setText("Email: "+emailinfo);
            email.setText(emailinfo);
        }

        if (passinfo != null){
            passw.setText(passinfo);
        }

        else{}

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz

    }

    public void creaServer(String nombre, String password, String password2, String correo) {

        Usuario u = new Usuario(nombre, password, correo);

        emailinfo = ""+email.getText();
        nameinfo = ""+name.getText();

        if(passw.getText().equals(passw2.getText()))
        {/*
            //conecto con servidor y hago función register
            mapServices.getCrearUsuario().enqueue( //funcion que he definido
                    new Callback()<ModelClass>{ //pongo lo que retorno
                        @Override
                        public void onResponse(Call <ModelClass> call, Response <ModelClass> response){
                            if(response.isSuccessful()){ //pongo toda la funcion cuando me funciona el retrofit
                                //boolean r = response.body().booleanValue();
                                Toast a = Toast.makeText(getApplicationContext(), "Conexion bien", Toast.LENGTH_LONG);
                                a.show();

                                if (response.code()==201) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG);
                                    t.show();
                                    Intent i = new Intent(getApplicationContext(), UserMain.class);
                                    startActivity(i);
                                }

                                else if (response.code()==403) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error on registration", Toast.LENGTH_LONG);
                                    t.show();
                                }

                                else if (response.code()==400) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG);
                                    t.show();
                                }

                                else {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                                    t.show();
                                }
                            }
                            else{
                                //Logger
                                Toast t = Toast.makeText(getApplicationContext(), "Fallo de conexion", Toast.LENGTH_LONG);
                                t.show();
                            }
                        }
                        @Override
                        public void onFailure(Call <ModelClass> call, Throwable t){
                            //Logger
                            Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexion2", Toast.LENGTH_LONG);
                            a.show();
                        }
                    }
            );*/
        }

        else
        {
            Toast t = Toast.makeText(getApplicationContext(), "Los passwords no coinciden", Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void actualizaServer(View view) {

        emailinfo = ""+email.getText();
        nameinfo = ""+name.getText();

        if(passw.getText().equals(passw2.getText())) {
            //conecto con servidor y hago función register
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
        else
        {
            Toast t = Toast.makeText(getApplicationContext(), "The passwords don't match", Toast.LENGTH_LONG);
            t.show();
        }
    }

}
