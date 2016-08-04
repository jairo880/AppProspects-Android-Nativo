package com.aplication.com.aplication1.Acivity;

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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aplication.com.aplication1.Adaptador.ProspectsAdapter;
import com.aplication.com.aplication1.Interfaces.IProspects;
import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.Presenter.ProductsPresenter;
import com.aplication.com.aplication1.R;

import java.util.ArrayList;
import java.util.List;


public class ProspectsActivity extends AppCompatActivity implements IProspects {

    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private ListView listView;
    private ImageView imageView;
    private TextView NombreUsuario;
    private ImageButton imageButtonCarrito;

    String Email;
    ImageButton button;


    ProductsPresenter productsPresenter = new ProductsPresenter();

    ProspectsAdapter Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productsPresenter.addView(ProspectsActivity.this);

        final SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        productsPresenter.getProspects(prefs.getString("authToken", "").toString());

        imageView = (ImageView) findViewById(R.id.imageView);
        NombreUsuario = (TextView) findViewById(R.id.NombreUsuario);
        button = (ImageButton) findViewById(R.id.imageButtonlogout);
        imageButtonCarrito = (ImageButton) findViewById(R.id.imageButton);

        Intent intent = getIntent();

        Email = intent.getStringExtra("email").toString();

        NombreUsuario.setText(Email);

        Toast.makeText(getApplicationContext(), R.string.mensaje_bienvenida, Toast.LENGTH_SHORT).show();

        if (intent.getStringExtra("email").toString().equals(prefs.getString("email", getString(R.string.email_no_encontrado)))) {

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar));

        }

        imageButtonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProspectsActivity.this, R.string.carritoCompras , Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ProspectsActivity.this, R.string.Desarrollo, Toast.LENGTH_SHORT).show();

            }
        });

    }

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

                        Intent intent = new Intent(ProspectsActivity.this, PopupDll.class);
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

        final SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProspectsActivity.this);
        alertDialog.setTitle("Cerrar Sessiòn").setMessage("¿Està seguro que desèa cerrar la sessiòn?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        SharedPreferences.Editor editor = prefs.edit();

                        if (prefs.getString("recordarUsuario", "No se encontrò el campo recordar usuario").equals("true")) {

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
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        alertDialog.show();

    }

    public void FinishActivity(){
        Intent intent = new Intent(ProspectsActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}






