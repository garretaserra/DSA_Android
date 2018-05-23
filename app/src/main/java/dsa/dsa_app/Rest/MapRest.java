package dsa.dsa_app.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MapRest {

    @POST("map/userLogin/")
    Call<Usuario> getLogIn();

    @POST("map/ crearUsuario/")
    Call<Boolean> getCrearUsuario();


}
