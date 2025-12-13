package LDHD.study.domain.Join.service;

import LDHD.study.common.exception.GeneralException;
import LDHD.study.common.response.ErrorCode;
import LDHD.study.domain.Join.UserProfile;
import LDHD.study.domain.Join.repository.UserProfileRepository;
import LDHD.study.domain.Join.web.controller.dto.JoinRequest;
import LDHD.study.domain.Join.web.controller.dto.JoinResponse;
import LDHD.study.domain.user.User;
import LDHD.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public JoinResponse joinUser(JoinRequest request){

        // 이메일 중복 검사
        if(userRepository.existsByEmail(request.getEmail())){
            throw new GeneralException(ErrorCode.ALREADY_EXISTS);
        }

        // 일반 주소와 상세 주소 합침(fullAddress 사용 위해)
        String fullAddress = request.getAddress()+ " " + request.getSpecAddress();

        User newUser = User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .address(fullAddress)
                .age(request.getAge())
                .build();

        User savedUser = userRepository.save(newUser);

        UserProfile userProfile = UserProfile.builder()
                .user(savedUser)
                .gender(request.getGender())
                .birthYear(request.getBirthYear())
                .birthMonth(request.getBirthMonth())
                .birthDay(request.getBirthDay())
                .build();

        userProfileRepository.save(userProfile);

        return JoinResponse.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .created_at(savedUser.getCreated_at())
                .build();

    }
}
