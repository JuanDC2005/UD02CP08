package org.example;

import org.example.BufferTemperatura;

import java.util.Random;

public class SensorTemperatura extends Thread {

    private final BufferTemperatura buffer;
    private final int numMediciones;
    private final Random random = new Random();

    public SensorTemperatura(BufferTemperatura buffer, int numMediciones) {
        this.buffer = buffer;
        this.numMediciones = numMediciones;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numMediciones; i++) {
            int temperatura = 15 + random.nextInt(21);
            buffer.publicar(temperatura);
            System.out.printf("[SENSOR]  publica medicion %02d: %d°C%n", i, temperatura);
            try {
                Thread.sleep(100 + random.nextInt(400));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        buffer.cerrar();
        System.out.println("[SENSOR]  fin de las mediciones, buffer cerrado");
    }
}
