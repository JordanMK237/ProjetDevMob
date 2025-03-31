package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetdevmob.api.AjoutAppareilRequete;
import com.example.projetdevmob.api.ApiResponse;
import com.example.projetdevmob.api.ApiService;
import com.example.projetdevmob.api.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjoutAppareilActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private ImageButton btnRetour; // ⬅️ bouton retour ajouté
    private Spinner spinnerAppareils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_appareil);

        // 🔗 Récupération des vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour); // 🔁 récupération du bouton retour
        spinnerAppareils = findViewById(R.id.spinner_appareils);

        // ☰ Bouton menu
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // ⬅️ Bouton retour
        btnRetour.setOnClickListener(v -> onBackPressed());

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



        // 📦 Liste des appareils
        String[] appareils = {"Aspirateur", "Fer à repasser", "Climatiseur", "Machine à laver"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                appareils
        );

        spinnerAppareils.setAdapter(adapter);

    }

    // ✅ Gestion du bouton retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); // retour à l'activité précédente
        }
    }
}
