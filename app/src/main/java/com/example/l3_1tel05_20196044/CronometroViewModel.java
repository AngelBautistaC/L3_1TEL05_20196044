package com.example.l3_1tel05_20196044;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CronometroViewModel extends ViewModel {
    private final MutableLiveData<Integer> contador = new MutableLiveData<>();

    public MutableLiveData<Integer> getContador() {
        return contador;
    }

    public void fetchContador() {
        contador.postValue(CronometroManager.getInstance().getContador());
    }
}

