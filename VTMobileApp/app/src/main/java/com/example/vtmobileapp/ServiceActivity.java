package com.example.vtmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vtmobileapp.Api.IDeviceController;
import com.example.vtmobileapp.Api.IEmployeeController;
import com.example.vtmobileapp.Api.IServiceController;
import com.example.vtmobileapp.Models.Device;
import com.example.vtmobileapp.Models.Employee;
import com.example.vtmobileapp.Models.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceActivity extends AppCompatActivity {

    private EditText titleEdittext, descEdittext;
    private TextView deviceNameTextview;
    private Button addButton;
    private RadioGroup radioGroup;

    private void init(){
        titleEdittext = findViewById(R.id.title_edittext);
        descEdittext = findViewById(R.id.desc_edittext);
        deviceNameTextview = findViewById(R.id.device_name_textview);
        addButton = findViewById(R.id.add_service_button);
        radioGroup = findViewById(R.id.radio_group);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticVariables.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent intent = getIntent();
        int deviceId = intent.getIntExtra("deviceId", 0);

        getDeviceNameById(deviceId, retrofit);

        addButton.setOnClickListener(view -> {
            String title = titleEdittext.getText().toString();
            String desc = descEdittext.getText().toString();
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if(title.isEmpty() || desc.isEmpty() || selectedId == -1){
                Toast.makeText(this, "Lütfen tüm birimleri doldurunuz!", Toast.LENGTH_SHORT).show();
            }
            else{
                // add service
                String status = getSelectedRadioValue(selectedId);
                Service service = new Service();
                service.setServiceTitle(title);
                service.setServiceDesc(desc);
                service.setDeviceId(deviceId);
                service.setDeviceStatus(status);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                String dateTime = simpleDateFormat.format(new Date());

                service.setTime(dateTime);

                getEmployeeId(retrofit, service);
            }

        });
    }

    private void getDeviceNameById(int id, Retrofit retrofit){
        IDeviceController deviceController = retrofit.create(IDeviceController.class);
        Call<Device> deviceCall = deviceController.GetById(id);

        deviceCall.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if(response.code() == 200 && response.body() != null){
                    String deviceName = response.body().getName();
                    deviceNameTextview.setText(deviceName);
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {

            }
        });
    }

    private void addService(Retrofit retrofit, Service service){
        IServiceController serviceController = retrofit.create(IServiceController.class);
        Call<Service> serviceCall = serviceController.AddService(service);

        serviceCall.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                Toast.makeText(ServiceActivity.this, "Servis Eklendi!", Toast.LENGTH_SHORT).show();
                if(response.code() == 200){
                    Toast.makeText(ServiceActivity.this, "Servis başarıyla eklendi!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ServiceActivity.this, StationActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(ServiceActivity.this, "Hata Oluştu" + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                Toast.makeText(ServiceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getSelectedRadioValue(int selectedId){
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }

    private void getEmployeeId(Retrofit retrofit, Service service){

        IEmployeeController employeeController = retrofit.create(IEmployeeController.class);
        Call<Employee> employeeCall = employeeController.GetOnlineUser();

        employeeCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(response.code() == 200 && response.body() != null){
                    int employeeId = response.body().getId();
                    service.setEmployeeId(employeeId);

                    addService(retrofit, service);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }
}



















