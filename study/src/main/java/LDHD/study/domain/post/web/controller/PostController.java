package LDHD.study.domain.post.web.controller;


import LDHD.study.domain.post.service.PostService;
import LDHD.study.domain.post.web.controller.dto.CreatePostRequest;
import LDHD.study.domain.post.web.controller.dto.CreatePostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post") // /api/post로 경로 지정
public class PostController {

    private final PostService postService;

    @PostMapping // 위 /api/post 경로를 통해 들어왔을 때 아래 로직 실행
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest createPostRequest){
         CreatePostResponse response = postService.createPost(createPostRequest);
        //@RequestBody 를 통해 JSON형식으로 들어온 요청을 java 형식으로 변환 후
        // postService의 createPost 로직 실행 후 response로 반환

         return ResponseEntity.ok(response);
    }


}
