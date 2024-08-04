package springex.order;

import springex.AppConfig;
import springex.member.Grade;
import springex.member.Member;
import springex.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {


    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void before(){
        AppConfig appconfig=new AppConfig();
        memberService=appconfig.memberService();
        orderService=appconfig.orderService();
    }

    @Test
    void 테스트()
    {
        Long memberId=1L;
        Member member =new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);
        Order order =orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
