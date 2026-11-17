package org.example;

public class BufferTemperatura {

    private volatile int temperatura;
    private volatile boolean disponible = false;
    private volatile boolean cerrado = false;

    public void publicar(int valor) {
        while (disponible) {
            Thread.yield();
        }
        temperatura = valor;
        disponible = true;
    }

    public int leer() {
        boolean avisado = false;
        while (!disponible) {
            if (cerrado) {
                return -1;
            }
            if (!avisado) {
                System.out.println("[CONTROL] esperando medicion...");
                avisado = true;
            }
            Thread.yield();
        }
        int valor = temperatura;
        disponible = false;
        return valor;
    }

    public void cerrar() {
        cerrado = true;
    }
}
