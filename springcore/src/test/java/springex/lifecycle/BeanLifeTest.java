package springex.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeTest {

    @Test
    public void test() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client=ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod = "init")   // 빈이 등록될때 inti메소드 호출 , 소멸될때 destroymethod호출(따로지정안해도 close 메소드를 자동호출함)
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
