package springex.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImple implements MemberService{

    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImple(MemberRepository memberRepository) { //추상화에만 의존하기 위해 생성자를 통해 추상화만 이용해서 객체 주입 , 현체는 클래스에 존재 x
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
