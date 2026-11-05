package org.example;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService agendarTrabalhadores = Executors.newScheduledThreadPool(2);

        ScheduledFuture<?> atividade = agendarTrabalhadores.scheduleWithFixedDelay(() ->{
            try {//Boa prática, tratar execeções dentro do método scheduleWithFixedDelay
                System.out.println("A temperatura atual do seu computador é de "+ new Random().nextInt(20,80)+" graus célsius.");
            }catch (Exception e){
                System.out.println("Falha na ativida! "+e.getMessage());
            }
        },1,3, TimeUnit.SECONDS);

        try {
            Thread.sleep(12000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Desligando atividade recorrente...");
        atividade.cancel(false);
        System.out.println("Desligando seu Schedule Pool..");
        agendarTrabalhadores.shutdown();
    }
}