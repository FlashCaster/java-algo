package linked_list;

/*
 * 结点结构: pre next val
 * 链表结构：size first last
 * 增(头插/尾插)
 * 删
 * 查
 * 改
 * 打印
 */

public class LinkedList<E> implements List<E> {
    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;

    void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<E>(e, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(e, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    E unlink(Node<E> x) {
        final E e = x.item;
        final Node<E> prev = x.prev;
        final Node<E> next = x.next;

        // node left
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        // node right
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size++;
        return e;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public void printLinkList() {
        if (this.size == 0) {
            System.out.print("Empty list");
        } else {
            Node<E> tmp = this.first;
            System.out.print("Current List, head: " + first.item + " tail: " + last.item + " size: " + size + " total: ");
            while (tmp != null) {
                System.out.print(tmp.item + " ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    }



    //Node Structure
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
