package linked_list.__test__;

import linked_list.LinkedList;
import linked_list.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListTest {

    private final Logger logger = LoggerFactory.getLogger(LinkedListTest.class);

    @Test
    public void test_linked_list() {
        List<String> list = new LinkedList<>();
        // add
        list.add("a");
        list.addFirst("b");
        list.addLast("c");
        list.printLinkList();

        // remove
        list.remove("b");
        list.printLinkList();
    }
}
