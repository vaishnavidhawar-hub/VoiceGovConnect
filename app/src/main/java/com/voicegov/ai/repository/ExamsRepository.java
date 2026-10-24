package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.Exam;
import com.voicegov.ai.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamsRepository {
    public LiveData<List<Exam>> getExams() {
        MutableLiveData<List<Exam>> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getExams().enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}