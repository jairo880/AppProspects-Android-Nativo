package com.aplication.com.aplication1.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.aplication.com.aplication1.Acivity.LoginActivity;
import com.aplication.com.aplication1.Interfaces.IServiceClient;
import com.aplication.com.aplication1.Interfaces.Ilogin;
import com.aplication.com.aplication1.Models.*;
import com.aplication.com.aplication1.R;
import com.aplication.com.aplication1.Repositirios.Repositorios;
import com.aplication.com.aplication1.helper.InternetValidate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

/**
 * Created by jairo880 on 13/07/16.
 */
public class LoginPresenter {

    boolean datosVacios = true;
    String mensajeError = null;
    Ilogin ilogin;
    InternetValidate internetValidate;
    Repositorios repositorios = new Repositorios();
    LoginActivity loginActivity = new LoginActivity();

    public void addView(Ilogin ilogin){

        this.ilogin= ilogin;
    }

    public void Validar(String emailUsuario, String passwordUsuario, Boolean recordarContrasenia) {

        String EmailUsaurio = emailUsuario.toString().trim();
        String PasswordUsuario = passwordUsuario.toString().trim();

        if (EmailUsaurio.length() < 1 || PasswordUsuario.length() < 1 || EmailUsaurio.equals("") || PasswordUsuario.equals("")) {


            mensajeError = String.valueOf(R.string.mensaje_error_cantidad_caracteres_login);
            datosVacios = true;

            } else {

               if(ValidarCampos(EmailUsaurio, PasswordUsuario)==false){

                       Boolean recordarContraseniaBool = recordarContrasenia;

                       String recordarContraseniaString = String.valueOf(recordarContraseniaBool);

                       ValidarUsuario(EmailUsaurio,PasswordUsuario, recordarContraseniaString);

                   }

               }

            }

    public boolean ValidarCampos(String EmailUsuario, String PasswordUsuario) {

        boolean CamposInvalidos;

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String email = EmailUsuario;

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {

            CamposInvalidos = false;

        } else {

            CamposInvalidos = true;

        }

        return CamposInvalidos;

    }


    public void ValidarUsuario(final String EmailUsuario, final String PasswordUsuario, final String recordarUsuario) {


        final IServiceClient service = repositorios.retrofit.create(IServiceClient.class);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    final  Response<Cliente> response = service.getCliente(EmailUsuario, PasswordUsuario).execute();


                    if(response.body() != null){

                        cathResponse(response ,recordarUsuario);

                    }else{

                        cathResponseError(response);
                    }
                } catch (IOException e) {

                    System.out.println("Error servicio lg");

                }
            }
        });

        thread.start();

    }

    public void cathResponse(Response<Cliente> response, String recordarUsuario) {

        if (response.message().toString().equals("OK")) {
            response.body();
            ilogin.showClient(response.body(), recordarUsuario);
        }

    }

    public void cathResponseError(Response response){

        try {

            ilogin.showMessageError(response.errorBody().string().toString());

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public boolean ValidarRegistrosPreferencias(SharedPreferences prefs){

        boolean RegistrosPreferencias;

        if(prefs.getString("email", "").length() > 0 && prefs.getString("authToken", "").length() > 0) {

            RegistrosPreferencias = true;

        }else{

            RegistrosPreferencias = false;
        }

        return RegistrosPreferencias;

    }

}
