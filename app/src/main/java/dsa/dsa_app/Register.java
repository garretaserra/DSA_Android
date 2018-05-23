package dsa.dsa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

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
        }
        else
        {
            Toast t = Toast.makeText(getApplicationContext(), "The passwords don't match", Toast.LENGTH_LONG);
            t.show();
        }
    }

}
