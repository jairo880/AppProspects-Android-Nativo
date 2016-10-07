package com.aplication.com.aplication1.Interfaces;

import android.content.Context;
import android.view.View;

import com.aplication.com.aplication1.Models.Cliente;
import com.aplication.com.aplication1.Models.Prospects;

import java.util.List;


/**
 * Created by jairo880 on 5/07/16.
 */
public interface Ilogin {


     void ValidarRegistrosPreferencias();


     void showClient(Cliente cliente, String recordarUsuario);

     void showMessageError(String error);

     boolean internetValidate(Context context);

}
