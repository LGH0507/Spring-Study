package LDHD.study.domain.user.repository;

import LDHD.study.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository 계층을 담당하는 클래스라고 보면 된다.
 *
 * 참고)
 * Service 계층은 데이터를 처리하는 실질적인 비즈니스 로직을 담당하는 계층이고,
 * Repository 계층은 DB와 연결 되어 실제로 서버에서 DB에 쿼리문을 날리고, DB로부터 데이터도 받아오는 역할을 담당하는 계층이다.
 * 즉, 서버에서 DB와의 유일한 통신 수단이 되는 계층
 *
 * @JpaRepository 인터페이스를 상속 받으면 DB와의 통신 작업을 편리하게 제공해준다.
 */

//JpaRepository 타입에는 <데이터 객체, PK 타입>이 들어간다.
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     *  의문: 메소드 정의 된 게 아무것도 없는데, 어떻게 Service에서 save() 메소드를 사용하고 있을까??
     *
     *  위에서 "@JpaRepository 인터페이스를 상속 받으면 DB와의 통신 작업을 편리하게 제공해준다."
     *  -> save() 같은 메소드를 기본적으로 제공해준다는 의미이다.
     *
     * 또 다른 기능으로는 "메소드 편의 기능"을 제공해준다.
     * 우선, 이 Repository는 "interface"이다.
     * 즉, interface 자체로 객체를 생성할 수 없고, 반드시 그 구현체에 의해 객체가 생성 된다.
     * 구현체 -> public class UserRepositoryCustom implements UserRepository
     * JpaRepository는 이 인터페이스 안에서 메소드를 그냥 선언해도, 별도의 implements 없이 이를 그대로 사용할 수 있다.
     *
     * !!!심지어 메소드의 명명규칙만 어느정도 준수해도 그 메소드를 구현하지 않아도 기능을 제공한다.!!!
     *
     */

    public List<User> findAllByName(String name); // -> 메소드 이름만 선언 (구체적인 안의 코드는 구현하지 않음)

}
