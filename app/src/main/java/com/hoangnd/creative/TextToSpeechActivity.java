package com.hoangnd.creative;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etContent;
    private Button bConvert;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        tvTitle = (TextView) findViewById(R.id.tv_text_to_speech_tv_title);
        etContent = (EditText) findViewById(R.id.et_text_to_speech_et_content);
        bConvert = (Button) findViewById(R.id.b_text_to_speech_b_convert);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = tts.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bConvert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String data = etContent.getText().toString();
                int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                if (speechStatus == TextToSpeech.ERROR) {
                }
            }

        });
    }
            @Override
            public void onDestroy() {
                super.onDestroy();
                if (tts != null) {
                    tts.stop();
                    tts.shutdown();
                }
            }
    }
