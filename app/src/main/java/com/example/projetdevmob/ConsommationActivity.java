package com.example.projetdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ConsommationActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CalendarView calendarView;
    private TextView textInfo, textDetails;
    private Button btnReserver;

    private ImageButton btnMenu;
    private ImageButton btnRetour;

    private final HashMap<String, Integer> consommationMap = new HashMap<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
    private String dateSelectionnee = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consommation);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour);
        calendarView = findViewById(R.id.calendar_view);
        textInfo = findViewById(R.id.text_reservation_info);
        textDetails = findViewById(R.id.text_details);
        btnReserver = findViewById(R.id.btn_reserver);

        consommationMap.put("2025-04-21", 70);  // orange
        consommationMap.put("2025-04-22", 20);  // vert
        consommationMap.put("2025-04-23", 90);  // rouge

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        btnRetour.setOnClickListener(v -> onBackPressed());

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_accueil) {
                startActivity(new Intent(this, ListeAppartementsActivity.class));
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


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            dateSelectionnee = String.format(Locale.FRANCE, "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            int consommation = consommationMap.getOrDefault(dateSelectionnee, 0);
            String couleur = consommation < 30 ? getString(R.string.vert)
                    : consommation < 70 ? getString(R.string.orange)
                    : getString(R.string.rouge);
            String dateNow = sdf.format(new Date());
            int wattUtilise = 2000 * consommation / 100;

            Log.d("DEBUG_CONSO", "Date sélectionnée : " + dateSelectionnee + " → conso : " + consommation);

            textInfo.setText(getString(R.string.text_info, dateSelectionnee));
            textDetails.setText(getString(R.string.text_details,
                    dateSelectionnee,
                    consommation,
                    couleur,
                    dateNow,
                    wattUtilise));

            if (consommation < 70) {
                btnReserver.setVisibility(Button.VISIBLE);
            } else {
                btnReserver.setVisibility(Button.GONE);
            }

            Toast.makeText(this, getString(R.string.toast_text, dateSelectionnee, consommation), Toast.LENGTH_SHORT).show();
        });

        btnReserver.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("date_reservation", dateSelectionnee);
            editor.apply();

            Toast.makeText(this, "✅ Créneau réservé pour le " + dateSelectionnee, Toast.LENGTH_LONG).show();
            btnReserver.setVisibility(Button.GONE);
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
