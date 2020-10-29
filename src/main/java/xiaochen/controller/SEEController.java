package xiaochen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaochen.util.Loggers;

import java.util.Random;

@RestController
@RequestMapping(value = "/api/test")
public class SEEController {
    //    http://localhost:8877/api/test/push
    //设置响应格式
    @RequestMapping(value = "/push", produces = "text/event-stream;charset=UTF-8")
    public String push() {
        Loggers.CONTROLLER_SEE.warn("=============|||||");
        Random r = new Random();
        //SSE返回数据格式是固定的以data:开头，以\n\n结束
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }
}
