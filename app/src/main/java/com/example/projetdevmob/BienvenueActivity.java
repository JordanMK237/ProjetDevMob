package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BienvenueActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);

        // 🔁 Initialisation
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_accueil) {
                startActivity(new Intent(this, BienvenueActivity.class));
            } else if (id == R.id.nav_creneau) {
                startActivity(new Intent(this, ConsommationActivity.class));
            } else if (id == R.id.nav_ajout) {
                startActivity(new Intent(this, AjoutAppareilActivity.class));
            } else if (id == R.id.nav_parametres) {
                startActivity(new Intent(this, ParametreActivity.class));
            } else if (id == R.id.nav_deconnexion) {
                startActivity(new Intent(this, DeconnexionActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 🔍 Récupération des SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String prenom = prefs.getString("user_prenom", null);

        TextView prenomTextView = findViewById(R.id.prenom);
        TextView infosTextView = findViewById(R.id.etage);
        TextView appareilsTextView = findViewById(R.id.appareils);

        if (prenom == null) {
            // 🔓 Pas connecté
            prenomTextView.setText("Bienvenue sur SmartEco ");
            infosTextView.setText("Veuillez vous connecter pour accéder à vos informations.");
            appareilsTextView.setText("Aucun appareil détecté.");
        } else {
            // ✅ Connecté
            String nom = prefs.getString("user_nom", "");
            String etage = prefs.getString("user_etage", "Non précisé");
            String superficie = prefs.getString("user_superficie", "Non précisée");
            int nbEquipements = prefs.getInt("user_nb_equipements", 0);

            prenomTextView.setText("Bienvenue " + prenom + " " + nom);
            infosTextView.setText("Étage : " + etage + "\nSuperficie : " + superficie + " m²");

            if (nbEquipements > 0) {
                appareilsTextView.setText("Appareils enregistrés : " + nbEquipements);
            } else {
                appareilsTextView.setText("Aucun appareil enregistré pour l’instant. Pensez à en ajouter !");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
