package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.LoginResponse;
import com.voicegov.ai.models.User;
import com.voicegov.ai.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository;

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<LoginResponse> login(String mobile, String password) {
        return authRepository.login(mobile, password);
    }

    public LiveData<User> register(User user) {
        return authRepository.register(user);
    }
}