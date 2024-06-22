package stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.toBinaryString;

public class ArrayDeque<E> implements Deque<E> {
    private final Logger logger = LoggerFactory.getLogger(ArrayDeque.class);

    /**
     * 存储双端队列元素的数组。双端队列的容量就是这个数组的长度，它总是 2 的幂。
     * 数组永远不允许变满，除非在 addX 方法中短暂地在变满后立即调整大小（请参阅 doubleCapacity），
     * 从而避免头部和尾部环绕以彼此相等。我们还保证所有不包含双端队列元素的数组单元始终为空。
     */
    transient Object[] elements;

    /**
     * 队列的头索引。remove()或pop()时调用
     */
    transient int head;

    /**
     * 队列的尾。下一个元素添加到队列尾部的索引。add()，push()时调用
     */
    transient int tail;

    /**
     * 构造一个初始队列，容量为16
     * 为方便测试改为2
     */
    public ArrayDeque() {
        elements = new Object[2];
    }

    // neet to implement
    /**
     * 双端队列容量翻倍。仅当头部和尾部相等时调用
     */
    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Deque too big");
        }
        Object[] a = new Object[newCapacity];

        /**
         * src      - 源数组
         * srcPos   - 源数组中的起始位置
         * dest     - 目标数组
         * destPos  - 目标数组的起始位置
         * length   - 复制的元素数量
         */

        // 第一次拷贝：将数组中的扩容后一半元素拷贝到新数组0开始往后的位置
        System.arraycopy(elements, p, a, 0, r);
        // 第二次拷贝：将数组中的前面一半数量的元素，拷贝到新数组后一半开始的位置往后
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    @Override
    public void push(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[head = (head - 1) & (elements.length - 1)] = e;
        logger.info("push.idx head: {}", head);
        if (head == tail) {
            doubleCapacity();
        }
    }

    @Override
    public E pop() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E) elements[h];
        if (result == null) {
            return null;
        }
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        logger.info("pop.idx {} = {} & {}", head, toBinaryString(h + 1), toBinaryString(elements.length - 1));
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
