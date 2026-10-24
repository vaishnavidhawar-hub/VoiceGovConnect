package com.voicegov.ai.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.voicegov.ai.R;
import com.voicegov.ai.models.Scheme;
import com.voicegov.ai.viewmodel.SchemesViewModel;

public class SchemeDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvDesc, tvEligibility, tvBenefits;
    private ProgressBar progressBar;
    private SchemesViewModel schemesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_detail);

        tvTitle = findViewById(R.id.tvSchemeTitle);
        tvDesc = findViewById(R.id.tvSchemeDesc);
        tvEligibility = findViewById(R.id.tvSchemeEligibility);
        tvBenefits = findViewById(R.id.tvSchemeBenefits);
        progressBar = findViewById(R.id.progressBar);

        schemesViewModel = new ViewModelProvider(this).get(SchemesViewModel.class);

        int schemeId = getIntent().getIntExtra("scheme_id", -1);
        if (schemeId != -1) {
            loadSchemeDetails(schemeId);
        }
    }

    private void loadSchemeDetails(int id) {
        progressBar.setVisibility(View.VISIBLE);
        schemesViewModel.getSchemeDetails(id).observe(this, scheme -> {
            progressBar.setVisibility(View.GONE);
            if (scheme != null) {
                tvTitle.setText(scheme.getTitle());
                tvDesc.setText(scheme.getDescription());
                tvEligibility.setText(scheme.getEligibility());
                tvBenefits.setText(scheme.getBenefits());
            } else {
                Toast.makeText(SchemeDetailActivity.this, "Failed to load details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
