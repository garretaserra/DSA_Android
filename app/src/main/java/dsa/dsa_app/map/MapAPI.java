package dsa.dsa_app.map;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MapAPI {

    @GET("/map/list")
    Call<String> getMapList();

    @GET("/getbyname")
    Call<Map> getMap(@Body String nombremapa);
}
