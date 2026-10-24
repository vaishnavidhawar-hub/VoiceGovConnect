package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.SessionManager;

public class LanguageSelectionActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        sessionManager = new SessionManager(this);

        findViewById(R.id.btnEn).setOnClickListener(v -> selectLanguage("en"));
        findViewById(R.id.btnHi).setOnClickListener(v -> selectLanguage("hi"));
        findViewById(R.id.btnMr).setOnClickListener(v -> selectLanguage("mr"));
    }

    private void selectLanguage(String lang) {
        sessionManager.setLanguage(lang);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}