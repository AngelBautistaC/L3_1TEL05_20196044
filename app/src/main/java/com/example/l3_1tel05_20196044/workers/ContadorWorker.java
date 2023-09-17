package com.example.l3_1tel05_20196044.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.l3_1tel05_20196044.ContadorManager;

public class ContadorWorker extends Worker {
    public ContadorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        ContadorManager.getInstance().incrementarContador();
        return Result.success();
    }
}
