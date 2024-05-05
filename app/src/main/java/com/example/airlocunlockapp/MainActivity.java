package com.example.airlocunlockapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Initialisation du Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://9ca02523-be4f-46d6-a10d-2681a925f726.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Définition d'un OnClickListener pour le bouton de connexion
        buttonLogin.setOnClickListener(v -> loginUser());

        // Définition d'un OnClickListener pour le texte "Mot de passe oublié ?"
        TextView textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(v -> redirectToForgotPassword());
    }

    // Méthode pour gérer la connexion de l'utilisateur
    private void loginUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        Call<ConnexionResponse> call = apiService.login(email, password);
        call.enqueue(new Callback<ConnexionResponse>() {
            @Override
            public void onResponse(Call<ConnexionResponse> call, Response<ConnexionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ConnexionResponse connexionResponse = response.body();
                    if (connexionResponse.isSuccess()) {
                        Toast.makeText(MainActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                        // Redirection vers ReservationActivity
                        Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
                        startActivity(intent);
                        finish(); // Optionnel : pour fermer l'activité actuelle après la redirection
                    } else {
                        Toast.makeText(MainActivity.this, "Échec de la connexion", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ConnexionResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur de réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Méthode pour rediriger vers ForgotPasswordActivity
    private void redirectToForgotPassword() {
        Intent intent = new Intent(MainActivity.this, forgot_password.class);
        startActivity(intent);
    }
}