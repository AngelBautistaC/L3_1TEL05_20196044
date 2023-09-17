package com.example.l3_1tel05_20196044;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.l3_1tel05_20196044.dto.Perfil;
import com.example.l3_1tel05_20196044.services.Randomservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tengoInternet()) {
                    // Si tenemos Internet, procedemos con la llamada API
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://randomuser.me/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    Randomservice.RandomUserService service = retrofit.create(Randomservice.RandomUserService.class);

                    Call<Perfil> call = service.getRandomUser();

                    call.enqueue(new Callback<Perfil>() {
                        @Override
                        public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                            if (response.isSuccessful()) {
                                Perfil perfil = response.body();
                                Perfil.Result result = perfil.getResults().get(0);
                                firstName = result.getName().getFirst();
                                lastName = result.getName().getLast();
                                email = result.getEmail();
                                password = result.getLogin().getPassword();

                                // Ahora que tenemos los datos, iniciamos RegisterActivity
                                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                intent.putExtra("firstName", firstName);
                                intent.putExtra("lastName", lastName);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Perfil> call, Throwable t) {
                            // Manejo de errores
                            Toast.makeText(LoginActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Si no hay conexión a Internet
                    Toast.makeText(LoginActivity.this, "No tienes conexión a Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.d("msg-test", "Internet: " + tieneInternet);
        return tieneInternet;
    }
}
