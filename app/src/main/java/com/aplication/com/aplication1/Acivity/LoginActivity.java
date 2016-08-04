package com.aplication.com.aplication1.Acivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aplication.com.aplication1.Interfaces.Ilogin;
import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Presenter.LoginPresenter;
import com.aplication.com.aplication1.R;
import com.aplication.com.aplication1.helper.InternetValidate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends AppCompatActivity implements Ilogin {

    EditText CajaTextoEmail;
    EditText CajaTextoPassword;
    Button btnRegistrarse;
    Button btnLogin;
    CheckBox checkBox;
    String CorreoPruebaSharedPreference = "";
    String PasswrodPruebaSharedPreference = "";
    LoginPresenter presentadorLogin;
    InternetValidate internetValidate;
    Boolean recordarContrasenia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presentadorLogin = new LoginPresenter();
        internetValidate = new InternetValidate();


        //Hacerle set de un color al elemento Bar superior de la actividad.
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#303030")));

        CajaTextoEmail = (EditText) findViewById(R.id.editText);
        CajaTextoPassword = (EditText) findViewById(R.id.editText2);

        btnRegistrarse = (Button) findViewById(R.id.btn_registrarse);
        btnLogin = (Button) findViewById(R.id.btn_login);

        CajaTextoEmail.setText(CorreoPruebaSharedPreference);
        CajaTextoPassword.setText(PasswrodPruebaSharedPreference);

        checkBox = (CheckBox) findViewById(R.id.CheckRecordarUsuario);

        presentadorLogin.addView(LoginActivity.this);

        ValidarRegistrosPreferencias();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(internetValidate(getApplicationContext())){
                    recordarContrasenia = checkBox.isChecked();
                    presentadorLogin.Validar(CajaTextoEmail.getText().toString(), CajaTextoPassword.getText().toString(), recordarContrasenia);
                } else {

                    Toast.makeText(LoginActivity.this, R.string.noConectionManager, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedirRegistro();
            }
        });

    }

    public void RedirRegistro() {

        Intent intent = new Intent(getApplicationContext(), RegistrActivity.class);
        startActivity(intent);

    }

    @Override
    public void Registrarse(View view) {

    }


    @Override
    public void showClient(Cliente cliente, String recordarUsuario) {

        String email;

        SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", cliente.getEmail().toString());
        editor.putString("authToken", cliente.getAuthoken().toString());
        editor.putString("success", cliente.getSucces().toString());
        editor.putString("password", CajaTextoPassword.getText().toString());
        editor.putString("recordarUsuario", String.valueOf(recordarUsuario).toString());
        editor.commit();

        email = prefs.getString("email", "Usuario no encontrado");

        Intent i = new Intent(LoginActivity.this, ProspectsActivity.class);
        i.putExtra("email", prefs.getString("email", getString(R.string.email_no_encontrado)).toString());
        startActivity(i);
        this.finish();

    }

    @Override
    public void showMessageError(final String error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(LoginActivity.this, getString(R.string.mensaje_error_usuario_incorrecto), Toast.LENGTH_SHORT).show();
                CajaTextoPassword.setText("");

                System.out.println(error);
            }
        });

    }

    public void ValidarRegistrosPreferencias() {

        SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        if (prefs.getString("recordarUsuario", "No se encontrò el campo").equals("true")) {
            CajaTextoEmail.setText(prefs.getString("email", ""));
        }

        if (presentadorLogin.ValidarRegistrosPreferencias(prefs)) {

            CajaTextoEmail.setText(prefs.getString("email", ""));
            CajaTextoPassword.setText(prefs.getString("password", ""));

            // Toast.makeText(getApplicationContext(), R.string.usuario_existente_preferencias, Toast.LENGTH_SHORT).show();

            presentadorLogin.ValidarUsuario(prefs.getString("email", "Email no encontrado en preferencias"), prefs.getString("password", "Contraseña no encontrada en preferencias"), prefs.getString("recordarUsuario", "No se encontrò el campo recordar Usuario"));

            Intent i = new Intent(LoginActivity.this, ProspectsActivity.class);
            i.putExtra("email", prefs.getString("email", getString(R.string.email_no_encontrado)).toString());
            startActivity(i);

        } else {

            Toast.makeText(getApplicationContext(), R.string.datos_login_necesarios, Toast.LENGTH_SHORT).show();

        }

    }
    public boolean internetValidate(Context context){

        return internetValidate.isConnected(context);
    }
}
