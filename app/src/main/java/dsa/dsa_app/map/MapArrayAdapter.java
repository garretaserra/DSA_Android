package dsa.dsa_app.map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import dsa.dsa_app.R;

public class MapArrayAdapter extends ArrayAdapter<String> {
    public MapArrayAdapter(@NonNull Context context, List<String> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String currentname = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.maparray, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.mapname);
        tv.setText(currentname);

        ImageButton ib = (ImageButton)convertView.findViewById(R.id.download_btn);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Download Map

            }
        });

        return convertView;
    }
}
