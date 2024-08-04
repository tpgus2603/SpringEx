package springex;

import org.springframework.context.annotation.Primary;
import springex.discount.DiscountPolicy;
import springex.discount.FixDiscountPolicy;
import springex.discount.RateDiscountPolicy;
import springex.member.MemberService;
import springex.member.MemberServiceImple;
import springex.member.MemoryMemberRepository;
import springex.order.OrderService;
import springex.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



//call AppConfig.memberService
//call AppConfig.memberRepository
//call AppConfig.orderService`

@Configuration //app의 설정정보를 담당하는 클래스
public class AppConfig { //애플리케이션의 실제 동작에 필요한 구현객체를 생성

    @Bean //스프링 컨테이너에 등록(메소드를 통해 등록함)
    public MemberService memberService(){ //생성자 의존성 주입
        System.out.println(" call AppConfig.memberService");
        return new MemberServiceImple(memberRepository());
    }

    @Bean
    public  MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}


//public MemberService memberService(){ //생성자 의존성 주입
//    return new MemberServiceImple(memberRepository());
//}
//
//public  MemoryMemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//}
//
//public OrderService orderService(){
//    return new OrderServiceImpl(memberRepository(), discountPolicy());
//}
//
//public DiscountPolicy discountPolicy(){
//    //return new FixDiscountPolicy();
//    return new RateDiscountPolicy();
//}