package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.Job;
import com.voicegov.ai.repository.JobsRepository;
import java.util.List;

public class JobsViewModel extends ViewModel {
    private JobsRepository repository;

    public JobsViewModel() {
        repository = new JobsRepository();
    }

    public LiveData<List<Job>> getJobs() {
        return repository.getJobs();
    }
}