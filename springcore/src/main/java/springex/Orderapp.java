package springex;

import springex.member.Grade;
import springex.member.Member;
import springex.member.MemberService;
import springex.order.Order;
import springex.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Orderapp {
    public static void main(String[] args) {
//        AppConfig appconfig= new AppConfig();
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService();
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId=1L;
        Member member =new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);
        Order order  =orderService.createOrder(memberId,"itemA",10000);
        System.out.println("order = "+order);
    }
}
