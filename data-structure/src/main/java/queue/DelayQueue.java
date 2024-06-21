package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class DelayQueue<E extends Delayed> implements BlockingQueue<E>{
    private final transient ReentrantLock lock = new ReentrantLock();

    private final PriorityQueue<E> queue = new PriorityQueue<>();

    private final Condition avaliable = lock.newCondition();

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            queue.offer(e);
            if (queue.peek() == e) {
                avaliable.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E first = queue.peek();
            if (first == null || first.getDelay(NANOSECONDS) > 0) {
                return null;
            } else {
                return queue.poll();
            }
        } finally {
            lock.unlock();
        }
    }

    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return queue.peek();
        } finally {
            lock.unlock();
        }
    }
}
