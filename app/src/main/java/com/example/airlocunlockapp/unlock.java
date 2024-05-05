package com.example.airlocunlockapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class unlock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);

        // Récupérer les données de la réservation sélectionnée depuis l'intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String localisation = intent.getStringExtra("localisation");
            String date = intent.getStringExtra("date");
            String imageUrl = intent.getStringExtra("imageUrl"); // Récupérer l'URL de l'image

            // Mettre à jour les vues avec les données de la réservation
            TextView textViewName = findViewById(R.id.textViewName);
            TextView textViewLocation = findViewById(R.id.textViewLocation);
            TextView textViewDate = findViewById(R.id.textViewDate);
            ImageView imageViewLocation = findViewById(R.id.imageViewLocation);

            textViewName.setText(name);
            textViewLocation.setText(localisation);
            textViewDate.setText(date);

            // Charger l'image depuis l'URL dans l'ImageView
            // Utilisez une bibliothèque comme Picasso ou Glide pour le chargement asynchrone
            // Voici un exemple d'utilisation de Picasso :
            Picasso.get().load(imageUrl).into(imageViewLocation);
        }

        // Gérer le clic sur le bouton flèche
        ImageView imageViewBack = findViewById(R.id.imageViewBackArrow);
        imageViewBack.setOnClickListener(view -> {
            // Rediriger vers ReservationActivity
            startActivity(new Intent(unlock.this, ReservationActivity.class));
            finish(); // Facultatif : fermer cette activité après la redirection
        });
    }
}
