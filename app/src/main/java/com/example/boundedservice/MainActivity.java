package com.example.boundedservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b;
    TextView tv;
    LocalService localService;
    boolean bound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.b);
        tv = findViewById(R.id.ouput);
        b.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent ob = new Intent(this, LocalService.class);
        bindService(ob, con, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(con);
        bound = false;
    }

    @Override
    public void onClick(View v) {
        if(bound){
            tv.setText(""+localService.getRandom());
            Toast.makeText(this, ""+localService.getRandom(), Toast.LENGTH_LONG).show();
            Log.i("Random Number : ", ""+localService.getRandom());
        }
    }

    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.InnerClass binder = (LocalService.InnerClass) service;
            localService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };
}
