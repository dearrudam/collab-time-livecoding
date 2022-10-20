package io.github.dearrudam;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

public class Timed {

    public static void timed(Runnable runnable, Consumer<Duration> consumer){
        var start= Instant.now();
        try{
            runnable.run();
        }finally {
            consumer.accept(Duration.between(start,Instant.now()));
        }
    }
}
