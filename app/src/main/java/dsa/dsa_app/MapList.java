package dsa.dsa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import dsa.dsa_app.map.MapAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapList extends AppCompatActivity {

    //Base URL of server
    public static final String baseURL = "http://192.168.42.209:8080/myapp/";

    private MapAPI mapServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map_list);

        //start API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapServices = retrofit.create(MapAPI.class);
    }


    public void getMapList(View view){
        mapServices.getMapList().enqueue(
        new Callback<List<String>>(){
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response){
                if(response.isSuccessful()){
                    TextView tx = (TextView)findViewById(R.id.textView);
                    tx.setText(response.body().toString());
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
