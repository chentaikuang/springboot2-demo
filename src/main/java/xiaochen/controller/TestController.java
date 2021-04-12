package xiaochen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import xiaochen.common.CommonResult;
import xiaochen.param.Params;
import xiaochen.service.TestService;
import xiaochen.util.Loggers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/test")
@RestController
public class TestController {

    @Value("${server.port}")
    private int port;

    @Autowired
    private TestService testService;

    //http://localhost:8877/api/test/tips
    @RequestMapping("/tips")
    public String tips() {
        Loggers.CONTROLLER_TEST.error("===============>>");
        return "hi," + port;
    }

    //http://localhost:8877/api/test/returnReq
    @RequestMapping("/returnReq")
    public String returnReq(HttpServletResponse response, HttpServletRequest servlet) {
        SEEController seeController = new SEEController();
        return seeController.push();
    }

    //http://localhost:8877/api/test/lock
    @RequestMapping("/lock")
    public String lock(HttpServletResponse response, HttpServletRequest servlet) {
        int nm = 5;
        while (nm-- > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testService.test();
                }
            }).start();
        }
        return "1";
    }

    //http://localhost:8877/api/test/lock2
    @RequestMapping("/lock2")
    public int lock2(HttpServletResponse response, HttpServletRequest servlet) {
        return testService.test2();
    }

    //http://localhost:8877/api/test/getStr?key=DJv
    @RequestMapping("/getStr")
    public String getStr(@RequestParam("key") String key) {
        return testService.getByKey(key);
    }

    //http://localhost:8877/api/test/lockMap?key=DJv
    @RequestMapping("/lockMap")
    public String lockMap(@RequestParam("key") String key) {
        return testService.lockMap(key);
    }


    //http://localhost:8877/api/test/del
    @DeleteMapping("/del")
    public String del(@RequestBody Params param) {
        return testService.del(param);
    }

    //http://localhost:8877/api/test/complateTabFuture?p=XX
    @GetMapping("/complateTabFuture")
    public CommonResult complateTabFuture(@RequestParam(defaultValue = "P") String p) throws ExecutionException, InterruptedException {
        return testService.complateTabFuture(p);
    }

    //http://localhost:8877/api/test/futureJoin?p=XX
    @GetMapping("/futureJoin")
    public CommonResult futureJoin(@RequestParam(defaultValue = "P") String p) throws ExecutionException, InterruptedException {
        return testService.futureJoin(p);
    }

    //http://localhost:8877/api/test/nc
    @GetMapping("/nc")
    public CommonResult nc(@RequestParam String sessionId, @RequestParam String sig, @RequestParam String token) {
        return testService.checkNoCaptcha(sessionId, sig, token);
    }
}
