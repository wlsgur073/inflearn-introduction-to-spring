package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //  JPA 는 EntityManager 라는 걸로 모든 게 동작이 된다.
    //  스프링 부트가 자동으로 EntityManager 라는 걸 생성해서 모든 연결 요소들을 만들어준다.
    private final EntityManager em;

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
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
            "select m from Member m"은 jpql 이라는 쿼리이다.
            보통 테이블 대상으로 sql 를 날리는데, jpql 은 entity 대상으로 쿼리를 날린다.
            select 의 m 은 엔티티 자체를 셀렉트 하는 것이며,
            from Member m 의 m 은 alias 로 별칭이다.
        */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
