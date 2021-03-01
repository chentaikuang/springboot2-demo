package xiaochen.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import xiaochen.param.Params;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TestService {

    //static 供所有线程共享，达到所有线程互斥目的、安全
    private static Map<String, String> concurMap = new ConcurrentHashMap<>();

    static {
        concurMap.put(RandomStringUtils.randomAlphabetic(3), RandomStringUtils.randomAlphabetic(8));
        concurMap.put(RandomStringUtils.randomAlphabetic(3), RandomStringUtils.randomAlphabetic(8));
        concurMap.put(RandomStringUtils.randomAlphabetic(3), RandomStringUtils.randomAlphabetic(8));
        System.out.println(JSONObject.toJSONString(concurMap));
    }

    private static String LOCK = new String();

    public int test() {
        String str;
//        synchronized (LOCK) {
//        synchronized (TestService.class) {
        forInfo();
//        }
        return 1;
    }

    private static synchronized void forInfo() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(DateFormatUtils.format(new Date(), "hh:MM:ss  ")
                        + Thread.currentThread().getName() + ":" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int test2() {
        String str;
//        synchronized (LOCK) {
//        synchronized (TestService.class) {
        //str = printStr();
        forInfo();
//        }

        return 1;
    }

    private String printStr() {
        String str;
        str = RandomStringUtils.randomAlphanumeric(5);
        System.out.println(DateFormatUtils.format(new Date(), "hh:MM:ss") + ",进来2-" + str);
        System.out.println(DateFormatUtils.format(new Date(), "hh:MM:ss") + ",返回2-" + str + "\n");
        return str;
    }

    public String getByKey(String key) {
        System.out.println("进.MAP_SIZE:" + concurMap.size() + "," + JSONObject.toJSONString(concurMap));
        //synchronized 锁定相同的共享对象concurMap，达成互斥
//        synchronized (concurMap) {
//            concurMap.put(RandomStringUtils.randomNumeric(3), RandomStringUtils.randomNumeric(6));
//            System.out.println("出.MAP_SIZE:" + concurMap.size() + JSONObject.toJSONString(concurMap));
//        }
        return concurMap.containsKey(key) ? concurMap.get(key) : "NO_FOUND";
    }

    public String lockMap(String key) {
        synchronized (concurMap) {
            sleep(20000);
        }
        String Return = "LOCK_RETURN:" + key;
        System.out.println(Return);
        return Return;
    }

    public String del(Params param) {
        System.out.println(JSONObject.toJSONString(param));
        return "OK";
    }
}
