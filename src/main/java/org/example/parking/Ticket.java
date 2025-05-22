package org.example.parking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@AllArgsConstructor
public class Ticket {
    private final Cliente cliente;
    private final Vehiculo vehiculo;
    private final LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Ticket(Cliente cliente, Vehiculo vehiculo) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.horaEntrada = LocalDateTime.now();
    }

    public void marcarSalida() {
        Random random = new Random();
        this.horaSalida = LocalDateTime.now().plusMinutes(random.nextInt(200)+1);
    }

    public long calcularMinutos() {
        return Duration.between(horaEntrada, horaSalida).toMinutes();
    }

    public double calcularPrecio() {
        // TODO implementar el metodo para calcular el importe a abonar segun el tipo de vehiculo
        // AUTO -> 100, SUV -> 130, PICKUP -> 180
        // el importe es por hora redondeando el tiempo hacia arriba,
        // por ejemplo si estuvo 45 minutos se le tarifa por 60, si estuvo 80 minutos se le tarifa por 120 minutos, etc...
        // retornar el importe final
        double duracionMinutos = calcularMinutos();

        double formateoHora = duracionMinutos / 60;


        //lo saque de: https://www.w3schools.com/java/ref_math_round.asp
        int horas = (int)Math.round(formateoHora);

        double importeFinal = 0 ;

        switch (this.vehiculo.getTipo()){
            case SUV:
                importeFinal = horas * 130;
                break;
            case AUTO:
                importeFinal = horas * 100;
                break;
            case PICKUP:
                importeFinal = horas * 180;
                break;
        }

        return importeFinal;
    }

}