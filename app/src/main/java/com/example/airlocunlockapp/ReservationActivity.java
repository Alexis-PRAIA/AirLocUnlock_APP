package com.example.airlocunlockapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationActivity extends AppCompatActivity {
    private ListView listView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        listView = findViewById(R.id.listViewReservations);
        ImageView imageViewBack = findViewById(R.id.imageViewBack);

        // Gérer le clic sur le bouton flèche
        imageViewBack.setOnClickListener(view -> {
            // Rediriger vers MainActivity
            startActivity(new Intent(ReservationActivity.this, MainActivity.class));
            finish(); // Facultatif : fermer cette activité après la redirection
        });

        // Appel de la méthode pour récupérer les réservations depuis l'API
        fetchReservations();

        // Ajouter un écouteur d'événements à la ListView pour détecter les clics sur les réservations
        // Dans ReservationActivity.java, méthode pour ouvrir unlock avec les informations de réservation
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Reservation reservation = (Reservation) adapterView.getItemAtPosition(position);
            Intent intent = new Intent(ReservationActivity.this, unlock.class);
            intent.putExtra("name", reservation.getName());
            intent.putExtra("localisation", reservation.getLocalisation());
            intent.putExtra("date", reservation.getDate());
            // Ajoutez d'autres informations si nécessaire
            startActivity(intent);
        });

    }

    private void fetchReservations() {
        // Utilisation de Retrofit pour effectuer la requête HTTP
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://9ca02523-be4f-46d6-a10d-2681a925f726.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Utilisation de l'interface ApiService pour appeler la méthode de récupération des réservations
        Call<Map<String, Reservation>> call = apiService.getReservations(123);

        // Exécution de la requête asynchrone
        call.enqueue(new Callback<Map<String, Reservation>>() {
            @Override
            public void onResponse(Call<Map<String, Reservation>> call, Response<Map<String, Reservation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Récupération des réservations depuis la réponse
                    Map<String, Reservation> reservationsMap = response.body();
                    List<Reservation> reservations = new ArrayList<>(reservationsMap.values());

                    // Mise à jour de la ListView avec les réservations récupérées
                    adapter = new ReservationAdapter(ReservationActivity.this, R.layout.item_reservation, reservations);
                    listView.setAdapter(adapter);
                } else {
                    // Gestion des erreurs de réponse
                    Toast.makeText(ReservationActivity.this, "Erreur lors de la récupération des réservations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Reservation>> call, Throwable t) {
                // Gestion des erreurs de réseau
                Toast.makeText(ReservationActivity.this, "Erreur de réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}