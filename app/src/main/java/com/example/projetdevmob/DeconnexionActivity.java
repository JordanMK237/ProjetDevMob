package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DeconnexionActivity extends AppCompatActivity {

    private Button btnAnnuler, btnDeconnecter;
    private ImageButton btnMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deconnexion);

        // Initialisation des vues
        btnAnnuler = findViewById(R.id.btn_annuler);
        btnDeconnecter = findViewById(R.id.btn_deconnecter);
        btnMenu = findViewById(R.id.btn_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Menu latéral
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // NavigationView Listener (si tu veux ajouter plus tard les redirections)
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Bouton "Annuler" → Retour à AccueilActivity
        btnAnnuler.setOnClickListener(v -> {
            Intent intent = new Intent(DeconnexionActivity.this, AccueilActivity.class);
            startActivity(intent);
            finish();
        });

        // Bouton "Déconnexion" → Retour à MainActivity (Splash/Login)
        btnDeconnecter.setOnClickListener(v -> {
            Intent intent = new Intent(DeconnexionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // évite retour avec bouton back
            startActivity(intent);
            finish();
        });
    }
}
