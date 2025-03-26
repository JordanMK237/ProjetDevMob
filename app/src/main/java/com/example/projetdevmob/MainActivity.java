package com.example.projetdevmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetdevmob.LoginActivity;
import com.example.projetdevmob.R;
import com.example.projetdevmob.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageButton backButton = findViewById(R.id.backButton);
        Button buttonConnexion = findViewById(R.id.buttonConnexion);
        Button buttonInscription = findViewById(R.id.buttonInscription);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action de retour
                finish();
            }
        });

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'écran de connexion
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'écran d'inscription
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}