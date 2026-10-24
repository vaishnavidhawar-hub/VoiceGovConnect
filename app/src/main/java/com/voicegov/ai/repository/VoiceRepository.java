package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.VoiceRequest;
import com.voicegov.ai.models.VoiceResponse;
import com.voicegov.ai.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoiceRepository {
    public LiveData<VoiceResponse> sendVoiceQuery(String query, String language, int userId) {
        MutableLiveData<VoiceResponse> voiceData = new MutableLiveData<>();
        VoiceRequest request = new VoiceRequest(query, language, userId);

        RetrofitClient.getApiService().sendVoiceQuery(request).enqueue(new Callback<VoiceResponse>() {
            @Override
            public void onResponse(Call<VoiceResponse> call, Response<VoiceResponse> response) {
                if (response.isSuccessful()) {
                    voiceData.setValue(response.body());
                } else {
                    voiceData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<VoiceResponse> call, Throwable t) {
                voiceData.setValue(null);
            }
        });
        return voiceData;
    }
}