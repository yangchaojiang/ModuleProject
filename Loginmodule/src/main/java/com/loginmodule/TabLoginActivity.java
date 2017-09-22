package com.loginmodule;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.yutils.AndroidWorKaroundUtils;
import com.yutils.TimeUtils;
import com.yutils.YUtils;

public class TabLoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    View ll_phone_numberLogin, llVerification_Login, login;
    private CountDownTimer countDownTimer;
    private TextView tvSendVerificationCode;
    private MyOnClickListener listener = new MyOnClickListener();
    private EditText et_phoneNumber, et_input_password, et_phone_number, et_input_verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_login);
        YUtils.initialize(this.getApplication());
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        login = findViewById(R.id.login);
        llVerification_Login = findViewById(R.id.ll_verification_Login);
        CheckBox checkBox_see_password = (CheckBox) findViewById(R.id.checkBox_see_password);
        et_phoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
        et_input_password = (EditText) findViewById(R.id.et_input_password);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_input_verification = (EditText) findViewById(R.id.et_input_verification);
        ll_phone_numberLogin = findViewById(R.id.ll_phone_numberLogin);
        tvSendVerificationCode = (TextView) findViewById(R.id.tv_sendVerificationCode);
        findViewById(R.id.forget_password).setOnClickListener(listener);
        findViewById(R.id.tv_register).setOnClickListener(listener);
        tvSendVerificationCode.setOnClickListener(listener);
        login.setOnClickListener(listener);
        AndroidWorKaroundUtils.assistActivity(this);
        checkBox_see_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_input_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    et_input_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_input_password.setSelection(et_input_password.length());
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(R.string.verify_code_to_log_in_quickly).setTag("0"));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.mobile_phone_login).setTag("1"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ("1".equals(tab.getTag().toString())) {
                    ll_phone_numberLogin.setVisibility(View.VISIBLE);
                    llVerification_Login.setVisibility(View.GONE);
                } else {
                    ll_phone_numberLogin.setVisibility(View.GONE);
                    llVerification_Login.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    /***
     * 登录
     ***/
    private void submit() {
        if (tabLayout.getSelectedTabPosition() == 0) {//验证码登录
            if (et_phone_number.getText().toString().isEmpty()) {
                YUtils.Toast(R.string.hint_login_username);
            } else if (et_input_verification.getText().toString().isEmpty()) {
                YUtils.Toast(R.string.hint_login_password);
            } else if (et_input_password.getText().toString().length() < 6) {
                YUtils.Toast(R.string.please_input_limit_pwd_length);
            } else {
                YUtils.closeInputMethod(et_phone_number);///实现登录业务
                submitBody(et_phone_number.getText().toString(), et_input_password.getText().toString(), 0);
            }
        } else {//密码登录
            if (et_phoneNumber.getText().toString().isEmpty()) {
                YUtils.Toast(R.string.hint_login_username);
            } else if (et_input_password.getText().toString().isEmpty()) {
                YUtils.Toast(R.string.hint_login_password);
            } else if (!et_phoneNumber.getText().toString().matches("[A-Za-z0-9]+")) {
                YUtils.Toast(R.string.please_input_limit_pwd);
            } else if (et_input_password.getText().toString().length() < 6) {
                YUtils.Toast(R.string.please_input_limit_pwd_length);
            } else {
                YUtils.closeInputMethod(et_phoneNumber);
                submitBody(et_phoneNumber.getText().toString(), et_input_password.getText().toString(), 1);
            }
        }
    }

    /***
     * 发送验证码
     **/
    private void senCode() {
        countDownTimer = TimeUtils.countDown(this, 60, 1, new CodeCount());
        //跳用发送验证码接口
    }

    /***
     * 提交数据
     *
     * @param phone 手机号
     * @param pwd   密码或者 验证码
     * @param type  登录类型 0验证码登录  1密码登录
     ***/
    public void submitBody(String phone, String pwd, int type) {
        ///实现登录业务
        //getPresenter().login(phone,pwd,type);
    }

    /***
     * 点击事件类
     ***/
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_sendVerificationCode) {
                senCode();
            } else if (v.getId() == R.id.login) {
                submit();
            } else if (v.getId() == R.id.forget_password) {
                //忘记密码
            } else if (v.getId() == R.id.tv_register) {
                //注册账号
            }
        }
    }

    /***
     * 验证回调
     **/
    private class CodeCount implements TimeUtils.CountDownListener {
        @Override
        public void onFinish(String text) {
            tvSendVerificationCode.setText(text);
            tvSendVerificationCode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished, String text) {
            tvSendVerificationCode.setText(text);
            tvSendVerificationCode.setEnabled(false);
        }
    }
}
