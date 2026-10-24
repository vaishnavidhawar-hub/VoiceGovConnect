package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.Scheme;
import com.voicegov.ai.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchemesRepository {
    public LiveData<List<Scheme>> getSchemes() {
        MutableLiveData<List<Scheme>> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getSchemes().enqueue(new Callback<List<Scheme>>() {
            @Override
            public void onResponse(Call<List<Scheme>> call, Response<List<Scheme>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Scheme>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Scheme> getSchemeDetails(int id) {
        MutableLiveData<Scheme> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getSchemeDetails(id).enqueue(new Callback<Scheme>() {
            @Override
            public void onResponse(Call<Scheme> call, Response<Scheme> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Scheme> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}