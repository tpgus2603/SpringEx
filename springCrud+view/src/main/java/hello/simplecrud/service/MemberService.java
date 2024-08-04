package hello.simplecrud.service;

import hello.simplecrud.domain.Member;
import hello.simplecrud.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

     // 의존성이 주입된 멤버 레퍼지토리를 스프링에서 찾아서 넣어줌
    public MemberService(MemberRepository meme)
    {
        this.memberRepository=meme;
    }

    //회원가입

    public Long join(Member member)
    {
        Dupmember(member);
        memberRepository.save(member);
         return member.getId();

    }

    private void Dupmember(Member member) {
        memberRepository.findByname(member.getName()).ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니다");});
    }

    //전체 멤버조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
