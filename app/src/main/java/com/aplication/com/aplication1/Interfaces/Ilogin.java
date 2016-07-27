package com.aplication.com.aplication1.Interfaces;

import android.view.View;

import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Models.Prospects;

import java.util.List;


/**
 * Created by jairo880 on 5/07/16.
 */
public interface Ilogin {


     void ValidarRegistrosPreferencias();

     void Registrarse(View view);

     void showClient(Cliente cliente);

     void showMessageError(String error);

}
