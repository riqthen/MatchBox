package com.example.administrator.liveplayer;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    //url=rtmp://rtmpwspub.ttddsh.com/live/
    String url = "\"rtmp://zhibo2.xinyanyuan.com.cn/test_app/-10000001\"";

    SurfaceView sv;

    MediaPlayer mediaPlayer;

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!LibsChecker.checkVitamioLibs(this))
//            return;
        setContentView(R.layout.activity_main);
        sv = (SurfaceView) findViewById(R.id.sv);
        iv = (ImageView) findViewById(R.id.iv);
        sv.getHolder().addCallback(this);
        sv.setOnClickListener(this);
        sv.getHolder().setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer = new MediaPlayer(this);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepareAsync();
            mediaPlayer.setBufferSize(1024 * 1024 * 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iv.setVisibility(View.GONE);
        }
    };

    @Override
    public void onClick(View v) {
        //暂停
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                //暂停
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.pause);
                mediaPlayer.pause();
            } else {
                iv.setImageResource(R.mipmap.play);
                mediaPlayer.start();
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        }
    }
}
