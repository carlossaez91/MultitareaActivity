package com.example.multitareaactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout pantallaDividida;
    private View vistaSuperior;
    private View vistaInferior;
    private boolean vistaSuperiorActiva = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén la vista personalizada y las vistas superior e inferior
        pantallaDividida = findViewById(R.id.pantalla_dividida);
        vistaSuperior = findViewById(R.id.vista_superior);
        vistaInferior = findViewById(R.id.vista_inferior);

        // Inicia la otra aplicación en modo de pantalla completa y agrega la vista a la sección inferior
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.otraaplicacion.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getWindowManager().addView(vistaInferior, new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSPARENT
        ));
        startActivity(intent);
    }

    // Agrega la funcionalidad para cambiar la posición de las aplicaciones en la vista personalizada
    public void cambiarPosicion(View view) {
        if (vistaSuperiorActiva) {
            pantallaDividida.removeView(vistaSuperior);
            pantallaDividida.addView(vistaSuperior);
            vistaSuperiorActiva = false;
        } else {
            pantallaDividida.removeView(vistaInferior);
            pantallaDividida.addView(vistaInferior);
            vistaSuperiorActiva = true;
        }
    }

    // Agrega la funcionalidad para salir del modo de pantalla dividida
    public void salirPantallaDividida(View view) {
        getWindowManager().removeView(vistaInferior);
        finish();
    }
}


