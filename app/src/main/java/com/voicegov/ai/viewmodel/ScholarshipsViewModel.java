package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.Scholarship;
import com.voicegov.ai.repository.ScholarshipsRepository;
import java.util.List;

public class ScholarshipsViewModel extends ViewModel {
    private ScholarshipsRepository repository;

    public ScholarshipsViewModel() {
        repository = new ScholarshipsRepository();
    }

    public LiveData<List<Scholarship>> getScholarships() {
        return repository.getScholarships();
    }
}