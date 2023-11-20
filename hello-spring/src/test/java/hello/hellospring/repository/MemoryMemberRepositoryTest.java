package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get(); // get()이라고하면 Optional 에서 한번 까서 꺼낼 수 있다.

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    /*
    * 모든 테스트는 순서랑 상관없이 메소드별로 다 따로 동작하게만 설계를 해야 된다.
    * 해당 클래스에 있는 모든 테스트를 한 번에 돌리면 findByName()에서 에러가 발생한다.
    * 그 이유는 모든 테스트는 순서랑 상관없이 메소드별로 다 따로 동작하는데
    * findAll()에서 생성된 member1과 findByName()에서 생성된 member1이 같은 객체가 아니기 때문이다.
    * 이름만 member1이라고 명시했을 뿐, 둘 다 새롭게 new Member()이 되어 주소값이 다른 객체이다.
    * 그래서 findByName()에서 Optional().get()를 하게 되면 findAll()에 있는 member1를 불러올수도 있어 에러가 발생하는거다.
    *
    * 따라서 테스트 하나가 끝나고 나면 해당 테스트에 있던 데이터를 클리어 해줘야 한다.
    * */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
}
