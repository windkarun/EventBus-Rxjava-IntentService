package com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
//https://gunhansancar.com/ease-communication-between-activities-fragments-services/
    public static String DOWNLOAD_ACTION = "com.e";
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void btn(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        intent.setAction(DOWNLOAD_ACTION);
        startService(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Message event){
        String s=event.getMessage();
        tv.setText(s);
        Log.d("TAGS", "data onMessage: " + s);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}



