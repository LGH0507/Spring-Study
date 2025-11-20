package LDHD.study.domain.user.service;

import LDHD.study.common.exception.GeneralException;
import LDHD.study.common.response.ErrorCode;
import LDHD.study.domain.user.User;
import LDHD.study.domain.user.repository.UserRepository;
import LDHD.study.domain.user.web.controller.dto.*;
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
                request.getAge(),
                request.getEmail()
        );

        if(userRepository.existsByEmail((request.getEmail()))){
            throw new GeneralException(ErrorCode.ALREADY_EXISTS);
        }


        // userRepository의 save() 메소드를 호출한다.
        /* (이런 식으로 save() 메소드는 저장된 객체를 메소드로 반환) User newUser = */ userRepository.save(user);

 //       userRepository.findAllByName(request.getName()); -> 이렇게 메소드 이름만 선언해도 바로 사용 가능

        return new CreateUserResponse(request.getName());

    }

    //사용자 정보 삭제
    public DeleteUserResponse deleteUser(Long userId){

        /* 사용자 정보가 존재하지 않는 경우 GeneralException 통해 예외처리
        1. 사용자가 없으면 GeneralException 을 던짐
        2. GlobalExceptionHandler가 예외를 처리
        3. 에러코드 USER_NOT_FOUND 반환
         */
        if(!userRepository.existsById(userId)){
            throw new GeneralException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(userId);

        return new DeleteUserResponse(userId);
    }

    //사용자 정보 수정
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request, String message){

        User user = userRepository.findById(userId).orElseThrow(()
                -> new GeneralException(ErrorCode.USER_NOT_FOUND));



        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setAddress(request.getAddress());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return new UpdateUserResponse(userId);
    }
    // TODO: 사용자의 정보를 삭제하는 메소드 구현 (userRepository.delete(); 사용)

}
