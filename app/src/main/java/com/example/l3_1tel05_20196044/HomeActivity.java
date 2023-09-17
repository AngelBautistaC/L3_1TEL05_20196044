// HomeActivity.java
package com.example.l3_1tel05_20196044;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnIrACronometro = findViewById(R.id.btnIrACronometro);

        btnIrACronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CronometroActivity.class);
                startActivity(intent);
            }
        });
    }
}
