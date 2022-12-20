package com.example.vtmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vtmobileapp.Api.IDeviceController;
import com.example.vtmobileapp.Models.Device;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String baseUrl = "http://192.168.1.112:5243/api/";

    private TextView myTextview;

    private void init(){
        myTextview = findViewById(R.id.mytext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IDeviceController controller = retrofit.create(IDeviceController.class);
        Call<List<Device>> devices = controller.GetAll();
        devices.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if(response.code() != 200){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.code());
                    myTextview.setText(response.code());
                }

                assert response.body() != null;
                System.out.println(response.body().size());
                myTextview.setText(String.valueOf(response.body().get(0).getName()));
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
                myTextview.setText(t.getMessage());
            }
        });

        Device device = new Device();
        device.setName("denem3");
        device.setStationId(1);
        device.setStatus("denem3");
        addDevice(device, retrofit);

         */

    }

    private void addDevice(Device device, Retrofit retrofit)
    {
        IDeviceController controller = retrofit.create(IDeviceController.class);
        Call<Device> addDevice = controller.Add(device);

        addDevice.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}