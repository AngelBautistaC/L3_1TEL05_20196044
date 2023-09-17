package com.example.l3_1tel05_20196044;

public class ContadorManager {
    private static ContadorManager instance;
    private int contador = 104; // Valor inicial del contador

    private ContadorManager() {
        // Constructor privado para evitar instancias múltiples
    }

    public static ContadorManager getInstance() {
        if (instance == null) {
            instance = new ContadorManager();
        }
        return instance;
    }

    public int getContador() {
        return contador;
    }

    public void incrementarContador() {
        if (contador < 226) {
            contador++;
        } else {
            // Vibrar o sonar una alarma
            // ... (su lógica aquí)
        }
    }
}
