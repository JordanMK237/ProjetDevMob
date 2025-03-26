package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetdevmob.R;
import com.example.projetdevmob.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button btnLogin;
    private TextView linkRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Récupération des vues
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        linkRegister = findViewById(R.id.link_register);

        // Simulation d'un login sans API
        btnLogin.setOnClickListener(view -> {
            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (email.equals("test@example.com") && password.equals("123456")) {
                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, com.example.smarteco.MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        // Lien vers l'inscription
        linkRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
