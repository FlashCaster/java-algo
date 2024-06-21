package queue;

import java.util.concurrent.TimeUnit;

public interface Delayed extends Comparable<Delayed>{
    long getDelay(TimeUnit unit);
}
