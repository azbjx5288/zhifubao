package com.ace.android.supervise.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ace.android.supervise.R;
import com.ace.android.supervise.service.MyNotificationListenService;

/**
 * Created by Gan on 2018/11/9.
 */

public class NotificationActivity  extends Activity implements View.OnClickListener {

    private Button notifyButton;
    private Button setAuthButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initView();
        // 开启服务
        startNotificationListenService();
    }

    private void initView() {
        notifyButton = (Button) findViewById(R.id.button_notify);
        notifyButton.setOnClickListener(this);
        setAuthButton = (Button) findViewById(R.id.button_setauth);
        setAuthButton.setOnClickListener(this);
    }

    private void startNotificationListenService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Intent intent = new Intent(NotificationActivity.this,
                    MyNotificationListenService.class);
            startService(intent);
        } else {
            Toast.makeText(NotificationActivity.this, "手机的系统不支持此功能", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_notify:
                showNotify();
                break;
            case R.id.button_setauth:
                setAuth();
                break;
            default:
                break;
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    private void showNotify() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // android 3.0之前
            n = new Notification(R.drawable.ic_launcher_background, "title",
                    System.currentTimeMillis());
        } else {
            // android 3.0之后
            n = new Notification.Builder(NotificationActivity.this)
                    .setSmallIcon(R.drawable.ic_launcher_background).setTicker("title")
                    .setContentTitle("content title")
                    .setContentText("content text").setSubText("sub text")
                    .setWhen(System.currentTimeMillis()).build();
        }
        manager.notify(0, n);
    }

    private void setAuth() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Intent intent = new Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        } else {
            Toast.makeText(NotificationActivity.this, "手机的系统不支持此功能", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
