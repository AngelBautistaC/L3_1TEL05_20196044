package com.example.l3_1tel05_20196044.services;

import com.example.l3_1tel05_20196044.dto.Perfil;

import retrofit2.Call;
import retrofit2.http.GET;

public class Randomservice {

    public interface RandomUserService {
        @GET("api/")
        Call<Perfil> getRandomUser();
    }
}
