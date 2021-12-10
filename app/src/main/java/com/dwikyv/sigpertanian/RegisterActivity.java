package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dwikyv.sigpertanian.API.ApiClient;
import com.dwikyv.sigpertanian.API.ApiInterface;
import com.dwikyv.sigpertanian.Model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etFullname, etPassword, etEmail;
    Button btnRegister;
    TextView tvLogin;
    String Username, Fullname, Password, Email;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etRegisterUsername);
        etFullname = findViewById(R.id.etRegisterFullname);
        etPassword = findViewById(R.id.etRegisterPassword);
        etEmail = findViewById(R.id.etRegisterEmail);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        tvLogin = findViewById(R.id.tvLoginHere);
        tvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Username = etUsername.getText().toString();
                Fullname = etFullname.getText().toString();
                Password = etPassword.getText().toString();
                Email = etEmail.getText().toString();
                register(Username, Fullname, Password, Email);
                break;
            case R.id.tvLoginHere:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void register(String username, String fullname, String password, String email) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerResponse(username, fullname, password, email);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
