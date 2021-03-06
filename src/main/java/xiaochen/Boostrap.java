package xiaochen;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Boostrap {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(Boostrap.class, args);
    }

    @RequestMapping
    public String defMethod() {
        return "this is default home page:" + port;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setThreadNamePrefix("test_thread_");
        return taskExecutor;
    }
}
