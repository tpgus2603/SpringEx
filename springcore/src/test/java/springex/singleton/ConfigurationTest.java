package springex.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springex.AppConfig;
import springex.member.MemberRepository;
import springex.member.MemberServiceImple;
import springex.order.OrderServiceImpl;

public class ConfigurationTest {

    @Test
    void configTest(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImple memberService =ac.getBean("memberService", MemberServiceImple.class);
        OrderServiceImpl orderService =ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository1=memberService.getMemberRepository();
        MemberRepository memberRepository2=orderService.getMemberRepository();
        MemberRepository memberRepository3 = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("member1: "+memberRepository1);
        System.out.println("member2: "+memberRepository2);
        System.out.println("member3: "+memberRepository3);
    }
    @Test
    void configDeep(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig= ac.getBean(AppConfig.class);
        MemberServiceImple bean2=ac.getBean("memberService", MemberServiceImple.class);
        System.out.println("bean2: "+bean2.getClass());
        System.out.println("bean = "+appConfig.getClass());
    }
}
