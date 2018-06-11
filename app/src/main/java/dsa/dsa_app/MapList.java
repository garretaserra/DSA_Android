package dsa.dsa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import dsa.dsa_app.map.MapAPI;
import dsa.dsa_app.map.MapArrayAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);
        this.setTitle("Map List");
        getMapList();
    }


    public void getMapList(){
        MapAPI.getInstance().getMapList().enqueue(
        new Callback<List<String>>(){
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response){
                if(response.isSuccessful()){
                    ListView lv =(ListView)findViewById(R.id.maplist_lv);
                    MapArrayAdapter maa = new MapArrayAdapter(getApplicationContext(),response.body());
                    lv.setAdapter(maa);
                }
                else{
                    //Logger
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t){
                //Logger
            }
        }
        );
    }
}
