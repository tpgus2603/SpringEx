package hello.simplecrud.repository;

import hello.simplecrud.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; //엔티티매니져 주입받기

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member =em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByname(String name) { //pk기반이 아닌경우
        List<Member> result=em.createQuery("select m from Member m where m.name= :name",Member.class).setParameter("name",name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //객체를 대상으로 queury -> 객체를 선택
        return em.createQuery("select m from Member m",Member.class).
                getResultList();
    }




}
