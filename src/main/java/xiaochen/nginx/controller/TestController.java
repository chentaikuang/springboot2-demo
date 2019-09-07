package xiaochen.nginx.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@RestController
public class TestController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/tips")
    public String tips() {
        return "hi," + port;
    }

}
