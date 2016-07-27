package com.aplication.com.aplication1.Acivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.aplication.com.aplication1.R;

import org.w3c.dom.Text;

/**
 * Created by jairo880 on 22/07/16.
 */
public class popupdll extends Activity {

    TextView txtName;
    TextView txtSurname;
    TextView txtAddress;
    TextView txtTelephone;
    TextView txtObservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        txtName= (TextView) findViewById(R.id.prospectname);
        txtSurname= (TextView) findViewById(R.id.prospectsurname);
        txtAddress = (TextView) findViewById(R.id.prospectaddress);
        txtTelephone = (TextView) findViewById(R.id.prospecttelephone);
        txtObservation = (TextView) findViewById(R.id.prospectobservation);

        final Intent intent = getIntent();

        //POPUP Properties.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int witdh = dm.widthPixels;
        int heigth = dm.heightPixels;
        getWindow().setLayout((int)(witdh*.8),(int)(heigth*.5));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                txtName.setText(intent.getStringExtra("name").toString());
                txtSurname.setText(intent.getStringExtra("surname").toString());
                txtAddress.setText(intent.getStringExtra("telephone").toString());
                txtTelephone.setText(intent.getStringExtra("address").toString());
                txtObservation.setText(intent.getStringExtra("observation").toString());

            }
        });

    }
}
