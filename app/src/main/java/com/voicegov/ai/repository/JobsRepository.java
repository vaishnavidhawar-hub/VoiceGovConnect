package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.Job;
import com.voicegov.ai.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRepository {
    public LiveData<List<Job>> getJobs() {
        MutableLiveData<List<Job>> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getJobs().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}