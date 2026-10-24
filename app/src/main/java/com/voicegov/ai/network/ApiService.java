package com.voicegov.ai.network;

import com.voicegov.ai.models.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/register")
    Call<User> register(@Body User user);

    @GET("api/schemes")
    Call<List<Scheme>> getSchemes();

    @GET("api/schemes/{id}")
    Call<Scheme> getSchemeDetails(@Path("id") int id);

    @POST("api/voice-query")
    Call<VoiceResponse> sendVoiceQuery(@Body VoiceRequest request);

    @GET("api/exams")
    Call<List<Exam>> getExams();

    @GET("api/jobs")
    Call<List<Job>> getJobs();

    @GET("api/scholarships")
    Call<List<Scholarship>> getScholarships();

    @GET("api/notifications")
    Call<List<Notification>> getNotifications();
}