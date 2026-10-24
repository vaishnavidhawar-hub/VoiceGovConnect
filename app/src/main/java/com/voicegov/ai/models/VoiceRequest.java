package com.voicegov.ai.models;
public class VoiceRequest {
    public String query, language;
    public int user_id;
    public VoiceRequest(String q, String l, int uid) { query = q; language = l; user_id = uid; }
}