package xiaochen.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import xiaochen.common.CommonResult;
import xiaochen.param.Params;
import xiaochen.util.DateUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@Service
public class TestService {

    @Resource
    ThreadPoolTaskExecutor taskExecutor;

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
                System.out.println(DateFormatUtils.format(new Date(), "HH:mm:ss  ")
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

    public CommonResult complateTabFuture(String p) throws ExecutionException, InterruptedException {
        CompletableFuture<CommonResult> futureRst = null;
        if (StringUtils.isBlank(p) || "0".equalsIgnoreCase(p)) {
            CompletableFuture.runAsync(() -> {
                infoMsg("runAsync");
            }, taskExecutor);
            futureRst = new CompletableFuture<>();
            futureRst.complete(new CommonResult("runAsync"));
        } else {
            futureRst = CompletableFuture.supplyAsync(() -> {
                infoMsg("supplyAsync");
                CommonResult result = new CommonResult("supplyAsync");
                return result;
            }, taskExecutor);
        }


        CompletableFuture<Void> T1 = CompletableFuture.runAsync(() -> {
            try {
                int n = 10;
                int xx = n / 0;
            } catch (Exception e) {
                errMsg("T1(" + e.getMessage() + ")");
                throw new CompletionException(e);
            }
        }, taskExecutor);
        CompletableFuture<Void> T2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                infoMsg("T2");
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, taskExecutor);

        return futureRst.get();
    }

    private void infoMsg(String msg) {
        System.out.println(DateUtil.curDate() + " -> " + msg);
    }

    private void errMsg(String msg) {
        System.err.println(DateUtil.curDate() + " -> " + msg);
    }

    public CommonResult futureJoin(String p) throws ExecutionException, InterruptedException {
        System.out.println(DateUtil.curDate());
        CompletableFuture<CommonResult> t1 = CompletableFuture.supplyAsync(() -> {
            try {
                infoMsg("T1");
                return new CommonResult("T1");
            } catch (Exception e) {
                errMsg("T1(" + e.getMessage() + ")");
                throw new CompletionException(e);
            }
        }, taskExecutor);
        CompletableFuture<CommonResult> t2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                infoMsg("T2");
                return new CommonResult("T2");
            } catch (Exception e) {
                errMsg("T2(" + e.getMessage() + ")");
                throw new CompletionException(e);
            }
        }, taskExecutor);
        CompletableFuture.allOf(t1, t2).join();
        System.out.println(DateUtil.curDate());
        return new CommonResult(t1.get().getMsg() + " || " + t2.get().getMsg());
    }
}
