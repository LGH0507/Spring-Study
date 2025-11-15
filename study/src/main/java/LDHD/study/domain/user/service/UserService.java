package LDHD.study.domain.user.service;

import LDHD.study.domain.user.User;
import LDHD.study.domain.user.repository.UserRepository;
import LDHD.study.domain.user.web.controller.dto.CreateUserRequest;
import LDHD.study.domain.user.web.controller.dto.CreateUserResponse;
import LDHD.study.domain.user.web.controller.dto.DeleteUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Controller로부터 요청 처리를 위임받아서 실제 비즈니스 로직을 실행하여 처리한다.
    public CreateUserResponse addUser(CreateUserRequest request){

        // 입력 받은 데이터를 바탕으로 새로운 User 객체를 생성한다.
        User user = new User(request.getName(),
                request.getPassword(), // @Getter로 인해 메소드를 선언하지 않아도 사용 가능
                request.getAddress(),
                request.getEmail(),
                request.getAge());

        // userRepository의 save() 메소드를 호출한다.
        /* (이런 식으로 save() 메소드는 저장된 객체를 메소드로 반환) User newUser = */ userRepository.save(user);

 //       userRepository.findAllByName(request.getName()); -> 이렇게 메소드 이름만 선언해도 바로 사용 가능

        return new CreateUserResponse(request.getName());

    }

    public DeleteUserResponse deleteUser(Long userId){

        userRepository.deleteById(userId);

        return new DeleteUserResponse(userId);
    }

    // TODO: 사용자의 정보를 삭제하는 메소드 구현 (userRepository.delete(); 사용)

}
