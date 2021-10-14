package com.hoangnd.creative;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MusicActivity extends AppCompatActivity {
    private Handler threadHandler = new Handler();
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView tvMaxtime, tvCurrentposion;
    private Button bRewind, bStart, bPause, bForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvMaxtime = (TextView) findViewById(R.id.tv_activity_music_tv_maxtime);
        tvCurrentposion = (TextView) findViewById(R.id.tv_activity_music_tv_currentposion);
        bRewind = (Button) findViewById(R.id.b_activity_music_b_rewind);
        bStart = (Button) findViewById(R.id.b_activity_music_b_start);
        bPause = (Button) findViewById(R.id.b_activity_music_b_pause);
        bForward = (Button) findViewById(R.id.b_activity_music_b_forward);
        bPause.setEnabled(false);
        seekBar.setClickable(false);
        mediaPlayer = MediaPlayer.create(MusicActivity.this,R.raw.music);
    }

    private String millisecondsToString(int milliseconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds((long) milliseconds);
        return minutes + ":" + seconds;
    }

    public void doRewind(View view) {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        int SUBTRACT_TIME = 5000;

        if(currentPosition - SUBTRACT_TIME > 0 )  {
            this.mediaPlayer.seekTo(currentPosition - SUBTRACT_TIME);
        }
    }

    public void doStart(View view) {
        int duration = this.mediaPlayer.getDuration();
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if (currentPosition == 0) {
            this.seekBar.setMax(duration);
            String maxTimeString = this.millisecondsToString(duration);
            this.tvMaxtime.setText(maxTimeString);
        } else if (currentPosition == duration) {
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.start();
        UpdateSeekBarThread updateSeekbarThread = new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekbarThread,50);
        this.bPause.setEnabled(true);
        this.bStart.setEnabled(false);
    }


    class UpdateSeekBarThread implements Runnable{
        public void run(){
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = millisecondsToString(currentPosition);
            tvCurrentposion.setText(currentPositionStr);
            seekBar.setProgress(currentPosition);
            threadHandler.postDelayed(this,50);
        }
    }

    public void doPause(View view) {
        this.mediaPlayer.pause();
        this.bPause.setEnabled(false);
        this.bStart.setEnabled(true);
    }

    public void doFastForward(View view) {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        int ADD_TIME = 5000;
        if(currentPosition + ADD_TIME < duration)  {
            this.mediaPlayer.seekTo(currentPosition + ADD_TIME);
        }
    }
}