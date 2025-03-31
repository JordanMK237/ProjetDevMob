package com.example.projetdevmob;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetdevmob.R;

public class EditProfilFragment extends Fragment {

    private EditText inputPrenom, inputNom, inputEmail;
    private Button btnSauvegarder;

    public EditProfilFragment() {
        // Constructeur vide
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profil, container, false);

        inputPrenom = view.findViewById(R.id.edit_prenom);
        inputNom = view.findViewById(R.id.edit_nom);
        inputEmail = view.findViewById(R.id.edit_email);
        btnSauvegarder = view.findViewById(R.id.btn_save);

        SharedPreferences prefs = requireActivity().getSharedPreferences("user_session", getContext().MODE_PRIVATE);
        inputPrenom.setText(prefs.getString("user_prenom", ""));
        inputNom.setText(prefs.getString("user_nom", ""));
        inputEmail.setText(prefs.getString("user_email", ""));

        btnSauvegarder.setOnClickListener(v -> {
            String prenom = inputPrenom.getText().toString().trim();
            String nom = inputNom.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();

            if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user_prenom", prenom);
            editor.putString("user_nom", nom);
            editor.putString("user_email", email);
            editor.apply();

            Toast.makeText(getContext(), "Profil mis à jour avec succès ✅", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
