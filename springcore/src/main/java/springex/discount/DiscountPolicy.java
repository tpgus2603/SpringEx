package springex.discount;

import springex.member.Member;

public interface DiscountPolicy {


     /*
       return 할인대상 금액
      */
    int discount(Member member,int price);
}
