package com.example.vtmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vtmobileapp.Api.IEmployeeController;
import com.example.vtmobileapp.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView registerTextview;
    private EditText emailEdittext, passwordEdittext;
    private Button loginButton;

    private ProgressDialog progressDialog;

    private void init(){
        registerTextview = findViewById(R.id.register_textview);
        emailEdittext = findViewById(R.id.email_edittext);
        passwordEdittext = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticVariables.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginButton.setOnClickListener(view -> {
            String email = emailEdittext.getText().toString();
            String password = passwordEdittext.getText().toString();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,   "Lütfen tüm birimleri doldurunuz!", Toast.LENGTH_SHORT).show();
            }
            else{
                loginUser(email, password, retrofit);
            }
        });
    }

    private void loginUser(String email, String password, Retrofit retrofit){
        progressDialog.setTitle("Giriş Yapılıyor...");
        progressDialog.show();

        setAllOffline(retrofit);

        IEmployeeController employeeController = retrofit.create(IEmployeeController.class);
        Call<Employee> loggedEmployee = employeeController.GetByPassword(password);

        loggedEmployee.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(@NonNull Call<Employee> call, @NonNull Response<Employee> response) {

                if(response.code() == 200){
                    Employee employee = response.body();
                    employee.setStatus("online");

                    Call<Employee> updateCall = employeeController.Update(employee);
                    updateCall.enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            progressDialog.dismiss();

                            if(response.code() == 200){
                                startActivity(new Intent(LoginActivity.this, StationActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Hata Oluştu!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAllOffline(Retrofit retrofit){
        IEmployeeController employeeController = retrofit.create(IEmployeeController.class);
        Call<List<Employee>> employees = employeeController.GetAll();

        employees.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(response.code() == 200 && response.body() != null){

                    for(Employee employee : response.body()){
                        employee.setStatus("offline");

                        Call<Employee> updateCall = employeeController.Update(employee);
                        updateCall.enqueue(new Callback<Employee>() {
                            @Override
                            public void onResponse(Call<Employee> call, Response<Employee> response) {
                            }

                            @Override
                            public void onFailure(Call<Employee> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });
    }
}



















