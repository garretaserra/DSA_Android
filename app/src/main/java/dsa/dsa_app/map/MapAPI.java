package dsa.dsa_app.map;
import android.content.res.Resources;

import dsa.dsa_app.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapAPI {

    private static MapAPII instance;

    private MapAPI(){
        String baseURL = "http://" + "192.168.42.123" + ":8080/myapp/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        instance = retrofit.create(MapAPII.class);
    }

    public static MapAPII getInstance(){
        if(instance==null)
            new MapAPI();
        return instance;
    }
}
