package com.example.projetdevmob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class RegisterActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private ImageButton btnRetour; // ⬅️ bouton retour ajouté
    private Button btnInscription;
    private EditText inputPrenom, inputNom, inputEmail, inputPassword, inputEtage, inputSuperficie, inputReponseSecrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Vérifie le nom de ton fichier XML

        // 🔽 Initialisation des vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour); // 🔁 bouton retour
        btnInscription = findViewById(R.id.btn_inscription);

        inputPrenom = findViewById(R.id.input_prenom);
        inputNom = findViewById(R.id.input_nom);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        inputEtage = findViewById(R.id.etage);
        inputSuperficie = findViewById(R.id.superficie);
        inputReponseSecrete = findViewById(R.id.reponse_secrete);

        // ☰ Bouton Menu
        btnMenu.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView);
            } else {
                drawerLayout.openDrawer(navigationView);
            }
        });

        // ⬅️ Bouton retour → retour vers page précédente
        btnRetour.setOnClickListener(v -> onBackPressed());

        // 📋 Navigation du menu (à compléter si besoin)
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            drawerLayout.closeDrawer(navigationView);
            return true;
        });

        // ➕ Inscription
        btnInscription.setOnClickListener(v -> enregistrerUtilisateur());
    }

    // 🔽 Méthode d'inscription
    private void enregistrerUtilisateur() {
        String prenom = inputPrenom.getText().toString().trim();
        String nom = inputNom.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String etage = inputEtage.getText().toString().trim();
        String superficie = inputSuperficie.getText().toString().trim();
        String reponseSecrete = inputReponseSecrete.getText().toString().trim();

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = "Bienvenue " + prenom + " " + nom + " !\nInscription réussie ✅";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // ➤ Ici tu peux intégrer la logique pour envoyer les données (BDD, API, etc.)
    }

    // ✅ Gestion du bouton retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed(); // revient à la page précédente
        }
    }
}
