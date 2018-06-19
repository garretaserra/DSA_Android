package dsa.dsa_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dsa.dsa_app.rest.Usuario;

public class UsuarioArrayAdapter extends ArrayAdapter<Usuario> {
    public UsuarioArrayAdapter(@NonNull Context context, List<Usuario> resource) {
        super(context, 0,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Usuario u = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_layout, parent, false);
        }

        //Picasso.with(super.getContext()).load(u.getImage()).into((ImageView) convertView.findViewById(R.id.foto_img));
        //Picasso.with(super.getContext()).load("http://www.dysconcsa.com/img/user.png").into((ImageView) convertView.findViewById(R.id.foto_img));
        Picasso.with(super.getContext()).load(""+u.getImagen()).into((ImageView) convertView.findViewById(R.id.foto_img));
        TextView et = (TextView)convertView.findViewById(R.id.username_txt);
        TextView et2 = (TextView)convertView.findViewById(R.id.idemail_txt);
        TextView et3 = (TextView)convertView.findViewById(R.id.obj1_txt);

        et.setText(u.getNombre()); //cojo los nombres de los autores
        et2.setText(u.getEmail());

        int x = u.getObj1();
        et3.setText("Objetos:"+ Integer.toString(x));

        return convertView;
    }
}
