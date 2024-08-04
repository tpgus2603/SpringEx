package springex.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springex.AppConfig;
import springex.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {
    @Test
    @DisplayName("순수 Di")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        //참조값이 다름
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        assertThat(memberService1).isNotEqualTo(memberService2);
    }
    @Test
    @DisplayName("싱글톤 패턴 적용")
    void singletonServiceTest(){
        SingletonService singletonService1= SingletonService.getInstance();
        SingletonService singletonService2=SingletonService.getInstance();
        System.out.println("sing 1:"+singletonService1);
        System.out.println("sing 2:"+singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);
    }
    @Test
    @DisplayName("순수 Di")
    void springContainer() {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1=ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);


        assertThat(memberService1).isSameAs(memberService2);
    }

    
}
