package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.Scholarship;
import com.voicegov.ai.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScholarshipsRepository {
    public LiveData<List<Scholarship>> getScholarships() {
        MutableLiveData<List<Scholarship>> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getScholarships().enqueue(new Callback<List<Scholarship>>() {
            @Override
            public void onResponse(Call<List<Scholarship>> call, Response<List<Scholarship>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Scholarship>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}