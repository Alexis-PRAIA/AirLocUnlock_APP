package com.example.airlocunlockapp;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class forgot_password extends AppCompatActivity {
    private WebView webView;
    private TextView textViewTitle;
    private ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        webView = findViewById(R.id.webView);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewBack = findViewById(R.id.imageViewBack);

        // Titre de la WebView
        textViewTitle.setText("Mot de passe oublié ?");

        // Charger l'URL dans la WebView
        webView.loadUrl("http://51.210.151.13/btssnir/projets2024/airlocunlock2024/airlocunlock2024/Final/boostrap/mdp_oublie/reset.php");

        // Configurer le WebViewClient pour intercepter les clics sur les liens
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Charger l'URL cliquée dans la WebView
                view.loadUrl(request.getUrl().toString());
                return true; // Indiquer que l'URL est gérée
            }
        });

        // Gestion du clic sur le bouton flèche
        imageViewBack.setOnClickListener(v -> finish()); // Fermer l'activité actuelle
    }
}