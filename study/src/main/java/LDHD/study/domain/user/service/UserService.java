package LDHD.study.domain.user.service;

import LDHD.study.domain.user.User;
import LDHD.study.domain.user.repository.UserRepository;
import LDHD.study.domain.user.web.controller.dto.CreateUserRequest;
import LDHD.study.domain.user.web.controller.dto.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse addUser(CreateUserRequest request){

        User user = new User(request.getName(),
                request.getPassword(),
                request.getAddress(),
                request.getEmail(),
                request.getAge());

        userRepository.save(user);

        return new CreateUserResponse(request.getName());

    }
}
