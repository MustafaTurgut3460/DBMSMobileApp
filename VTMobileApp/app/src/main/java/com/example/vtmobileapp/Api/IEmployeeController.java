package com.example.vtmobileapp.Api;

import com.example.vtmobileapp.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IEmployeeController {
    String field = "Employee";

    @GET(field + "/getall")
    Call<List<Employee>> GetAll();

    @GET(field + "/getbypassword/{password}")
    Call<Employee> GetByPassword(@Path("password") String password);

    @GET(field + "/getonline")
    Call<Employee> GetOnlineUser();

    @PUT(field + "/update")
    Call<Employee> Update(@Body Employee employee);
}
