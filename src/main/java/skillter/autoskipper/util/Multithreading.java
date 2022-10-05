package skillter.autoskipper.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Multithreading {

    private static AtomicInteger threadCounter = new AtomicInteger(0);

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(20, r -> new Thread(r, "Thread " + threadCounter.incrementAndGet()));


    public static ScheduledFuture<?> schedule(Runnable r, long delay, TimeUnit timeUnit) {
        return executor.schedule(r, delay, timeUnit);
    }

    public static ScheduledFuture<?> schedule(Runnable r, long initialDelay, long delay, TimeUnit timeUnit) {
        return executor.scheduleAtFixedRate(r, initialDelay, delay, timeUnit);
    }

    public static void runAsync(Runnable r)  {
        executor.execute(r);
    }

}
