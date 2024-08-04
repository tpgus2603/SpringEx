package hello.simplecrud.repository;

import hello.simplecrud.domain.Member;

import java.util.*;


public class MemoryMemberRepository  implements MemberRepository{
    private static Map<Long, Member> store =new HashMap<>(); //저장소
    private static long sequence =0;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        System.out.println((sequence));
        store.put(member.getId(),member); //맵에 키 밸류 넣기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //옵셔널로 감싸서 널 포인터 대처
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream().filter(member->member.getName().equals(name)).findAny();//필터로 이름 같은 밸류들 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clear(){
        store.clear();
    }
}
