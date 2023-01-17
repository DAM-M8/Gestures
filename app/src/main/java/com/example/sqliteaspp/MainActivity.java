package com.example.sqliteaspp;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Clase principal del restaurante. Permite ver la carta del restaurante, pedir
 * comandas, calcular la cuenta de una mesa, cobrar mesas y gestionar el alta y
 * baja de reservas de clientes.
 *
 * @author Laura Lopez
 */
public class MainActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {

    private Button boton;
    public GestorBDRestaurante gbdRest = new GestorBDRestaurante(this);
    private GestureLibrary libreria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gbdRest.cargarDatos();

        //Carreguem la llibreria
        libreria = GestureLibraries.fromRawResource(this,R.raw.gestures);

        if(!libreria.load()){
            finish();
        }

        GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestures);
        gestureView.addOnGesturePerformedListener(this);



        boton = (Button) findViewById(R.id.Button01);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarPlatos(null);
            }
        });

        boton = (Button) findViewById(R.id.Button02);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarHacerPedido(null);
            }
        });

        boton = (Button) findViewById(R.id.Button03);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarCalcularPedido(null);
            }
        });

        boton = (Button) findViewById(R.id.Button04);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarReserva(null);
            }
        });

        boton = (Button) findViewById(R.id.Button05);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarEliminarReserva(null);
            }
        });

        boton = (Button) findViewById(R.id.Button06);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarsalir();
            }
        });




    }

    //Comportament a aplicar segons el 'gesture'
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = libreria.recognize(gesture);
        if (predictions.size() > 0) {
            String comando = predictions.get(0).name;
            switch(comando)
            {
                case "ver_carta": //opció del menú "Ver carta"
                    lanzarPlatos(null);
                    break;
                case "calcular_pedido": //'Gesture' associat a calcular una comanda
                    lanzarCalcularPedido(null);
                    break;
                case "salir": //'Gesture' associat a la sortida de l'aplicació
                    lanzarsalir();
                    break;
            }
        }
    }


    public void lanzarPlatos(View view) {
        Intent i = new Intent(this, ListarPlatos.class);
        startActivity(i);
    }

    public void lanzarHacerPedido(View view) {

        Intent i = new Intent(this, PedirPlatos.class);
        startActivity(i);
    }

    public void lanzarCalcularPedido(View view) {
        Intent i = new Intent(this, CalcularPedido.class);
        startActivity(i);
    }

    public void lanzarReserva(View view) {
        Intent i = new Intent(this, Reservar.class);
        startActivity(i);
    }

    public void lanzarEliminarReserva(View view) {
        Intent i = new Intent(this, EliminarReserva.class);
        startActivity(i);
    }

    public void lanzarsalir() {
        finish();
    }


}

