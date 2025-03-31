package com.example.projetdevmob;

import static com.example.projetdevmob.R.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class ParametreActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton buttonMenu;
    private ImageButton buttonRetour; // 🔁 Bouton retour ajouté
    private Button btnInfos, btnMdp, btnNotifs, btnAPropos, btnPrefernce,btnEditProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        buttonMenu = findViewById(R.id.btn_menu);
        buttonRetour = findViewById(R.id.btn_retour); // ⬅️ récupération du bouton retour
        btnEditProfil = findViewById(R.id.btn_edit_profil);

        btnInfos = findViewById(R.id.btn_se_connecter);
        btnMdp = findViewById(R.id.btn_inscription);
        btnNotifs = findViewById(R.id.btn_notification);
        btnAPropos = findViewById(R.id.btn_apropos);
        btnPrefernce = findViewById(R.id.btn_preference);

        // ☰ Menu
        buttonMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
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

        // ⬅️ Bouton retour → retour à l’activité précédente (comportement système standard)
        buttonRetour.setOnClickListener(v -> onBackPressed());

        // 📦 Navigation vers les fragments
        btnInfos.setOnClickListener(v -> openFragment(new ListeHabitatsFragment()));
        btnMdp.setOnClickListener(v -> openFragment(new MonHabitatFragment()));
        btnNotifs.setOnClickListener(v -> openFragment(new NotificationsFragment()));
        btnPrefernce.setOnClickListener(v -> openFragment(new PreferencesFragment())); // 🔧 Corrigé ici
        btnEditProfil.setOnClickListener(v -> openFragment(new EditProfilFragment()));

        btnAPropos.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("À propos")
                .setMessage("Application SmartEco v1.0\nConçue pour une gestion intelligente de l'énergie résidentielle.")
                .setPositiveButton("OK", null)
                .show());
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // permet de revenir en arrière avec le bouton retour
        transaction.commit();
    }

    public Button getBtnPrefernce() {
        return btnPrefernce;
    }

    public void setBtnPrefernce(Button btnPrefernce) {
        this.btnPrefernce = btnPrefernce;
    }

    // ✅ Gestion du bouton retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(); // retour dans les fragments
        } else {
            super.onBackPressed(); // retour à l'activité précédente
        }
    }
}
