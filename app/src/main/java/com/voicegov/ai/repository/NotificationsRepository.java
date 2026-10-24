package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.Notification;
import com.voicegov.ai.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsRepository {
    public LiveData<List<Notification>> getNotifications() {
        MutableLiveData<List<Notification>> data = new MutableLiveData<>();
        RetrofitClient.getApiService().getNotifications().enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}