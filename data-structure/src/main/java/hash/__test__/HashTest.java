package hash.__test__;

import hash.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashTest {
    private final Logger logger = LoggerFactory.getLogger(HashTest.class);

    @Test
    public void test_hashMap01() {
        Map<String, String> map = new HashMap01<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key:{}, value:{}", "01", map.get("01"));

        // 碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key:{}, value:{}", "01", map.get("01"));
    }

    @Test
    public void test_hashMap02() {
        Map<String, String> map = new HashMap02BySeperateChaining<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key:{}, value:{}", "01", map.get("01"));

        // 碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key:{}, value:{}", "01", map.get("01"));
    }

    @Test
    public void test_hashMap03() {
        Map<String, String> map = new HashMap03ByOpenAddressing<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key:{}, value:{}", "01", map.get("01"));

        // 碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key:{}, value:{}", "01", map.get("01"));

        logger.info("数据结构：{}", map);
    }

    @Test
    public void test_hashMap04() {
        Map<String, String> map = new HashMap04ByCoalescedHashing<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key:{}, value:{}", "01", map.get("01"));

        // 碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key:{}, value:{}", "01", map.get("01"));

        logger.info("数据结构：{}", map);
    }

    @Test
    public void test_hashMap05() {
        java.util.Map<String, String> map = new HashMap05ByCuckooHashing<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key:{}, value:{}", "01", map.get("01"));

        // 碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞后 key:{}, value:{}", "01", map.get("01"));

        logger.info("数据结构：{}", map);
    }
}
