package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.SessionManager;
import com.voicegov.ai.viewmodel.AuthViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText etMobile, etPassword;
    private SessionManager sessionManager;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> loginUser());
        if (tvRegister != null) {
            tvRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        }
    }

    private void loginUser() {
        String mobile = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (mobile.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        authViewModel.login(mobile, password).observe(this, response -> {
            if (response != null && "success".equals(response.status)) {
                sessionManager.createLoginSession(response.user.id, response.user.name, response.user.mobile, response.user.email);
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            } else {
                String msg = (response != null && response.message != null) ? response.message : "Invalid Credentials or Network Error";
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}