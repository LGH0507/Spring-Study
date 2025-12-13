    package LDHD.study.domain.Join.web.controller;


    import LDHD.study.common.response.GlobalResponse;
    import LDHD.study.common.response.SuccessCode;
    import LDHD.study.domain.Join.service.JoinService;
    import LDHD.study.domain.Join.web.controller.dto.JoinRequest;
    import LDHD.study.domain.Join.web.controller.dto.JoinResponse;
    import io.swagger.v3.oas.annotations.Operation;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/signup")
    public class JoinController {

        private final JoinService joinService;

        @Operation(summary = "새로운 사용자 등록", description = "회원가입 요청 데이터를 받아 새로운 데이터를 DB에 저장합니다.")
        @PostMapping
        public ResponseEntity<GlobalResponse>joinUser(@RequestBody JoinRequest joinRequest) {

            JoinResponse response = joinService.joinUser(joinRequest);

            return GlobalResponse.onSuccess(SuccessCode.CREATED, response);
        }

    }
