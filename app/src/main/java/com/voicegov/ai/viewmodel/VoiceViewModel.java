package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.VoiceResponse;
import com.voicegov.ai.repository.VoiceRepository;

public class VoiceViewModel extends ViewModel {
    private VoiceRepository voiceRepository;

    public VoiceViewModel() {
        voiceRepository = new VoiceRepository();
    }

    public LiveData<VoiceResponse> sendVoiceQuery(String query, String language, int userId) {
        return voiceRepository.sendVoiceQuery(query, language, userId);
    }
}