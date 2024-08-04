package hello.simplecrud;


import hello.simplecrud.repository.JpaMemberRepository;
import hello.simplecrud.service.MemberService;
import hello.simplecrud.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//컨테이너에 빈 등록

@Configuration
public class SpringConfig {
//    private final DataSource dataSource; //데이터 커넥션->jdbc템플릿
//    private final EntityManager em; //jpa용

//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public SpringConfig(EntityManager em) { //jpa용
//        this.em = em;
//    }
    private final MemberRepository memberRepository; // 스프링데이타 jpa

//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

   // @Bean
//    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//       // return new JdbcTemplateMemberRepository(dataSource)
//        return new JpaMemberRepository(em);
//    }
}