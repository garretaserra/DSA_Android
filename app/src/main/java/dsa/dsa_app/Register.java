package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dsa.dsa_app.Rest.MapRest;
import dsa.dsa_app.Rest.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    //Base URL of server
    public static final String baseURL = "http://192.168.42.209:8080/myapp/";
    private MapRest mapServices; //modifico con nombre interfaz

    EditText name;
    EditText email;
    EditText passw;
    EditText passw2;
    String res;
    String passw3;
    String passw4;
    String email1;
    String emailinfo;
    String passinfo;
    String nameinfo;
    String nameinfo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");

        Intent intent = getIntent();
        emailinfo = intent.getStringExtra("email2");
        passinfo = intent.getStringExtra("passw2");
        nameinfo2 = intent.getStringExtra("name");

        if (emailinfo != null) {
            //TextView n = (TextView) findViewById(R.id.myemail);
            //n.setText("Email: "+emailinfo);
            EditText n = (EditText) findViewById(R.id.email);
            n.setText(emailinfo);
        }

        if (passinfo != null){
            EditText p = (EditText) findViewById(R.id.passw);
            p.setText(passinfo);
        }

        if (nameinfo2 != null){
            EditText p = (EditText) findViewById(R.id.nom);
            p.setText(nameinfo2);
        }

        else{}

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapRest.class); //modifico con nombre interfaz

    }

    public void creaServer(View view) {
        //Guardo en variables lo que el usuario ecribe en la app en cada textBox

        name = (EditText) findViewById (R.id.nom);
        email = (EditText) findViewById (R.id.email); // poner el nombre de la cajita de texto "editText..."
        passw = (EditText) findViewById (R.id.passw);
        passw2 = (EditText) findViewById (R.id.passw2);

        passw3 = "3"+passw.getText();
        passw4 = "3"+passw2.getText();
        email1 = ""+email.getText();
        nameinfo = ""+name.getText();

        if (passw3.equals(passw4))
        {
            //conecto con servidor y hago función register
            mapServices.getCrearUsuario().enqueue( //funcion que he definido
                    new Callback<Boolean>(){ //pongo lo que retorno
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response){
                            if(response.isSuccessful()){ //pongo toda la funcion cuando me funciona el retrofit
                                boolean r = response.body().booleanValue();

                                if (r == true) {
                                    Intent i = new Intent(getApplicationContext(), UserMain.class);
                                    startActivity(i);
                                }

                                else {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                                    t.show();
                                }
                            }
                            else{
                                //Logger
                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t){
                            //Logger
                        }
                    }
            );
        }

        else
        {
            Toast t = Toast.makeText(getApplicationContext(), "Los passwords no coinciden", Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void actualizaServer(View view) {
        //Guardo en variables lo que el usuario ecribe en la app en cada textBox
        name = (EditText) findViewById (R.id.nom);
        email = (EditText) findViewById (R.id.email); // poner el nombre de la cajita de texto "editText..."
        passw = (EditText) findViewById (R.id.passw);
        passw2 = (EditText) findViewById (R.id.passw2);

        passw3 = "3"+passw.getText();
        passw4 = "3"+passw2.getText();
        email1 = ""+email.getText();
        nameinfo = ""+name.getText();


        if (passw3.equals(passw4)) {
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
