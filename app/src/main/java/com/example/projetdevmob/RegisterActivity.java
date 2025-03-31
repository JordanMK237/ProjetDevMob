package com.example.projetdevmob;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetdevmob.api.NewUser;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.projetdevmob.api.ApiService;
import com.example.projetdevmob.api.RegisterReponse;
import com.example.projetdevmob.api.RetrofitClient;

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

        // Valeur par défaut pour test
        if(true){
            inputPrenom.setText("Victor");
            inputNom.setText("Victor");
            inputEmail.setText("Victor");
            inputPassword.setText("Victor");
            inputEtage.setText("69");
            inputSuperficie.setText("69");
            inputReponseSecrete.setText("Victor");
        }

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

        // Création d'un objet RegisterRequest (à créer si besoin, ou vous pouvez réutiliser la classe User)
        NewUser newUser = new NewUser(prenom, nom, email, password, etage, superficie, reponseSecrete);

        // Appel à l'API via Retrofit
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterReponse> call = apiService.registerUser(newUser);
        call.enqueue(new Callback<RegisterReponse>() {
            @Override
            public void onResponse(Call<RegisterReponse> call, Response<RegisterReponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        Toast.makeText(RegisterActivity.this, "Inscription réussie", Toast.LENGTH_LONG).show();
                        // Rediriger vers la page de login ou MainActivity
                        // startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Erreur : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Erreur lors de l'inscription (réponse invalide)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterReponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
