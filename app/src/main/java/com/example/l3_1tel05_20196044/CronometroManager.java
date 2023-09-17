package com.example.l3_1tel05_20196044;

public class CronometroManager {

    private static final CronometroManager ourInstance = new CronometroManager();

    public static CronometroManager getInstance() {
        return ourInstance;
    }

    private CronometroManager() {
    }

    private int contador = 0;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
