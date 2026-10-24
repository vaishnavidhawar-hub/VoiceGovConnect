package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.SessionManager;

public class DashboardActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager = new SessionManager(this);

        findViewById(R.id.btnSchemes).setOnClickListener(v -> startActivity(new Intent(this, SchemesActivity.class)));
        findViewById(R.id.btnExams).setOnClickListener(v -> startActivity(new Intent(this, ExamsActivity.class)));
        findViewById(R.id.btnScholarships).setOnClickListener(v -> startActivity(new Intent(this, ScholarshipsActivity.class)));
        findViewById(R.id.btnJobs).setOnClickListener(v -> startActivity(new Intent(this, JobsActivity.class)));
        findViewById(R.id.btnVoiceAssistant).setOnClickListener(v -> startActivity(new Intent(this, VoiceAssistantActivity.class)));
        findViewById(R.id.btnProfile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        findViewById(R.id.btnNotifications).setOnClickListener(v -> startActivity(new Intent(this, NotificationsActivity.class)));
    }
}