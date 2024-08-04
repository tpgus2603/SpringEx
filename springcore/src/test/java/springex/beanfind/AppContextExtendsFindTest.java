package springex.beanfind;

import springex.discount.DiscountPolicy;
import springex.discount.FixDiscountPolicy;
import springex.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AppContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);

    @Test
    @DisplayName("부모타입으로 조회 : -> 오류")
    void findParentBean() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("특정 하위타입 빈으로 조회")
    void findBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 조회")
    void findAllParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key+ = " + key +" value= "+beansOfType.get(key));
        }
    }


    @Configuration
    static class Testconfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return  new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDisCountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
