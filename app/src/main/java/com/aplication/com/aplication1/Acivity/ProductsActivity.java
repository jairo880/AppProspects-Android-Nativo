package com.aplication.com.aplication1.Acivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aplication.com.aplication1.Adaptador.ListViewAdapter;
import com.aplication.com.aplication1.Interfaces.IProspects;
import com.aplication.com.aplication1.Models.Prospects;
import com.aplication.com.aplication1.Presenter.ProductsPresenter;
import com.aplication.com.aplication1.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class ProductsActivity extends AppCompatActivity implements IProspects {

    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private ListView listView;
    private ImageView imageView;
    private TextView NombreUsuario;

    String Email;
    ImageButton button;


    ProductsPresenter productsPresenter = new ProductsPresenter();

    ListViewAdapter Adapter;
    RecyclerView recyclerView;

  Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        productsPresenter.addView(ProductsActivity.this);

        final SharedPreferences prefs = this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        productsPresenter.getProspects(prefs.getString("authToken", "").toString());

        imageView = (ImageView) findViewById(R.id.imageView);
        NombreUsuario = (TextView) findViewById(R.id.NombreUsuario);
        button = (ImageButton) findViewById(R.id.imageButtonlogout);

        Intent intent = getIntent();

        Email = intent.getStringExtra("email").toString();

        NombreUsuario.setText(Email);

        Toast.makeText(getApplicationContext(), R.string.mensaje_bienvenida, Toast.LENGTH_SHORT).show();

        if (intent.getStringExtra("email").toString().equals(prefs.getString("email", getString(R.string.email_no_encontrado)))) {

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar));

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(ProductsActivity.this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), R.string.mensaje_logout_preferencias, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void ShowProspects(final List<Prospects> prospects) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                recyclerView = (RecyclerView) findViewById(R.id.RecView);
                recyclerView.setHasFixedSize(true);

                final ListViewAdapter Adapter = new ListViewAdapter(prospects);

                Adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProductsActivity.this, popupdll.class);
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
                        new LinearLayoutManager(ProductsActivity.this, LinearLayoutManager.VERTICAL, false));


            }
        });

    }

}



