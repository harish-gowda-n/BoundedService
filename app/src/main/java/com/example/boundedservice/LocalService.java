package com.example.boundedservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {
    IBinder iBinder = new InnerClass();
    Random ob = new Random();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
    public  class InnerClass extends Binder {
        LocalService getService(){
            return LocalService.this;
        }
    }
    public int getRandom(){
        return ob.nextInt(100);
    }
}
