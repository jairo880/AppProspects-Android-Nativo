package com.aplication.com.aplication1.Acivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.aplication.com.aplication1.R;

public class RegistrarseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast toast = Toast.makeText(RegistrarseActivity.this,R.string.mensaje_vista_registro , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,20,22);
        toast.show();

    }
}
