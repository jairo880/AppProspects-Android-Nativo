package com.aplication.com.aplication1.Repositirios;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jairo880 on 14/07/16.
 */
public class Repositorios {


    Gson gson = new GsonBuilder().setLenient().create();

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://directotesting.igapps.co")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

}
