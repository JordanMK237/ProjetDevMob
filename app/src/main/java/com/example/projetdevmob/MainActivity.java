package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button buttonConnexion, buttonInscription;
    private ImageButton buttonMenu;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Assure-toi d'utiliser le bon layout

        // 🟩 Récupération des éléments du layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        buttonMenu = findViewById(R.id.btn_menu);
        buttonConnexion = findViewById(R.id.btn_se_connecter);
        buttonInscription = findViewById(R.id.btn_inscription);

        // 🟨 Clique sur le bouton menu → ouvrir le drawer
        buttonMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // 🟧 Navigation via le menu latéral
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_accueil) {
                    startActivity(new Intent(MainActivity.this, ListeAppartementsActivity.class));
                } else if (id == R.id.nav_creneau) {
                    startActivity(new Intent(MainActivity.this, ConsommationActivity.class));
                } else if (id == R.id.nav_ajout) {
                    startActivity(new Intent(MainActivity.this, AjoutAppareilActivity.class));
                } else if (id == R.id.nav_parametres) {
                    startActivity(new Intent(MainActivity.this, ParametreActivity.class));
                } else if (id == R.id.nav_deconnexion) {
                    startActivity(new Intent(MainActivity.this, DeconnexionActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // 🟦 Boutons de la page d'accueil
        buttonConnexion.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        buttonInscription.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        // 🕒 Message d'accueil dynamique
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String message;

        if (hour >= 5 && hour < 12) {
            message = "Bonjour et bienvenue sur SmartEco ☀️\nGérez votre énergie dès le matin !";
        } else if (hour >= 12 && hour < 18) {
            message = "Bon après-midi ! Pensez à surveiller votre consommation ⚡";
        } else {
            message = "Bonsoir 🌙 Restez connecté à votre habitat avec SmartEco.";
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Fermer le drawer si ouvert quand on appuie sur "retour"
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
