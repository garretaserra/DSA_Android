package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

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

    }



    public void registerServer(View view) {
        Intent intent = new Intent(this, Register.class);
        intent.putExtra("email2", email1);
        intent.putExtra("passw2", passw1);
        startActivity(intent);
    }

    public void loginServer(View view){ //editar este campo para conectar con Servidor
        Intent i = new Intent(this, UserMain.class);
        startActivity(i);
    }
}
