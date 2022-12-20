package com.example.vtmobileapp.Api;

import com.example.vtmobileapp.Models.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IStationController {
    String field = "Station";

    @GET(field + "/getall")
    Call<List<Station>> GetAll();
}
