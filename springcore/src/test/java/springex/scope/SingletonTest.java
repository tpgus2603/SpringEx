package springex.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    void singletonTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleTon.class);//컨테이너 등록
        SingleTon bean1 = ac.getBean(SingleTon.class);
        System.out.println("bean1 = " + bean1);
        SingleTon bean2 = ac.getBean(SingleTon.class);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isEqualTo(bean2);
        ac.close();
        
    }
    @Scope("singleton")
    static class SingleTon{
        @PostConstruct
        public void init(){
            System.out.println("SingleTon.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingleTon.destroy");
        }
    }
}

