package springex.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springex.annotation.MainDiscountPolicy;
import springex.discount.DiscountPolicy;
import springex.member.Member;
import springex.member.MemberRepository;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private  final MemberRepository memberRepository;
    private   final DiscountPolicy discountPolicy ;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository)
//    {
//        System.out.println("memberRepository ="+memberRepository);
//        this.memberRepository=memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy)
//    {
//        System.out.println("discountPolicy  = "+discountPolicy);
//        this.discountPolicy=discountPolicy;
//    }

    @Autowired//스프링이 자동으로 생성자를 통해 의존성 주입을 해줌 컨테이너에 등록된 빈에서 가져옴
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override

    public Order createOrder(Long memberId, String itemname, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemname, itemPrice, discountPrice);
    }
    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
