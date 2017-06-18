package com.example.administrator.pushdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.recorder.api.LiveConfig;
import com.baidu.recorder.api.LiveSession;
import com.baidu.recorder.api.LiveSessionHW;
import com.baidu.recorder.api.LiveSessionSW;
import com.baidu.recorder.api.SessionStateListener;

public class MainActivity extends AppCompatActivity implements SessionStateListener {
    SurfaceView sv;
    LiveSession liveSession;
    String url = "rtmp://zhibo1.whunf.com.cn/xykj/push";
    boolean isPrepare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (SurfaceView) findViewById(R.id.sv);
        initLiveSession();
    }

    boolean isOpenFlash = false;

    public void openflash(View v) {
        isOpenFlash = !isOpenFlash;
        liveSession.toggleFlash(isOpenFlash);
    }

    int cameraState = 0;

    public static final int[] STATE = {LiveConfig.CAMERA_FACING_BACK, LiveConfig.CAMERA_FACING_FRONT};

    public void switchCamera(View v) {
        if (liveSession.canSwitchCamera()) {
            cameraState++;
            liveSession.switchCamera(STATE[cameraState % 2]);
        }
    }

    private void initLiveSession() {
        LiveConfig liveConfig = new LiveConfig.Builder()
                .setCameraId(LiveConfig.CAMERA_FACING_BACK) // 选择摄像头为前置摄像头
                .setCameraOrientation(LiveConfig.ORIENTATION_PORTRAIT) // 设置摄像头为竖向
                .setVideoWidth(1280) // 设置推流视频宽度, 需传入长的一边
                .setVideoHeight(720) // 设置推流视频高度，需传入短的一边
                .setVideoFPS(30) // 设置视频帧率
                .setInitVideoBitrate(1200) // 设置视频码率，单位为bit per seconds
                .setAudioBitrate(64 * 1000) // 设置音频码率，单位为bit per seconds
                .setAudioSampleRate(LiveConfig.AUDIO_SAMPLE_RATE_44100) // 设置音频采样率
                .setGopLengthInSeconds(2) // 设置I帧间隔，单位为秒
                .setQosEnabled(true) // 开启码率自适应，默认为true，即默认开启
                .setMinVideoBitrate(200 * 1000) // 码率自适应，最低码率
                .setMaxVideoBitrate(1024 * 1000) // 码率自适应，最高码率
                .setQosSensitivity(5) // 码率自适应，调整的灵敏度，单位为秒，可接受[5, 10]之间的整数值
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            liveSession = new LiveSessionHW(this, liveConfig);
        } else {
            liveSession = new LiveSessionSW(this, liveConfig);
        }
        liveSession.setStateListener(this);
        liveSession.bindPreviewDisplay(sv.getHolder());
        liveSession.prepareSessionAsync();
        //设置美颜
        liveSession.enableDefaultBeautyEffect(true);
    }


    boolean isPlay = false;
    boolean isError = false;

    public void play(View v) {
        Button btn = (Button) findViewById(R.id.button);
        if (isPrepare && !isError) {
            if (isPlay) {
                //暂停
                liveSession.startRtmpSession(url);
                btn.setText("停止");
            } else {
                liveSession.stopRtmpSession();
                btn.setText("开始");
            }
        } else if (isError) {
            showToast("推流出错");
        } else {
            showToast("正在准备推流");
        }
    }

    @Override
    public void onSessionPrepared(int i) {
        isPrepare = true;
    }

    @Override
    public void onSessionStarted(int i) {

    }

    @Override
    public void onSessionStopped(int i) {

    }

    @Override
    public void onSessionError(int i) {
        isError = true;
        /**
         * ERROR_CODE_OF_OPEN_MIC_FAILED // MIC设备无法打开
         ERROR_CODE_OF_OPEN_CAMERA_FAILED // 相机设备无法打开
         ERROR_CODE_OF_PREPARE_SESSION_FAILED // onSessionPrepared 接口调用失败，原因只能是 MIC 或相机打开失败
         ERROR_CODE_OF_CONNECT_TO_SERVER_FAILED // startRtmpSession 接口调用失败，原因通常为连接不上推流服务器
         ERROR_CODE_OF_DISCONNECT_FROM_SERVER_FAILED // stopRtmpSession 接口调用失败，原因通常是网络异常
         */
        switch (i) {
            case ERROR_CODE_OF_OPEN_MIC_FAILED:
                showToast("MIC设备无法打开");
                break;
            case ERROR_CODE_OF_OPEN_CAMERA_FAILED:
                showToast("相机设备无法打开");
                break;
            case ERROR_CODE_OF_PREPARE_SESSION_FAILED:
                showToast("接口调用失败，原因只能是 MIC 或相机打开失败");
                break;
            case ERROR_CODE_OF_CONNECT_TO_SERVER_FAILED:
                showToast("原因通常为连接不上推流服务器");
                break;
            case ERROR_CODE_OF_DISCONNECT_FROM_SERVER_FAILED:
                showToast("网路异常");
                break;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        liveSession.stopRtmpSession();
        super.onBackPressed();
    }
}
