package hello.simplecrud.service;

import hello.simplecrud.domain.Member;
import hello.simplecrud.repository.MemberRepository;
import hello.simplecrud.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest//스프링 컨테이너와 함께테스트실행
@Transactional //테스트케이스에 넣은 데이터 롤백

public class IntegrationTest {
    @Autowired MemberRepository memberRepository;
    @Autowired
    MemberService memberService ;

    @Test
    void 회원가입() {
        //given
        Member member=new Member();
        member.setName("spring");

        //when
        Long saveId=memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외(){
        Member member1=new Member();
        member1.setName("spring");
        Member member2 =new Member();
        member2.setName("spring");


        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        //Assertions.assertThrows
    }



}
