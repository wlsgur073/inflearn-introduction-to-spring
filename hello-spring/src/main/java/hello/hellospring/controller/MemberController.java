package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /*
    * MemberService 는 다른 컨트롤러에서도 사용할수도 있다.
    * 그래서 해당 서비스는 스프링 컨테이너에 등록하고 쓰면 하나만 등록이 되기에 메모리도 적게 먹는다.
    * */
    private final MemberService memberService;

    /*
    * Dependency Injection 에 3가지 방법이 있는데
    * 생성자를 통해서 들어오는 방법(생성자 주입),
    * 필드에다가 직접 @Autowired 를 넣는 방법(필드 주입),
    * 객체의 setter 메소드 위에 @Autowired 를 넣는 setter 주입이 있다.
    *
    * setter injection 은 누군가가 해당 객체를 호출하려 할 때 public 으로 열려 있어야 한다.
    * 객체가 노출이 되면 문제가 생길 수 있다.
    * */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
