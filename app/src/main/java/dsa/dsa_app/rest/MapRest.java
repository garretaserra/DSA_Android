package dsa.dsa_app.rest;

import dsa.dsa_app.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MapRest {

    @POST("map/userLogin/")
    Call<Usuario> getLogIn();

    @POST("map/ crearUsuario/")
    Observable<Response<tuUsuario>> crearUsuario(@Body Register signIn);
    //Call <ModelClass> getCrearUsuario();




}
