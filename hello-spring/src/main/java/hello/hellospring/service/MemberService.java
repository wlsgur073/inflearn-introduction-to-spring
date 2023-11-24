package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa 를 사용할 떄는 트랜잭션 안에서 실행이 되어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member){
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //  같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //  이미 findByName()에서 Optional 을 반환하고 있기에
        //  위의 코드에서는 특정 변수에 담을 필요 없이 ifPresent 를 호출할 수 있다.
        //  ifPresent 는 m 이 null 이 아니라 어떤 값이 존재한다면 throw 던짐
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
