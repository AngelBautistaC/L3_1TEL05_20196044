package com.example.l3_1tel05_20196044;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.l3_1tel05_20196044.workers.CronometroWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CronometroActivity extends AppCompatActivity {
    private ExecutorService executorService;
    private boolean running = false;
    private boolean paused = false;
    private int counter = 0;

    private TextView tvCronometro;
    private Button btnStart;
    private Button btnPause;
    private Button btnResume;
    private Button btnClear;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (running && !paused) {
                counter++;
                viewModel.getContador().postValue(counter);
            }
            updateTextView();
            handler.postDelayed(this, 1000);
        }
    };

    private CronometroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        ApplicationThreads application = (ApplicationThreads) getApplication();
        executorService = application.executorService;

        viewModel = new ViewModelProvider(this).get(CronometroViewModel.class);

        tvCronometro = findViewById(R.id.tvCronometro);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnClear = findViewById(R.id.btnClear);

        viewModel.getContador().observe(this, contador -> {
            updateTextView();
        });

        btnStart.setOnClickListener(v -> {
            if (!running) {
                running = true;
                paused = false;
                handler.postDelayed(runnable, 1000);
            }
        });

        btnPause.setOnClickListener(v -> {
            paused = true;
        });

        btnResume.setOnClickListener(v -> {
            if (running && paused) {
                paused = false;
            }
        });

        btnClear.setOnClickListener(v -> {
            running = false;
            paused = false;
            counter = 0;
            viewModel.getContador().postValue(counter);
            updateTextView();
            // Remove callbacks to prevent speeding up when starting again
            handler.removeCallbacksAndMessages(null);
        });
    }

    private void updateTextView() {
        int hours = counter / 3600;
        int minutes = (counter % 3600) / 60;
        int seconds = counter % 60;
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        tvCronometro.setText(formattedTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks when the activity is destroyed
        handler.removeCallbacksAndMessages(null);
    }
}

