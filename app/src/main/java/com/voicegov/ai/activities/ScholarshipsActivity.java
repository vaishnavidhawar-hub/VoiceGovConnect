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
import com.voicegov.ai.adapters.ScholarshipAdapter;
import com.voicegov.ai.models.Scholarship;
import com.voicegov.ai.viewmodel.ScholarshipsViewModel;
import java.util.List;

public class ScholarshipsActivity extends AppCompatActivity {
    private RecyclerView rvScholarships;
    private ProgressBar progressBar;
    private ScholarshipsViewModel scholarshipsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarships);

        rvScholarships = findViewById(R.id.rvScholarships);
        progressBar = findViewById(R.id.progressBar);
        rvScholarships.setLayoutManager(new LinearLayoutManager(this));

        scholarshipsViewModel = new ViewModelProvider(this).get(ScholarshipsViewModel.class);
        loadScholarships();
    }

    private void loadScholarships() {
        progressBar.setVisibility(View.VISIBLE);
        scholarshipsViewModel.getScholarships().observe(this, scholarships -> {
            progressBar.setVisibility(View.GONE);
            if (scholarships != null) {
                ScholarshipAdapter adapter = new ScholarshipAdapter(scholarships);
                rvScholarships.setAdapter(adapter);
            } else {
                Toast.makeText(ScholarshipsActivity.this, "Failed to load scholarships", Toast.LENGTH_SHORT).show();
            }
        });
    }
}