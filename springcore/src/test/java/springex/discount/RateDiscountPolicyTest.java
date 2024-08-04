package springex.discount;

import springex.member.Grade;
import springex.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy=new RateDiscountPolicy();
    @Test
    @DisplayName("vip는 10프로 할인이 되어야한다")
    void vipT(){
        Member member=new Member(1L,"memberVip", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("vip가 아니면 할인이 적용되면 안된다")
    void vipX(){
        Member member=new Member(1L,"memberVip", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(0);
    }

}