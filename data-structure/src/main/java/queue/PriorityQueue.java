package queue;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class PriorityQueue<E> implements Queue<E> {
    private Logger logger = LoggerFactory.getLogger(PriorityQueue.class);

    private static final int DEFAULT_CAPACITY = 11;

    transient Object[] queue;

    private int size = 0;

    public PriorityQueue() {
        queue = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        size = i + 1;
        if (i == 0) {
            queue[0] = e;
        } else {
            siftUp(i, e);
        }
        return true;
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double if small; else up by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - (Integer.MAX_VALUE - 8) > 0) {
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8) ?
                    Integer.MAX_VALUE :
                    Integer.MAX_VALUE - 8;
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void siftUp(int k, E e) {
        siftUpComparable(k, e);
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E e) {
        Comparable<? super E> key = (Comparable<? super E>) e;
        logger.info("[入队] 元素：{} 当前队列：{}", JSON.toJSONString(key), JSON.toJSONString(queue));
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            logger.info("[入队] 寻找父节点位置 k：{} parent：{}", JSON.toJSONString(k), JSON.toJSONString(parent));
            Object e_parent = queue[parent];
            if (key.compareTo((E) e_parent) >= 0) {
                logger.info("[入队] 值比对：父节点：{} 当前节点：{}", JSON.toJSONString(e_parent), JSON.toJSONString(key));
                break;
            }
            logger.info("[入队] 节点替换，继续循环：父节点值：{} 替换至：{}", JSON.toJSONString(e_parent), JSON.toJSONString(k));
            queue[k] = e_parent;
            k = parent;
        }
        queue[k] = key;
        logger.info("[入队] 完成：Idx：{} Val：{} \r\n 当前队列：{} \r\n", k, JSON.toJSONString(key), JSON.toJSONString(queue));
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        int i = --size;
        E result = (E) queue[0];
        E x = (E) queue[i];
        queue[i] = null;
        if (i != 0) {
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, E x) {
        siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            // 左右节点比较，取最小值
            if (right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0) {
                logger.info("[出队] 左右节点比对，取最小值。left: {} right: {}", JSON.toJSONString(c), JSON.toJSONString(queue[right]));
                c = queue[child = right];
            }
            // 目标值小于child，位置正确，迁移完成
            if (key.compareTo((E) c) <= 0) {
                break;
            }
            // 目标值大于child，替换位置，继续循环
            logger.info("[出队] 替换过程 上节点：{} 下节点：{} 位置替换", JSON.toJSONString(queue[k]), JSON.toJSONString(c));
            queue[k] = c;
            k = child;
        }
        // 把目标值放到对应位置
        logger.info("[出队] 替换结果，最终更换位置。Idx：{} Val：{}", k, JSON.toJSONString(key));
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
}
