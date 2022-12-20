package com.example.vtmobileapp.Api;

import com.example.vtmobileapp.Models.Service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiceController {

    String field = "Service";

    @POST(field + "/add")
    Call<Service> AddService(@Body Service service);
}
