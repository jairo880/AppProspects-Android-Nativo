package com.aplication.com.aplication1.Interfaces;

import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Models.Prospects;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by jairo880 on 13/07/16.
 */
public interface IServiceClient {

    @GET("/application/login")
    Call<Cliente> getCliente(@Query("email")String email, @Query("password")String password);

    @GET("/sch/prospects.json")
    Call<List<Prospects>> getProspects(@Header("token") String token);

}

