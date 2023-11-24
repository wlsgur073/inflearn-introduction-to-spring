package hello.hellospring.domain;

// JPA 는 ORM 기술이다.
// 따라서 object 와 관계형 데이터베이스를 mapping 한다.

import javax.persistence.*;

@Entity
public class Member {

    /*
    * DB 가 아이디를 자동으로 생성해주는 것을 아이덴티티 전략이라고 한다.
    * */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
