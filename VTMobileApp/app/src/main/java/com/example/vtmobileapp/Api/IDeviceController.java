package com.example.vtmobileapp.Api;

import com.example.vtmobileapp.Models.Device;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IDeviceController {
    String field = "Device";

    @GET(field + "/getall")
    Call<List<Device>> GetAll();

    @GET(field + "/getbyid/{id}")
    Call<Device> GetById(@Path("id") int id);

    @GET(field + "/getbystation/{id}")
    Call<List<Device>> GetByStationId(@Path("id") int id);

    @POST(field + "/add")
    Call<Device> Add(@Body Device device);

    @PUT(field + "/update")
    Call Update(@Body Device device);

    @DELETE(field + "/delete/{id}")
    Call Delete(@Path("id") int id);
}
