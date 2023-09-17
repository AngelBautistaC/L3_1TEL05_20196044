package com.example.l3_1tel05_20196044.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.l3_1tel05_20196044.CronometroManager;
import com.example.l3_1tel05_20196044.CronometroViewModel;

public class CronometroWorker extends Worker {
    public CronometroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        for (int i = 1; i <= 9000; i++) {
            CronometroManager.getInstance().setContador(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return Result.failure();
            }
        }
        return Result.success();
    }
}

