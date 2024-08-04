package springex.beanfind;

import springex.member.MemberRepository;
import springex.member.MemberService;
import springex.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppcontextSameBeanTypeTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("같은 타입의 빈 이 두가지이상 존재->오류발생 ")
    void findBeanByTypeDupe()
    {
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }
    @Test
    @DisplayName("같은 타입의 빈 이 두가지이상 존재->오류발생 ")
    void findBeanByName()
    {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("특정타입 모두 조회하기") //맵으로 반환
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key+"value = "+beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
