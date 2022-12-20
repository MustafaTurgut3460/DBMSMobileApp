package com.example.vtmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.vtmobileapp.Adapters.AdapterStation;
import com.example.vtmobileapp.Api.IEmployeeController;
import com.example.vtmobileapp.Api.IStationController;
import com.example.vtmobileapp.Models.Employee;
import com.example.vtmobileapp.Models.Station;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterStation adapterStation;
    private ArrayList<Station> stations;

    private void init(){
        recyclerView = findViewById(R.id.recyclerview);
        stations = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        init();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticVariables.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        getStations(retrofit);
    }

    private void getStations(Retrofit retrofit){
        IStationController stationController = retrofit.create(IStationController.class);
        Call<List<Station>> stationsCall = stationController.GetAll();

        stationsCall.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if(response.code() == 200 && response.body() != null){
                    stations.addAll(response.body());

                    adapterStation = new AdapterStation(StationActivity.this, stations);
                    recyclerView.setAdapter(adapterStation);
                }
                else{
                    Toast.makeText(StationActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Toast.makeText(StationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

























