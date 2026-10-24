package com.voicegov.ai.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import java.util.ArrayList;
import java.util.Locale;

public class VoiceHelper {

    public interface VoiceListener {
        void onSpeechResult(String result);
        void onReadyForSpeech();
        void onError(String error);
    }

    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private VoiceListener listener;
    private Context context;

    public VoiceHelper(Context context, VoiceListener listener) {
        this.context = context;
        this.listener = listener;

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                listener.onReadyForSpeech();
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                listener.onError("Speech error: " + error);
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    listener.onSpeechResult(matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        textToSpeech = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.SUCCESS) {
                listener.onError("TTS Initialization failed");
            }
        });
    }

    public void startListening(String languageCode) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode);
        speechRecognizer.startListening(intent);
    }

    public void speak(String text, String languageCode) {
        textToSpeech.setLanguage(new Locale(languageCode));
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void shutdown() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}