package com.example.paciencia;

import retrofit2.Call;

import retrofit2.http.GET;

public interface Regras {

    public static final String URL = "https://swapi.dev/api/planets/";

    @GET("planet")

    Call<StarPlanets> listPlanets();

}
