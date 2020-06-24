package com.arcta.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Timing {

    static Map<String, Long> map = new HashMap<>();

    public static boolean OUTPUT_TIMING = false;

    static Long nano_time__previous = null;

    public static void time_and_restart(String description) {
        time(description);
        restart();
    }

    public static void time_between(String name){
        name = name + Thread.currentThread().toString();
        if (map.containsKey(name)){
            System.out.println(
                    new BigDecimal((System.nanoTime() - map.get(name))/ 1000000000d,
                            new MathContext(2, RoundingMode.DOWN)).toPlainString() +
                            "s " + name);
            map.remove(name);
        } else {
            map.put(name, System.nanoTime());
        }
    }

    public static void time(String description){
        if (OUTPUT_TIMING) {
            if (nano_time__previous != null) {
                System.out.println(
                        new BigDecimal((System.nanoTime() - nano_time__previous)/ 1000000000d,
                                new MathContext(2, RoundingMode.DOWN)).toPlainString() +
                        "s " + description);
            } else {
                System.out.println(description);
            }
            nano_time__previous = System.nanoTime();
        }
    }

    public static void restart() {
        if (OUTPUT_TIMING) {
            nano_time__previous = System.nanoTime();
        }
    }
}
