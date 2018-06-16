package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;
import dsa.dsa_app.rest.MapRest;
import dsa.dsa_app.rest.Usuario;
import okhttp3.ResponseBody;
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

    String nominfo;
    String emailinfo;
    String passinfo;
    String passinfo2;

    String res;

    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");

        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);

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

    public void creaServer(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        nominfo = name.getText().toString().trim();
        passinfo = passw.getText().toString().trim();
        passinfo2 = passw2.getText().toString().trim();
        emailinfo = email.getText().toString().trim();

        //if(!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(correo)) {

        if (!TextUtils.isEmpty(nominfo) && !TextUtils.isEmpty(passinfo) && !TextUtils.isEmpty(passinfo2) && !TextUtils.isEmpty(emailinfo)) {
            if (passinfo2.equals(passinfo)) {

                //mapServices.crearUsuario(""+nominfo, ""+passinfo, ""+emailinfo);
                Usuario u = new Usuario(1,""+nominfo, ""+passinfo, ""+emailinfo,""+null, 0);

                //conecto con servidor y hago función register
                mapServices.crearUsuario(u /*"" + nominfo, "" + passinfo, "" + emailinfo*/).enqueue( //funcion que he definido
                        new Callback<Boolean>() { //pongo lo que retorno
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                int statusCode = response.code();

                                //if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit
                                    //boolean r = response.body().booleanValue();

                                    if (response.code() == 201) {
                                        Toast t = Toast.makeText(getApplicationContext(), "Registered, code:" + response.code(), Toast.LENGTH_LONG);
                                        t.show();
                                        Intent i = new Intent(getApplicationContext(), UserMain.class);
                                        startActivity(i);
                                        //al final de la tasca
                                        pb1.setVisibility(ProgressBar.INVISIBLE);
                                    }
                                    else if (response.code() == 409) {
                                        Toast t = Toast.makeText(getApplicationContext(), "Error on registration, code:" + response.code(), Toast.LENGTH_LONG);
                                        t.show();
                                        //al final de la tasca
                                        pb1.setVisibility(ProgressBar.INVISIBLE);
                                    }

//                                    else if (response.code()==400) {
//                                        Toast t = Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG);
//                                        t.show();
//                                    }

                                    else {
                                        Toast t = Toast.makeText(getApplicationContext(), "Error, code:" + response.code(), Toast.LENGTH_LONG);
                                        t.show();
                                        //al final de la tasca
                                        pb1.setVisibility(ProgressBar.INVISIBLE);
                                    }
                                }
//                                else {
//                                    //Logger
//                                    Toast t = Toast.makeText(getApplicationContext(), "Response not successful", Toast.LENGTH_LONG);
//                                    t.show();
//
//                                    Toast.makeText (Register.this ,"This user already exist",Toast.LENGTH_SHORT).show();
//                                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
//                                }
//                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                //Logger
                                Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexion", Toast.LENGTH_LONG);
                                a.show();
                                //al final de la tasca
                                pb1.setVisibility(ProgressBar.INVISIBLE);
                            }
                        }
                );
            }
            else {
                Toast t = Toast.makeText(getApplicationContext(), "Los passwords no coinciden", Toast.LENGTH_LONG);
                t.show();
                //al final de la tasca
                pb1.setVisibility(ProgressBar.INVISIBLE);
            }
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "Hay campos incompletos", Toast.LENGTH_LONG);
            t.show();
            //al final de la tasca
            pb1.setVisibility(ProgressBar.INVISIBLE);
        }
    }


    public void volverLogIn(View view) {
        //inici de la tasca
        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);
        pb1.setVisibility(ProgressBar.VISIBLE);


        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);

        //al final de la tasca
        pb1.setVisibility(ProgressBar.INVISIBLE);
    }

    public void actualizaServer(View view) {
    }
}

       /* nominfo = name.getText().toString().trim();
        passinfo = passw.getText().toString().trim();
        passinfo2 = passw2.getText().toString().trim();
        emailinfo = email.getText().toString().trim();

        //if(!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(correo)) {

        if (!TextUtils.isEmpty(nominfo) && !TextUtils.isEmpty(passinfo) && !TextUtils.isEmpty(passinfo2) && !TextUtils.isEmpty(emailinfo)) {
            if (passinfo2.equals(passinfo)) {

                //mapServices.crearUsuario(""+nominfo, ""+passinfo, ""+emailinfo);
                Usuario u = new Usuario(1,""+nominfo, ""+passinfo, ""+emailinfo,""+null, 0);

                //conecto con servidor y hago función register
                mapServices.crearUsuario(u).enqueue( //funcion que he definido
                        new Callback<Boolean>() { //pongo lo que retorno
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                int statusCode = response.code();

                                //if (response.isSuccessful()) { //pongo toda la funcion cuando me funciona el retrofit
                                //boolean r = response.body().booleanValue();

                                if (response.code() == 201) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Registered, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                    Intent i = new Intent(getApplicationContext(), UserMain.class);
                                    startActivity(i);
                                }
                                else if (response.code() == 409) {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error on registration, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                }

//                                    else if (response.code()==400) {
//                                        Toast t = Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG);
//                                        t.show();
//                                    }

                                else {
                                    Toast t = Toast.makeText(getApplicationContext(), "Error, code:" + response.code(), Toast.LENGTH_LONG);
                                    t.show();
                                }
                            }
//                                else {
//                                    //Logger
//                                    Toast t = Toast.makeText(getApplicationContext(), "Response not successful", Toast.LENGTH_LONG);
//                                    t.show();
//
//                                    Toast.makeText (Register.this ,"This user already exist",Toast.LENGTH_SHORT).show();
//                                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
//                                }
//                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                //Logger
                                Toast a = Toast.makeText(getApplicationContext(), "Fallo de conexion", Toast.LENGTH_LONG);
                                a.show();
                            }
                        }
                );
            }
            else {
                Toast t = Toast.makeText(getApplicationContext(), "Los passwords no coinciden", Toast.LENGTH_LONG);
                t.show();
            }
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "Hay campos incompletos", Toast.LENGTH_LONG);
            t.show();
        }

    }*/



