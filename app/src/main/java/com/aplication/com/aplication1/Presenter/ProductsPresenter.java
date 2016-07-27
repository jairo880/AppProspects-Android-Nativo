package com.aplication.com.aplication1.Presenter;
import com.aplication.com.aplication1.Interfaces.IProspects;
import com.aplication.com.aplication1.Interfaces.IServiceClient;
import com.aplication.com.aplication1.Interfaces.Ilogin;
import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.Repositiries.Repositorios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jairo880 on 15/07/16.
 */
public class ProductsPresenter {

    IProspects Iprospects;

    public void addView(IProspects Iprospects){

        this.Iprospects= Iprospects;
    }

    Repositorios repositorios = new Repositorios();

    public void getProspects(final String authToken) {


        final IServiceClient service = repositorios.retrofit.create(IServiceClient.class);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Response<List<Prospects>> prospects = null;
                try {
                    prospects = service.getProspects(authToken).execute();

                    ShowProspects(prospects.body());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();

    }

    private void ShowProspects(List<Prospects> prospects) {

        Iprospects.ShowProspects(prospects);

    }

}
