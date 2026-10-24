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
import com.voicegov.ai.adapters.SchemeAdapter;
import com.voicegov.ai.models.Scheme;
import com.voicegov.ai.viewmodel.SchemesViewModel;
import java.util.List;

public class SchemesActivity extends AppCompatActivity implements SchemeAdapter.OnSchemeClickListener {
    private RecyclerView rvSchemes;
    private ProgressBar progressBar;
    private SchemesViewModel schemesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);

        rvSchemes = findViewById(R.id.rvSchemes);
        progressBar = findViewById(R.id.progressBar);
        rvSchemes.setLayoutManager(new LinearLayoutManager(this));

        schemesViewModel = new ViewModelProvider(this).get(SchemesViewModel.class);
        loadSchemes();
    }

    private void loadSchemes() {
        progressBar.setVisibility(View.VISIBLE);
        schemesViewModel.getSchemes().observe(this, schemes -> {
            progressBar.setVisibility(View.GONE);
            if (schemes != null) {
                SchemeAdapter adapter = new SchemeAdapter(schemes, SchemesActivity.this);
                rvSchemes.setAdapter(adapter);
            } else {
                Toast.makeText(SchemesActivity.this, "Failed to load schemes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSchemeClick(Scheme scheme) {
        Intent intent = new Intent(this, SchemeDetailActivity.class);
        intent.putExtra("scheme_id", scheme.getId());
        startActivity(intent);
    }
}