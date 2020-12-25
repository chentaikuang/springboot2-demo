package xiaochen.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestService {

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

    private void sleep() {
        try {
            Thread.sleep(3000);
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
}
