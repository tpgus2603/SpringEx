package springex.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;
public class SingletonWithPrototypeTest1 {
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
    static class ClientBean {

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); //새로운 prototype을  스프링 컨테이너에서 가져옴(내부에서 주입 DL)
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Component
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

//        private final PrototypeBean prototypeBean;
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) { //생성자 호출시에만 프로토타입 생성
//            this.prototypeBean = prototypeBean;
//        }
//        public int logic() {
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
