package dsa.dsa_app.rest;

import java.util.List;

import dsa.dsa_app.Register;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface MapRest {

    /*@POST("funciones/userLogin/")
    Call<Usuario> getLogIn();*/

    @POST("funciones/infoUsuario") //LOGIN REAL
    Call<Boolean> consultarUsuario (@Body Usuario u);

    @POST("funciones/crearUsuario3") //REGISTER REAL
    //@FormUrlEncoded
    Call<Boolean> crearUsuario (@Body Usuario u);

    @POST("funciones/cambiarPass") //CAMBIAR PASSWORD
    Call<Boolean> cambiarPass (@Body Usuario u);

    @GET("/funciones/listaUsuarios") //LISTA DE TODOS LOS USUARIOS
    Call<List<Usuario>> listaUsuarios();

//            @Path("nombre") String nombre,
//                                 @Path("password") String password,
//                                 @Path("email") String email);

}

//    public void crearUsuario(  @Field("nombre") String nombre,
//                               @Field("password") String password,
//                               @Field("email") String email);


    //     Call<Usuario> crearUsuario(       @Field("nombre") String nombre,
//            @Field("password") String password,
//            @Field("email") String email,
//            Callback<Response> callback);


    //@Headers("Content-type: application/json")
//    @POST("map/ crearUsuario/")
//    Observable<Response<InstRegServerResponse>> crearUsuario(@Body InstRegServerRequest post);
    //Call <ModelClass> getCrearUsuario();





