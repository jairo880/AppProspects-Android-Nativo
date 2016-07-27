package com.aplication.com.aplication1.Interfaces;

import android.view.Menu;

import com.aplication.com.aplication1.Models.Prospects;

import java.util.List;

/**
 * Created by jairo880 on 21/07/16.
 */
public interface IProspects {

     boolean onCreateOptionsBar(Menu menu);

     void ShowProspects(List<Prospects> prospects);

}
