package hello.simplecrud.repository;

import hello.simplecrud.domain.Member;
import hello.simplecrud.repository.MemberRepository;
import hello.simplecrud.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberrepositoryTest {
    MemberRepository repository =new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ //하나의 테스트가 끝날때마다 실행시켜주는 메소드 ->저장소 초기화
        repository.clear();
    }
    @Test
        public void save(){
        Member member =new Member();
        member.setName("spring");

        repository.save(member);
        Member result=repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 =new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByname("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findALl(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result= repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
