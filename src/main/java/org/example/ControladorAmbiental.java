package org.example;

import org.example.BufferTemperatura;

import java.util.Random;

public class ControladorAmbiental extends Thread {

    private final BufferTemperatura buffer;
    private final int umbral;
    private final Random random = new Random();

    public ControladorAmbiental(BufferTemperatura buffer, int umbral) {
        this.buffer = buffer;
        this.umbral = umbral;
    }

    @Override
    public void run() {
        int contador = 0;
        int temperatura;
        while ((temperatura = buffer.leer()) != -1) {
            contador++;
            if (temperatura > umbral) {
                System.out.printf("[CONTROL] lee medicion %02d: %d°C -> EXCEDE EL UMBRAL de %d°C, VENTILACION ACTIVADA%n",
                        contador, temperatura, umbral);
            } else {
                System.out.printf("[CONTROL] lee medicion %02d: %d°C -> en rango normal, todo correcto%n",
                        contador, temperatura);
            }
            try {
                Thread.sleep(100 + random.nextInt(400));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[CONTROL] fin del monitoreo");
    }
}
