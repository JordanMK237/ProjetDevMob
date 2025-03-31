package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ForgotPasswordActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private ImageButton btnRetour;

    private EditText inputPrenom, inputNom, inputEmail, inputReponseSecrete;
    private Button btnReinitialiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password); // nom à adapter à ton fichier XML

        // 📌 Initialisation des vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour);

        inputPrenom = findViewById(R.id.input_prenom);
        inputNom = findViewById(R.id.input_nom);
        inputEmail = findViewById(R.id.input_email);
        inputReponseSecrete = findViewById(R.id.reponse_secrete);
        btnReinitialiser = findViewById(R.id.btn_inscription);

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


        // 🔁 Réinitialisation simulée
        btnReinitialiser.setOnClickListener(v -> {
            String prenom = inputPrenom.getText().toString().trim();
            String nom = inputNom.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String reponse = inputReponseSecrete.getText().toString().trim();

            if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || reponse.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // 💬 Simule un message de succès
            String message = "Bonjour " + prenom + " " + nom + ", un lien de réinitialisation a été envoyé à : " + email;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            // ➤ Tu peux ici appeler une API ou base de données pour valider les infos
        });
    }

    // ✅ Gestion du bouton retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); // retour automatique
        }
    }
}
