package stack.__test__;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stack.ArrayDeque;
import stack.Deque;

public class StackTest {
    private final Logger logger = LoggerFactory.getLogger(StackTest.class);

    @Test
    public void test_stack() {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        logger.info("弹出元素：{}", stack.pop());
        logger.info("弹出元素：{}", stack.pop());
        logger.info("弹出元素：{}", stack.pop());
        logger.info("弹出元素：{}", stack.pop());
        logger.info("弹出元素：{}", stack.pop());
        logger.info("弹出元素：{}", stack.pop());
    }

}
