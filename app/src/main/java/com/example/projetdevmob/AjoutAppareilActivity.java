package com.example.projetdevmob;

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

import com.google.android.material.navigation.NavigationView;

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
            int id = item.getItemId();

            if (id == R.id.nav_accueil) {
                Toast.makeText(this, "Accueil", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_creneau) {
                Toast.makeText(this, "Créneau", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_ajout) {
                Toast.makeText(this, "Ajout appareil", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_parametres) {
                Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_deconnexion) {
                Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
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

        // 🎯 Réaction quand on sélectionne un appareil
        spinnerAppareils.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String appareilChoisi = appareils[position];
                Toast.makeText(AjoutAppareilActivity.this, "Appareil : " + appareilChoisi, Toast.LENGTH_SHORT).show();
                // Tu peux ici faire d'autres actions avec l'appareil sélectionné
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Rien de spécial
            }
        });
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
