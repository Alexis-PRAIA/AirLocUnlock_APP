package com.example.airlocunlockapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("connexion")
    Call<ConnexionResponse> login(
            @Query("email") String email,
            @Query("mdp") String password
    );

    @GET("meslocations")
    Call<Map<String, Reservation>> getReservations(@Query("id_user") int userId);

}



