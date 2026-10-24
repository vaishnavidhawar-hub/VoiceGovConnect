package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SessionManager session = new SessionManager(this);
            if (session.isLoggedIn()) {
                startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LanguageSelectionActivity.class));
            }
            finish();
        }, 2000);
    }
}