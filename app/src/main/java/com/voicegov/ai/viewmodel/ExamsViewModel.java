package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.Exam;
import com.voicegov.ai.repository.ExamsRepository;
import java.util.List;

public class ExamsViewModel extends ViewModel {
    private ExamsRepository repository;

    public ExamsViewModel() {
        repository = new ExamsRepository();
    }

    public LiveData<List<Exam>> getExams() {
        return repository.getExams();
    }
}