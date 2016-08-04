package com.aplication.com.aplication1.Interfaces;

import android.view.Menu;
import android.view.MenuItem;

import com.aplication.com.aplication1.Models.Prospects;

import java.util.List;

/**
 * Created by jairo880 on 21/07/16.
 */
public interface IProspects {


     void ShowProspects(List<Prospects> prospects);
     boolean onCreateOptionsMenu(Menu menu);
     boolean onOptionsItemSelected(MenuItem item);
     void Cierre_Session();
     void FinishActivity();

}
