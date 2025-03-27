package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button btnLogin;
    private TextView linkRegister;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu; // Ton bouton menu en haut à gauche

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Ton layout avec DrawerLayout

        // 🟩 Login classique
        loginEmail = findViewById(R.id.input_email);
        loginPassword = findViewById(R.id.input_email);
        btnLogin = findViewById(R.id.btn_login);
        linkRegister = findViewById(R.id.txt_sinscrire);

        // 🟦 Drawer menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu); // assure-toi qu'il est bien défini dans le XML

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_accueil) {
                startActivity(new Intent(LoginActivity.this, AccueilActivity.class));
            } else if (id == R.id.nav_creneau) {
                startActivity(new Intent(LoginActivity.this, ConsommationActivity.class));
            } else if (id == R.id.nav_ajout) {
                startActivity(new Intent(LoginActivity.this, AjoutAppareilActivity.class));
            } else if (id == R.id.nav_parametres) {
                startActivity(new Intent(LoginActivity.this, ParametreActivity.class));
            } else if (id == R.id.nav_deconnexion) {
                startActivity(new Intent(LoginActivity.this, DeconnexionActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // ✅ Connexion simulée
        btnLogin.setOnClickListener(view -> {
            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (email.equals("test@example.com") && password.equals("123456")) {
                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        // ✅ Lien vers l'inscription
        linkRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    // ✅ Gestion retour si menu ouvert
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
