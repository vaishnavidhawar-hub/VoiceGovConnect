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
import com.voicegov.ai.adapters.JobAdapter;
import com.voicegov.ai.models.Job;
import com.voicegov.ai.viewmodel.JobsViewModel;
import java.util.List;

public class JobsActivity extends AppCompatActivity {
    private RecyclerView rvJobs;
    private ProgressBar progressBar;
    private JobsViewModel jobsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        rvJobs = findViewById(R.id.rvJobs);
        progressBar = findViewById(R.id.progressBar);
        rvJobs.setLayoutManager(new LinearLayoutManager(this));

        jobsViewModel = new ViewModelProvider(this).get(JobsViewModel.class);
        loadJobs();
    }

    private void loadJobs() {
        progressBar.setVisibility(View.VISIBLE);
        jobsViewModel.getJobs().observe(this, jobs -> {
            progressBar.setVisibility(View.GONE);
            if (jobs != null) {
                JobAdapter adapter = new JobAdapter(jobs);
                rvJobs.setAdapter(adapter);
            } else {
                Toast.makeText(JobsActivity.this, "Failed to load jobs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}