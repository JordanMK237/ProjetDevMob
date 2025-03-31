package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetdevmob.api.ApiService;
import com.example.projetdevmob.api.RegisterReponse;
import com.example.projetdevmob.api.RegisterRequete;
import com.example.projetdevmob.api.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private ImageButton btnRetour; // ️ bouton retour ajouté
    private Button btnInscription;
    private EditText inputPrenom, inputNom, inputEmail, inputPassword, inputEtage, inputSuperficie, inputReponseSecrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Vérifie le nom de ton fichier XML

        //  Initialisation des vues
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

        // ⬅ Bouton retour → retour vers page précédente
        btnRetour.setOnClickListener(v -> onBackPressed());

        //  Navigation du menu
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

        // ➕ Inscription
        btnInscription.setOnClickListener(v -> enregistrerUtilisateur());
    }

    //  Méthode d'inscription
    private void enregistrerUtilisateur() {
        //  Récupération des valeurs depuis les champs de saisie
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

        //  Envoi à l'API via Retrofit
        RegisterRequete requete = new RegisterRequete(prenom, nom, email, password, etage, superficie, reponseSecrete);
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterReponse> call = apiService.registerUser(requete);

        call.enqueue(new Callback<RegisterReponse>() {
            @Override
            public void onResponse(Call<RegisterReponse> call, Response<RegisterReponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        Toast.makeText(RegisterActivity.this, "✅ Inscription réussie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "❌ " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Erreur côté serveur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterReponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
