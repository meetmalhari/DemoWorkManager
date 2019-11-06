package com.app4you.demoworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    PeriodicWorkRequest mPeriodicWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder()
                .putString(MyWorker.TASK_DESC, "The task data passed from MainActivity")
                .build();

        Constraints constraints = new Constraints.Builder()
//                .setRequiresCharging(true)
                .build();


        mPeriodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class,
                15, TimeUnit.MINUTES)
                .addTag("periodicWorkRequest")
                .setInputData(data)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(MainActivity.this).enqueue(mPeriodicWorkRequest);

    }
}
