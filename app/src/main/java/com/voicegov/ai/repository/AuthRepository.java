package com.voicegov.ai.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.voicegov.ai.models.LoginRequest;
import com.voicegov.ai.models.LoginResponse;
import com.voicegov.ai.models.User;
import com.voicegov.ai.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    public LiveData<LoginResponse> login(String mobile, String password) {
        MutableLiveData<LoginResponse> loginData = new MutableLiveData<>();
        LoginRequest request = new LoginRequest(mobile, password);

        RetrofitClient.getApiService().login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginData.setValue(response.body());
                } else {
                    loginData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginData.setValue(null);
            }
        });
        return loginData;
    }

    public LiveData<User> register(User user) {
        MutableLiveData<User> registerData = new MutableLiveData<>();
        RetrofitClient.getApiService().register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    registerData.setValue(response.body());
                } else {
                    registerData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                registerData.setValue(null);
            }
        });
        return registerData;
    }
}