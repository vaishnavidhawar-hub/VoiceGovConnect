package com.voicegov.ai.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.voicegov.ai.R;

public class ExamDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);
        
        // Basic implementation, can be expanded to fetch details via API
        String title = getIntent().getStringExtra("exam_title");
        TextView tvTitle = findViewById(R.id.tvExamTitleDetail);
        if (title != null) tvTitle.setText(title);
    }
}