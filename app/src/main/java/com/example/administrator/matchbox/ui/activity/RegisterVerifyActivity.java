package com.example.administrator.matchbox.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.matchbox.R;
import com.example.administrator.matchbox.base.BaseActivity;
import com.example.administrator.matchbox.config.MyApp;
import com.example.administrator.matchbox.utils.BoxUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/11/25.
 */
// TODO 短信发送成功后，收到验证码的界面
public class RegisterVerifyActivity extends BaseActivity implements TextWatcher {
    @BindView(R.id.et_code)
    EditText etCode;        //输入验证码
    @BindView(R.id.tv_submit)
    TextView tvSubmit;      //提交
    @BindView(R.id.tv_voice)
    TextView tvVoice;       //接收语音验证码按钮
    @BindView(R.id.iv_clear)
    ImageView ivClear;      //清除文本按钮


    private String phoneNumber;
    private String country;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register_verify;
    }

    @Override
    protected void initView() {
        //username
        phoneNumber = getIntent().getStringExtra("phone");
        country = getIntent().getStringExtra("country");
        //开启定时器
        startDownTime();
        etCode.addTextChangedListener(this);
    }

    VerifyCountDownTimer downTimer;

    private void startDownTime() {
        downTimer = new VerifyCountDownTimer();
        tvVoice.setEnabled(false);
        downTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            int time = downTimer.restTime;
            if (time != 0) {    //即倒计时还在运行时，取消倒计时，同时记录此手机号和剩余的时间
                //电话号码
                downTimer.cancel();
                MyApp.getInstance().addVerifyPhone(phoneNumber, time);
            }
        }
    }


    @OnClick({R.id.tv_submit, R.id.tv_voice, R.id.iv_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                //发送验证码
//                submit();
                jumpActivity();
                break;
            case R.id.tv_voice:
                sendVoice();
                break;
            case R.id.iv_clear:
                etCode.getEditableText().clear();
                break;
        }
    }

    private void sendVoice() {
        dialog = BoxUtils.showProgressDialog(this);
        dialog.show();
        SMSSDK.getVoiceVerifyCode(country, phoneNumber);
    }

    Dialog dialog;

    private void submit() {
//        dialog = BoxUtils.showProgressDialog(this);
//        dialog.show();
//        SMSSDK.submitVerificationCode(country, phoneNumber, etCode.getText().toString());
        jumpActivity();
    }


    @Override
    protected void onStart() {
        super.onStart();
        SMSSDK.registerEventHandler(eventHandler);
    }

    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int i, int i1, Object o) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (i1 == SMSSDK.RESULT_COMPLETE) {
                switch (i) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        //验证成功
                        showToast("验证成功");
                        jumpActivity();
                        break;
                    case SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE:
                        showToast("语音验证码已发送，请注意接听!");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startDownTime();
                            }
                        });
                        break;
                }
            } else {
                showToast("验证失败");
            }
        }
    };

    private void jumpActivity() {
        Intent intent = new Intent(getActivity(), RegisterPasswordActivity.class);
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            tvSubmit.setEnabled(true);
            ivClear.setVisibility(View.VISIBLE);
        } else {
            ivClear.setVisibility(View.GONE);
            tvSubmit.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    //    倒计时，CountDownTimer类主要用于短信倒计时，一般可以用Timer
    private class VerifyCountDownTimer extends CountDownTimer {

        int restTime;   //发送短信剩余时间

        VerifyCountDownTimer() {
            //30 倒计时总长度30秒
            //1  每一秒onTick
            super(10000, 1000);
            restTime = 30;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvVoice.setText("已发送:" + millisUntilFinished / 1000 + "秒");
            restTime--;
        }

        @Override
        public void onFinish() {
            tvVoice.setText("接受语音验证码");
            tvVoice.setEnabled(true);
            restTime = 0;
        }
    }
}
