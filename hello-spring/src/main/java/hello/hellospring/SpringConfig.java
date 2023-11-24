package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    /*
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    /*
        spring data jpa 가 JpaRepository 를 받고 있으면 자동으로 구현제를 만들어준다.
        개발자가 스프링 빈에 등록하는 게 아니라 스프링 데이터 JPA 가 찾아서 알아서 구현체를 만들어서 등록해준다.
        개발자는 그것을 가져가 쓰면 된다.
    */
    private final MemberRepository memberRepository;

//    @Autowired 생성자가 하나일 경우에는 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
