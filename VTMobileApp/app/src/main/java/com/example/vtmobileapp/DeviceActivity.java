package com.example.vtmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vtmobileapp.Adapters.AdapterDevice;
import com.example.vtmobileapp.Api.IDeviceController;
import com.example.vtmobileapp.Models.Device;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterDevice adapterDevice;
    private ArrayList<Device> devices;

    private void init(){
        recyclerView = findViewById(R.id.device_recyclerview);
        devices = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        init();

        Intent intent = getIntent();
        int stationId = intent.getIntExtra("stationId", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticVariables.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        getDevices(retrofit, stationId);

    }


    private void getDevices(Retrofit retrofit, int stationId){
        IDeviceController deviceController = retrofit.create(IDeviceController.class);
        Call<List<Device>> devicesCall = deviceController.GetByStationId(stationId);

        devicesCall.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if(response.code() == 200 && response.body() != null){
                    devices.addAll(response.body());
                    adapterDevice = new AdapterDevice(DeviceActivity.this, devices);
                    recyclerView.setAdapter(adapterDevice);
                }
                else{
                    Toast.makeText(DeviceActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(DeviceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}




















