package com.example.splitworld.api;

import com.example.splitworld.api.endpoint.TravelEndPoint;
import com.example.splitworld.api.model.Response;
import com.example.splitworld.api.model.Travel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String URL_ROOT = "http://api.genialsaude.com.br/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getTravelById(int id, final Callback<Travel> callback) {
        TravelEndPoint endPoint = retrofit.create(TravelEndPoint.class);
        Call<Travel> call = endPoint.getTravelById(id);
        call.enqueue(callback);
    }
    public static void getTravelByAccount(int account, final Callback<ArrayList<Travel>> callback) {
        TravelEndPoint endPoint = retrofit.create(TravelEndPoint.class);
        Call<ArrayList<Travel>> call = endPoint.getTravelByAccount(account);
        call.enqueue(callback);
    }
    public static void postTravel(Travel travel, final Callback<Response> callback) {
        TravelEndPoint endPoint = retrofit.create(TravelEndPoint.class);
        Call<Response> call = endPoint.postTravel(travel);
        call.enqueue(callback);
    }
}
