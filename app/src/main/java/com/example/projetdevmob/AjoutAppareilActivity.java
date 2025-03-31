package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetdevmob.api.AjoutAppareilRequete;
import com.example.projetdevmob.api.ApiResponse;
import com.example.projetdevmob.api.ApiService;
import com.example.projetdevmob.api.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjoutAppareilActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private ImageButton btnRetour;
    private Spinner spinnerAppareils;
    private Button btnAjouter;  // Bouton pour déclencher l'ajout
    private TextView txtViewWatt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        boolean isLoggedIn = prefs.contains("user_email");

        if (!isLoggedIn) {
            Toast.makeText(this, "Veuillez vous connecter pour ajouter un appareil", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_ajout_appareil);

        // Récupération des vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour);
        spinnerAppareils = findViewById(R.id.spinner_appareils);
        btnAjouter = findViewById(R.id.btn_se_connecter); // Assurez-vous que cet id est défini dans votre layout
        txtViewWatt = findViewById(R.id.edit_wattage);


        // Bouton menu
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        // Bouton retour
        btnRetour.setOnClickListener(v -> onBackPressed());

        // Menu navigation
        navigationView.setNavigationItemSelectedListener(item -> {
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

        // Liste des appareils
        String[] appareils = {"Aspirateur", "Fer à repasser", "Climatiseur", "Machine à laver"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                appareils
        );
        spinnerAppareils.setAdapter(adapter);

        // Ajout du clic sur le bouton "Ajouter"
        btnAjouter.setOnClickListener(v -> {
            // Récupérer l'ID utilisateur stocké dans les SharedPreferences
            int userId = prefs.getInt("user_id", -1);
            if (userId == -1) {
                Toast.makeText(AjoutAppareilActivity.this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show();
                return;
            }
            // Récupérer l'appareil sélectionné dans le spinner
            String item = spinnerAppareils.getSelectedItem().toString();
            String appareil;
            switch (item){
                case "Aspirateur":
                    appareil = "aspirateur";
                    break;
                case "Fer à repasser":
                    appareil = "fer_repasser";
                    break;
                case"Climatiseur":
                    appareil = "climatiseur";
                    break;
                case"Machine à laver":
                    appareil = "machine_a_laver";
                    break;
                default:
                    try {
                        throw new Exception("erreur item selectionne non valide");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }
            int appareilConso = Integer.parseInt(txtViewWatt.getText().toString().trim());
            // Créer l'objet de requête
            AjoutAppareilRequete requete = new AjoutAppareilRequete(userId, appareil, appareilConso);

            // Appeler l'API via Retrofit
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<ApiResponse> call = apiService.ajouterAppareil(requete);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().isSuccess()) {
                            Toast.makeText(AjoutAppareilActivity.this, "Appareil ajouté avec succès", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AjoutAppareilActivity.this, "Erreur : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AjoutAppareilActivity.this, "Réponse invalide", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(AjoutAppareilActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
}
