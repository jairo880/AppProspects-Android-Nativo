package com.aplication.com.aplication1.Acivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.aplication.com.aplication1.Adaptador.ProspectsAdapter;
import com.aplication.com.aplication1.Interfaces.IProspects;
import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.Presenter.ProductsPresenter;
import com.aplication.com.aplication1.R;
import java.util.List;


public class ProspectsActivity extends AppCompatActivity implements IProspects {

    private ImageView imageView;
    private TextView NombreUsuario;
    private ImageButton imageButtonCarrito;
    String Email;
    ImageButton button;
    ProductsPresenter productsPresenter;
    ProspectsAdapter Adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productsPresenter = new ProductsPresenter();

        productsPresenter.addView(ProspectsActivity.this);

        final SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        productsPresenter.getProspects(prefs.getString("authToken", "").toString());

        imageView = (ImageView) findViewById(R.id.imageView);
        NombreUsuario = (TextView) findViewById(R.id.NombreUsuario);
       // button = (ImageButton) findViewById(R.id.imageButtonlogout);
       // imageButtonCarrito = (ImageButton) findViewById(R.id.imageButton);

        Intent intent = getIntent();

        Email = intent.getStringExtra("email").toString();

        NombreUsuario.setText(Email);

        Toast.makeText(getApplicationContext(), R.string.mensaje_bienvenida, Toast.LENGTH_SHORT).show();

        if (intent.getStringExtra("email").toString().equals(prefs.getString("email", getString(R.string.email_no_encontrado)))) {

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar));

        }

        //setListenersButtons();

    }

//    private void setListenersButtons() {//

//        imageButtonCarrito.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProspectsActivity.this, R.string.carritoCompras , Toast.LENGTH_SHORT).show();
//            }
//        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(ProspectsActivity.this, R.string.Desarrollo, Toast.LENGTH_SHORT).show();
//
//            }
//        });

//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Cierre_Session();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ShowProspects(final List<Prospects> prospects) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                recyclerView = (RecyclerView) findViewById(R.id.RecView);
                recyclerView.setHasFixedSize(true);

                final ProspectsAdapter Adapter = new ProspectsAdapter(prospects);

                Adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProspectsActivity.this, popupdll.class);
                        intent.putExtra("name", prospects.get(recyclerView.getChildLayoutPosition(v)).getName());
                        intent.putExtra("surname", prospects.get(recyclerView.getChildLayoutPosition(v)).getSurname());
                        intent.putExtra("telephone", prospects.get(recyclerView.getChildLayoutPosition(v)).getTelephone());
                        intent.putExtra("address", prospects.get(recyclerView.getChildLayoutPosition(v)).getAddress());
                        intent.putExtra("observation", prospects.get(recyclerView.getChildLayoutPosition(v)).getObservation());
                        startActivity(intent);

                    }
                });


                recyclerView.setAdapter(Adapter);

                recyclerView.setLayoutManager(
                        new LinearLayoutManager(ProspectsActivity.this, LinearLayoutManager.VERTICAL, false));

            }
        });

    }

    public void Cierre_Session(){

        progressDialog = ProgressDialog.show(ProspectsActivity.this,getString(R.string.progress_dialog_message_prospects_cierre_session), getString(R.string.progress_dialog_message_prospects_cierre_session), true);
        progressDialog.setCancelable(true);

        final SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProspectsActivity.this);
        alertDialog.setTitle(getString(R.string.tittle_cerrar_session)).setMessage(getString(R.string.message_cerrar_session))
                .setPositiveButton(getString(R.string.afirmacion_alert_dialog_cerrar_session), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        SharedPreferences.Editor editor = prefs.edit();

                        if (prefs.getString("recordarUsuario", getString(R.string.campo_recordar_contrasenia_no_encontrado)).equals("true")) {

                            editor.remove("authToken")
                                    .remove("success")
                                    .remove("password")
                                    .apply();

                        } else {

                            editor.clear().commit();

                        }

                        Toast.makeText(getApplicationContext(), R.string.mensaje_logout_preferencias, Toast.LENGTH_SHORT).show();

                        FinishActivity();

                    }
                })
                .setNegativeButton(getString(R.string.negacion_alert_dialog_cerrar_session), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        alertDialog.show();

    }

    public void FinishActivity(){
        Intent intent = new Intent(ProspectsActivity.this, LoginActivity.class);
        progressDialog.dismiss();
        startActivity(intent);
        this.finish();
    }

}






