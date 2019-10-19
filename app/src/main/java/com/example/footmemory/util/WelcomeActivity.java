package com.example.footmemory.util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.footmemory.MainActivity;
import com.example.footmemory.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getSupportActionBar().hide();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                //实现页面的跳转
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                super.handleMessage(msg);
            }
        };
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);//设置延迟时间
    }
}
