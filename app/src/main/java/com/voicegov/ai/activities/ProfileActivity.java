package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.Constants;
import com.voicegov.ai.utils.SessionManager;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);
        TextView tvName = findViewById(R.id.tvProfileName);
        TextView tvEmail = findViewById(R.id.tvProfileEmail);
        Button btnLogout = findViewById(R.id.btnLogout);

        HashMap<String, String> user = sessionManager.getUserDetails();
        tvName.setText(user.get(Constants.KEY_USER_NAME));
        tvEmail.setText(user.get(Constants.KEY_USER_EMAIL));

        btnLogout.setOnClickListener(v -> {
            sessionManager.logoutUser();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}