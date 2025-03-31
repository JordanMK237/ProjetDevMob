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

        // 📋 Menu navigation
        navigationView.setNavigationItemSelectedListener(item -> {
            SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
            boolean isLoggedIn = prefs.contains("user_email"); // Vérifie si un utilisateur est connecté

            int id = item.getItemId();

            if (id == R.id.nav_accueil) {
                if (isLoggedIn) {
                    startActivity(new Intent(this, BienvenueActivity.class));
                } else {
                    startActivity(new Intent(this, BienvenueInvitesActivity.class));
                }
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

        // 🧠 Affichage des données de session
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String prenom = prefs.getString("user_prenom", "Invité");
        String nom = prefs.getString("user_nom", "");
        String etage = prefs.getString("user_etage", "Non précisé");
        String superficie = prefs.getString("user_superficie", "Non précisée");
        int nbEquipements = prefs.getInt("user_nb_equipements", 0); // récupéré via API si dispo

        TextView prenomTextView = findViewById(R.id.prenom);
        TextView infosTextView = findViewById(R.id.etage);
        TextView appareilsTextView = findViewById(R.id.appareils);

        prenomTextView.setText("Bienvenue " + prenom + " " + nom );
        infosTextView.setText("Étage : " + etage + "\nSuperficie : " + superficie + " m²");


        // Récupération des informations d'équipements
        int aspirateur = prefs.getInt("aspi", 0);
        int fer = prefs.getInt("fer", 0);
        int clim = prefs.getInt("clim", 0);
        int machine = prefs.getInt("machine", 0);

        int aspirateurConso = prefs.getInt("aspirateur_conso", 0);
        int ferConso = prefs.getInt("fer_repasser_conso", 0);
        int climConso = prefs.getInt("climatiseur_conso", 0);
        int machineConso = prefs.getInt("machine_a_laver_conso", 0);
        int totalConsommation = prefs.getInt("userConsoTotal", 0);

        // Construction du texte à afficher
        StringBuilder sb = new StringBuilder();
        if (aspirateur == 1) {
            sb.append("Aspirateur : ").append(aspirateurConso).append(" W\n");
        }
        if (fer == 1) {
            sb.append("Fer à repasser : ").append(ferConso).append(" W\n");
        }
        if (clim == 1) {
            sb.append("Climatiseur : ").append(climConso).append(" W\n");
        }
        if (machine == 1) {
            sb.append("Machine à laver : ").append(machineConso).append(" W\n");
        }

        if (sb.length() > 0) {
            sb.append("Total consommation : ").append(totalConsommation).append(" W");
        } else {
            sb.append("Aucun appareil enregistré pour l’instant. Pensez à en ajouter !");
        }

        // Mise à jour de l'affichage
        appareilsTextView.setText(sb.toString());
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
