package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class BienvenueInvitesActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue_invites); // à créer dans les layouts

        TextView texteAccueil = findViewById(R.id.texte_bienvenue_invite);
        Button btnConnexion = findViewById(R.id.btn_aller_connexion);

        texteAccueil.setText("Bienvenue sur SmartEco !\nVeuillez vous connecter pour accéder à vos informations personnelles.");

        btnConnexion.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
