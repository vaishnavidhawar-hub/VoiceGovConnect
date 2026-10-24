package com.voicegov.ai.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.voicegov.ai.models.Notification;
import com.voicegov.ai.repository.NotificationsRepository;
import java.util.List;

public class NotificationsViewModel extends ViewModel {
    private NotificationsRepository repository;

    public NotificationsViewModel() {
        repository = new NotificationsRepository();
    }

    public LiveData<List<Notification>> getNotifications() {
        return repository.getNotifications();
    }
}