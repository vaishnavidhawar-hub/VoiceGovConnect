package com.voicegov.ai.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import com.voicegov.ai.R;
import com.voicegov.ai.adapters.NotificationAdapter;
import com.voicegov.ai.models.Notification;
import com.voicegov.ai.viewmodel.NotificationsViewModel;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private RecyclerView rvNotifications;
    private ProgressBar progressBar;
    private NotificationsViewModel notificationsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        rvNotifications = findViewById(R.id.rvNotifications);
        progressBar = findViewById(R.id.progressBar);
        rvNotifications.setLayoutManager(new LinearLayoutManager(this));

        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        loadNotifications();
    }

    private void loadNotifications() {
        progressBar.setVisibility(View.VISIBLE);
        notificationsViewModel.getNotifications().observe(this, notifications -> {
            progressBar.setVisibility(View.GONE);
            if (notifications != null) {
                NotificationAdapter adapter = new NotificationAdapter(notifications);
                rvNotifications.setAdapter(adapter);
            } else {
                Toast.makeText(NotificationsActivity.this, "Failed to load notifications", Toast.LENGTH_SHORT).show();
            }
        });
    }
}