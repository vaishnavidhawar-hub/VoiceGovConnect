package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.Scheme;
import com.voicegov.ai.repository.SchemesRepository;
import java.util.List;

public class SchemesViewModel extends ViewModel {
    private SchemesRepository repository;

    public SchemesViewModel() {
        repository = new SchemesRepository();
    }

    public LiveData<List<Scheme>> getSchemes() {
        return repository.getSchemes();
    }

    public LiveData<Scheme> getSchemeDetails(int id) {
        return repository.getSchemeDetails(id);
    }
}