package com.aplication.com.aplication1.Acivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplication.com.aplication1.Constantes.Constante;
import com.aplication.com.aplication1.Interfaces.Ilogin;
import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Models.Error;
import com.aplication.com.aplication1.Presenter.LoginPresenter;
import com.aplication.com.aplication1.R;

import java.util.ArrayList;



public class LoginActivity extends AppCompatActivity implements Ilogin {

    EditText CajaTextoEmail;
    EditText CajaTextoPassword;
    Button btnRegistrarse;
    Button btnLogin;

    String CorreoPruebaSharedPreference="";
    String PasswrodPruebaSharedPreference="";

    LoginPresenter presentadorLogin = new LoginPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CajaTextoEmail = (EditText)findViewById(R.id.editText);
        CajaTextoPassword = (EditText)findViewById(R.id.editText2);

        btnRegistrarse = (Button) findViewById(R.id.btn_registrarse);
        btnLogin = (Button) findViewById(R.id.btn_login);

        CajaTextoEmail.setText(CorreoPruebaSharedPreference);
        CajaTextoPassword.setText(PasswrodPruebaSharedPreference);

        presentadorLogin.addView(LoginActivity.this);

        ValidarRegistrosPreferencias();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (presentadorLogin.validarDatosVacios(CajaTextoEmail.getText().toString(), CajaTextoPassword.getText().toString())) {

                    Toast.makeText(LoginActivity.this, R.string.mensaje_error_cantidad_caracteres_login, Toast.LENGTH_SHORT).show();
                    CajaTextoEmail.setText("");
                    CajaTextoPassword.setText("");

                } else {

                    if (presentadorLogin.ValidarCampos(CajaTextoEmail.getText().toString(), CajaTextoPassword.getText().toString())) {

                        Toast.makeText(getApplicationContext(), R.string.mensaje_error_email_invalido, Toast.LENGTH_SHORT).show();
                        CajaTextoEmail.setText("");

                    } else {

                        presentadorLogin.ValidarUsuario(CajaTextoEmail.getText().toString(), CajaTextoPassword.getText().toString());

                    }

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

    public void RedirRegistro(){

        Intent intent = new Intent(getApplicationContext(), RegistrarseActivity.class);
        startActivity(intent);

    }

    @Override
    public void Registrarse(View view) {

    }

    @Override
    public void showClient(Cliente cliente) {

        String email;
        SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", cliente.getEmail().toString());
        editor.putString("authToken", cliente.getAuthoken().toString());
        editor.putString("success",cliente.getSucces().toString());
        editor.putString("password", CajaTextoPassword.getText().toString());
        editor.commit();

        email= prefs.getString("email", "Usuario no encontrado");

        Intent i = new Intent(LoginActivity.this, ProductsActivity.class);
        i.putExtra("email", prefs.getString("email", getString(R.string.email_no_encontrado)).toString());
        startActivity(i);

    }

    @Override
    public void showMessageError(final String error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(LoginActivity.this,getString(R.string.mensaje_error_usuario_incorrecto) , Toast.LENGTH_SHORT).show();
                CajaTextoEmail.setText("");
                CajaTextoPassword.setText("");

                System.out.println(error);
            }
        });

    }



    public void ValidarRegistrosPreferencias(){

        SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        if (presentadorLogin.ValidarRegistrosPreferencias(prefs)){

            CajaTextoEmail.setText(prefs.getString("email", ""));
            CajaTextoPassword.setText(prefs.getString("password", ""));

            // Toast.makeText(getApplicationContext(), R.string.usuario_existente_preferencias, Toast.LENGTH_SHORT).show();

            presentadorLogin.ValidarUsuario(prefs.getString("email", "Email no encontrado en preferencias"), prefs.getString("password", "Contraseña no encontrada en preferencias"));

            Intent i = new Intent(LoginActivity.this, ProductsActivity.class);
            i.putExtra("email", prefs.getString("email", getString(R.string.email_no_encontrado)).toString());
            startActivity(i);

        } else {

            Toast.makeText(getApplicationContext(),R.string.datos_login_necesarios , Toast.LENGTH_SHORT).show();

        }

    }
}
