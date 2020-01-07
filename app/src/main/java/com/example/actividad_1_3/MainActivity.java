package com.example.actividad_1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtAgregar;
    TextView txt2;

    private final String nomarch = "datosMafher.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAgregar = findViewById(R.id.txtAgregar);
        txt2 = findViewById(R.id.txt2);

        ManejoArchivoTXT objmanarch = new ManejoArchivoTXT();
        if(objmanarch.verifica(this,nomarch)){
            Toast.makeText(this, "Existe el archivo... ", Toast.LENGTH_SHORT).show();
            if(objmanarch.leer(this,nomarch)){
                TextoCompleto = objmanarch.getContenido();
                String cadena = "";
                for(String micadena : TextoCompleto ){
                    cadena+="\n" +micadena;
                }
                txt2.setText(cadena);
            }
        }else{
            Toast.makeText(this, "No existe el archivo", Toast.LENGTH_SHORT).show();
        }

    }

    public void MGrabar (View v){
        ManejoArchivoTXT controlador = new ManejoArchivoTXT();
        String Texto="";
        try {
            Texto=txtAgregar.getText().toString();
            controlador.agregar(Texto,TextoCompleto);
            TextoCompleto = controlador.getContenido();
            if(controlador.grabar(TextoCompleto,this,nomarch)){
                Toast.makeText(this, "Se grabo correctamente", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "No se pudo grabar correctamente", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
