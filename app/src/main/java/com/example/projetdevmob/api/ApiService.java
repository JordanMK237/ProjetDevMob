package com.example.projetdevmob.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api.php?action=register")
    Call<RegisterReponse> registerUser(@Body NewUser user);

    // Ajout de l'appel login
    @POST("api.php?action=login")
    Call<LoginReponse> loginUser(@Body LoginRequete loginRequete);

    @POST("ajoutAppareil")
    Call<ApiResponse> ajouterAppareil(@Body AjoutAppareilRequete requete);
}
