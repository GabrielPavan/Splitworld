package com.example.splitworld.api.endpoint;

import com.example.splitworld.api.model.Response;
import com.example.splitworld.api.model.Travel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TravelEndPoint {
    @GET("/api/listar/viagem?viagemId={viagemId}")
    Call<Travel> getTravelById(@Path("viagemId") int id);

    @GET("/api/listar/viagem/conta?contaId={contaId}")
    Call<ArrayList<Travel>> getTravelByAccount(@Path("contaId") int Account_id);

    @POST("/api/cadastro/viagem")
    Call<Response> postTravel(@Body Travel travel);
}
