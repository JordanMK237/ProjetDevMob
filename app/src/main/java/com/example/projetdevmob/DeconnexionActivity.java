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
    private ImageButton btnRetour; // ⬅️ bouton retour ajouté
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
        btnRetour = findViewById(R.id.btn_retour); // 🆕 récupération du bouton retour
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // ☰ Menu latéral
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // ⬅️ Bouton retour → revenir à l’activité précédente
        btnRetour.setOnClickListener(v -> onBackPressed());

        // 📋 Menu NavigationView
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 🔁 Bouton "Annuler" → retour à ListeAppartementsActivity
        btnAnnuler.setOnClickListener(v -> {
            Intent intent = new Intent(DeconnexionActivity.this, ListeAppartementsActivity.class);
            startActivity(intent);
            finish();
        });

        // 🚪 Bouton "Déconnexion" → retour à MainActivity (écran d’accueil / connexion)
        btnDeconnecter.setOnClickListener(v -> {
            Intent intent = new Intent(DeconnexionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // reset de la pile
            startActivity(intent);
            finish();
        });
    }

    // ✅ Gestion du bouton retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); // revient à l’activité précédente (automatique)
        }
    }
}
