package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  인터페이스 인터페이스를 상속 받을 때는 implements 가 아닌, extends
//  인터페이스는 다중 상속이 가능하기에 extends 뒤에 여러 객체를 상속한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
