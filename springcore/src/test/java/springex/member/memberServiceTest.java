package springex.member;


import springex.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class memberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();

    }
    @Test
    void join() {
        //given

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        memberService.join(member);
        Member findmember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findmember);
    }
}
