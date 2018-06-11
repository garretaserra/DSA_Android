package dsa.dsa_app.rest;

import retrofit2.Call;
import retrofit2.http.POST;

public interface MapRest {

    @POST("map/userLogin/")
    Call<Usuario> getLogIn();

    @POST("map/ crearUsuario/")
    Call<Boolean> getCrearUsuario();


}
