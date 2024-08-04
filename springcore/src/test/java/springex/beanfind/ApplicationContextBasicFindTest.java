package springex.beanfind;

import springex.AppConfig;
import springex.member.MemberService;
import springex.member.MemberServiceImple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈이름으로 조회")
    void findBeanByname(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("구체 타입 으로 조회")
    void findBeanByname2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImple.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("빈이름으로 조회x")
    void findBeanByNameX()
    {
        assertThrows(NoSuchBeanDefinitionException.class,()->ac.getBean("xxxxx", MemberService.class));
    }


}
