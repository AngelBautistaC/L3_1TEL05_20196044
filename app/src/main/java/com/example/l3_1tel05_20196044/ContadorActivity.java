package com.example.l3_1tel05_20196044;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ContadorActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private long delay = 10000; // 10 segundos
    private TextView txtContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        txtContador = findViewById(R.id.txtContador);
        Button btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delay /= 2;
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ContadorManager.getInstance().incrementarContador();
                        int nuevoValor = ContadorManager.getInstance().getContador();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtContador.setText(String.valueOf(nuevoValor));
                            }
                        });
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }
        });
    }
}

