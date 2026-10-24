package com.voicegov.ai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import com.voicegov.ai.R;
import com.voicegov.ai.adapters.ExamAdapter;
import com.voicegov.ai.models.Exam;
import com.voicegov.ai.viewmodel.ExamsViewModel;
import java.util.List;

public class ExamsActivity extends AppCompatActivity implements ExamAdapter.OnExamClickListener {
    private RecyclerView rvExams;
    private ProgressBar progressBar;
    private ExamsViewModel examsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        rvExams = findViewById(R.id.rvExams);
        progressBar = findViewById(R.id.progressBar);
        rvExams.setLayoutManager(new LinearLayoutManager(this));

        examsViewModel = new ViewModelProvider(this).get(ExamsViewModel.class);
        loadExams();
    }

    private void loadExams() {
        progressBar.setVisibility(View.VISIBLE);
        examsViewModel.getExams().observe(this, exams -> {
            progressBar.setVisibility(View.GONE);
            if (exams != null) {
                ExamAdapter adapter = new ExamAdapter(exams, ExamsActivity.this);
                rvExams.setAdapter(adapter);
            } else {
                Toast.makeText(ExamsActivity.this, "Failed to load exams", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onExamClick(Exam exam) {
        Intent intent = new Intent(this, ExamDetailActivity.class);
        intent.putExtra("exam_id", exam.getId());
        startActivity(intent);
    }
}