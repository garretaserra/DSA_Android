package dsa.dsa_app.map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MapAPI {

    @GET("/map/list")
    Call<String> getMapList();
}
