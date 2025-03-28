package com.example.projetdevmob;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    private ImageButton btnMenu;
    private ImageButton btnRetour; // ⬅️ bouton retour ajouté

    // Simulation conso par date
    private final HashMap<String, Integer> consommationMap = new HashMap<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consommation);

        // 🔗 Lier les vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btnMenu = findViewById(R.id.btn_menu);
        btnRetour = findViewById(R.id.btn_retour); // 🔁 bouton retour
        calendarView = findViewById(R.id.calendar_view);
        textInfo = findViewById(R.id.text_reservation_info);
        textDetails = findViewById(R.id.text_details);

        // 🧪 Données de simulation
        consommationMap.put("2025-04-21", 70);  // orange
        consommationMap.put("2025-04-22", 20);  // vert
        consommationMap.put("2025-04-23", 90);  // rouge

        // ☰ Bouton menu
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // ⬅️ Bouton retour
        btnRetour.setOnClickListener(v -> onBackPressed());

        // 📋 Navigation Drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 📅 Interaction calendrier
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format(Locale.FRANCE, "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            int consommation = consommationMap.getOrDefault(selectedDate, 0);
            String couleur = consommation < 30 ? getString(R.string.vert) : consommation < 70 ? getString(R.string.orange) : getString(R.string.rouge);
            String dateNow = sdf.format(new Date());
            int wattUtilise = 2000 * consommation / 100;

            textInfo.setText(getString(R.string.text_info, selectedDate));

            textDetails.setText(getString(R.string.text_details,
                    selectedDate,
                    consommation,
                    couleur,
                    dateNow,
                    wattUtilise));

            Toast.makeText(this, getString(R.string.toast_text, selectedDate, consommation), Toast.LENGTH_SHORT).show();
        });
    }

    // ✅ Gestion retour système
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); // Retour à l’activité précédente
        }
    }
}
