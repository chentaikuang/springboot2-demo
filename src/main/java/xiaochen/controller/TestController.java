package xiaochen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/test")
@RestController
public class TestController {

    @Value("${server.port}")
    private int port;

    //http://localhost:8877/api/test/tips
    @RequestMapping("/tips")
    public String tips() {
        return "hi," + port;
    }

    //http://localhost:8877/api/test/returnReq
    @RequestMapping("/returnReq")
    public String returnReq(HttpServletResponse response,HttpServletRequest servlet) {
        SEEController seeController = new SEEController();
        return seeController.push();
    }

}
