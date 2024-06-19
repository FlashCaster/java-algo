package linked_list;

public interface List<E> {

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(E e);

    E get(int index);

    void printLinkList();

}
