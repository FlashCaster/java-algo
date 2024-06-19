package queue;

public interface Deque<E> extends Queue<E> {

    void addFirst(E e);

    void addLast(E e);
}
