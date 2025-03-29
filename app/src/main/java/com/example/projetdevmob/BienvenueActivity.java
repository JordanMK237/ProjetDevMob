package com.example.projetdevmob;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BienvenueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue); // Assurez-vous que c'est le bon layout

        // Récupération des SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String prenom = prefs.getString("user_prenom", "Invité");

        TextView prenomTextView = findViewById(R.id.prenom);
        prenomTextView.setText(prenom);
    }
}
