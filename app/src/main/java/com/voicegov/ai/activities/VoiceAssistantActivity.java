package com.voicegov.ai.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.voicegov.ai.R;
import com.voicegov.ai.utils.SessionManager;
import com.voicegov.ai.utils.VoiceHelper;
import com.voicegov.ai.viewmodel.VoiceViewModel;

public class VoiceAssistantActivity extends AppCompatActivity implements VoiceHelper.VoiceListener {
    private TextView tvQuery, tvResponse;
    private ImageButton btnMic;
    private VoiceHelper voiceHelper;
    private SessionManager sessionManager;
    private VoiceViewModel voiceViewModel;
    private static final int MIC_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_assistant);

        tvQuery = findViewById(R.id.tvQuery);
        tvResponse = findViewById(R.id.tvResponse);
        btnMic = findViewById(R.id.btnMic);
        
        sessionManager = new SessionManager(this);
        voiceHelper = new VoiceHelper(this, this);
        voiceViewModel = new ViewModelProvider(this).get(VoiceViewModel.class);

        btnMic.setOnClickListener(v -> {
            if (checkPermission()) {
                voiceHelper.startListening(sessionManager.getLanguage());
            } else {
                requestPermission();
            }
        });
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MIC_PERMISSION_CODE);
    }

    @Override
    public void onSpeechResult(String result) {
        tvQuery.setText(result);
        sendQueryToBackend(result);
    }

    @Override
    public void onReadyForSpeech() {
        Toast.makeText(this, "Listening...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        tvResponse.setText("Error: " + error);
    }

    private void sendQueryToBackend(String query) {
        voiceViewModel.sendVoiceQuery(query, sessionManager.getLanguage(), sessionManager.getUserId())
            .observe(this, response -> {
                if (response != null) {
                    String botResponse = response.response;
                    tvResponse.setText(botResponse);
                    voiceHelper.speak(botResponse, sessionManager.getLanguage());
                } else {
                    tvResponse.setText("Connection failed or Error");
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        voiceHelper.shutdown();
    }
}