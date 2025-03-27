package com.example.projetdevmob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class ParametreActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton buttonMenu;
    private Button btnInfos, btnMdp, btnNotifs, btnAPropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        buttonMenu = findViewById(R.id.btn_menu);

        btnInfos = findViewById(R.id.btn_se_connecter);
        btnMdp = findViewById(R.id.btn_inscription);
        btnNotifs = findViewById(R.id.btn_notification);
        btnAPropos = findViewById(R.id.btn_apropos);

        // Menu
        buttonMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Fragments
        btnInfos.setOnClickListener(v -> openFragment(new ListeHabitatsFragment()));
        btnMdp.setOnClickListener(v -> openFragment(new MonHabitatFragment()));
        btnNotifs.setOnClickListener(v -> openFragment(new NotificationsFragment()));

        btnAPropos.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("À propos")
                .setMessage("Application SmartEco v1.0\nConçue pour une gestion intelligente de l'énergie résidentielle.")
                .setPositiveButton("OK", null)
                .show());
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
