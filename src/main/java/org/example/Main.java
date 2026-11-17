package org.example;

import org.example.BufferTemperatura;
import org.example.ControladorAmbiental;

public class Main {

    private static final int UMBRAL = 28;
    private static final int MEDICIONES = 12;

    public static void main(String[] args) {
        System.out.println("=== Monitorizacion ambiental del invernadero (productor/consumidor) ===");
        System.out.println("Espera activa: el sensor no sobrescribe hasta que el controlador ha leido.");
        System.out.println("Umbral de ventilacion: " + UMBRAL + "°C | mediciones: " + MEDICIONES);
        System.out.println();

        BufferTemperatura buffer = new BufferTemperatura();
        SensorTemperatura sensor = new SensorTemperatura(buffer, MEDICIONES);
        ControladorAmbiental controlador = new ControladorAmbiental(buffer, UMBRAL);

        sensor.start();
        controlador.start();

        try {
            sensor.join();
            controlador.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
