// LoginActivity.java
package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetdevmob.api.ApiService;
import com.example.projetdevmob.api.LoginRequete;
import com.example.projetdevmob.api.LoginReponse;
import com.example.projetdevmob.api.RetrofitClient;
import com.example.projetdevmob.api.User;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button btnLogin;
    private TextView linkRegister, linkForgotPassword;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu, btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.input_email);
        loginPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        linkRegister = findViewById(R.id.txt_sinscrire);
        linkForgotPassword = findViewById(R.id.txt_mdp_oublie);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour);

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
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


        btnLogin.setOnClickListener(view -> loginUser());

        linkRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        linkForgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });
    }


    private void loginUser() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer l'objet de requête pour le login
        LoginRequete LoginRequete = new LoginRequete(email, password);

        // Appel à l'API via Retrofit
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<LoginReponse> call = apiService.loginUser(LoginRequete);
        call.enqueue(new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        // Récupérer l'objet utilisateur retourné par l'API
                        com.example.projetdevmob.api.User userinfo = response.body().getUser();
                        Toast.makeText(LoginActivity.this, "Connexion réussie : " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        saveUser(userinfo);
                        startActivity(new Intent(LoginActivity.this, BienvenueActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Erreur : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Erreur lors de la connexion (réponse invalide)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void saveUser(com.example.projetdevmob.api.User user) {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("user_id", user.getId());
        editor.putString("user_prenom", user.getPrenom());
        editor.putString("user_nom", user.getNom());
        editor.putString("user_email", user.getEmail());

        editor.putString("user_etage", user.getEtage());
        editor.putString("user_superficie", user.getSuperficie());
        editor.apply();
    }

}
