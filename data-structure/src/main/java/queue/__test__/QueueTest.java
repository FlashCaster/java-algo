package queue.__test__;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queue.DelayQueue;
import queue.Delayed;
import queue.Queue;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    private final Logger logger = LoggerFactory.getLogger(QueueTest.class);

    @Test
    public void test_queue() throws InterruptedException {
        Queue<Job> queue = new DelayQueue<>();


    }

    static class Job implements Delayed {
        private final String name;
        private final Long begin;
        private final Long delayTime;

        public Job(String name, Long delayTime) {
            this.name = name;
            this.begin = System.currentTimeMillis();
            this.delayTime = delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(begin + delayTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Delayed o) {
            Job job = (Job) o;
            return (int) (this.getDelay(TimeUnit.MICROSECONDS) - job.getDelay(TimeUnit.MICROSECONDS));
        }
    }
}
