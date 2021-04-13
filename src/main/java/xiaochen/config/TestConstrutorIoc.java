package xiaochen.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import xiaochen.service.TestService;

@Component
public class TestConstrutorIoc implements InitializingBean {
    private TestService testService;

    public TestConstrutorIoc(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestConstrutorIoc -->> " + this.testService.getClass());
        this.testService.test();
    }
}
