package springex.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springex.AutoAppConfig;
import springex.member.MemberService;
import springex.member.MemberServiceImple;
import springex.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;

public class AutoConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
