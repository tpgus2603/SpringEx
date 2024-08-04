package springex.benedefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springex.AppConfig;

public class BeneDefinitionTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 메타설정 정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName+" beanDefinition= "+beanDefinition);
            }

        }
    }
}
