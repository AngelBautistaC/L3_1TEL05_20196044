// RegisterActivity.java
package com.example.l3_1tel05_20196044;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        final EditText etFirstName = findViewById(R.id.etFirstName);
        final EditText etLastName = findViewById(R.id.etLastName);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final CheckBox cbTerms = findViewById(R.id.cbTerms);
        final Button btnRegisterFinal = findViewById(R.id.btnRegisterFinal);

        // Aquí debes colocar el código que llena estos campos automáticamente
        // utilizando los datos obtenidos del método GET previamente.
        etFirstName.setText(firstName);
        etLastName.setText(lastName);
        etEmail.setText(email);
        etPassword.setText(password);

        cbTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegisterFinal.setEnabled(cbTerms.isChecked());
            }
        });

        btnRegisterFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
