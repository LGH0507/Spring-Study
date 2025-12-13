package LDHD.study.domain.post.web.controller;


import LDHD.study.common.response.GlobalResponse;
import LDHD.study.common.response.SuccessCode;
import LDHD.study.domain.post.service.PostService;
import LDHD.study.domain.post.web.controller.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post") // /api/post로 경로 지정
public class PostController {

    private final PostService postService;

    @Operation(summary = "새로운 게시물 등록", description = "게시물 작성 데이터를 받아 새로운 게시물을 DB에 저장합니다.")
    @PostMapping // 위 /api/post 경로를 통해 들어왔을 때 아래 로직 실행
    public ResponseEntity<GlobalResponse>createPost(@RequestBody CreatePostRequest createPostRequest){
         CreatePostResponse response = postService.createPost(createPostRequest);
        //@RequestBody 를 통해 JSON형식으로 들어온 요청을 java 형식으로 변환 후
        // postService의 createPost 로직 실행 후 response로 반환

         return GlobalResponse.onSuccess(SuccessCode.CREATED, response);
    }

    // 게시물 삭제 기능 구현
    @Operation(summary = "게시물 삭제", description = "DB에 저장되어 있는 사용자에 대한 게시물을 삭제합니다.")
    @DeleteMapping("/{postId}")  //경로 변수를 /{postId}로 지정 후 @PathVariable 통해 Json 형식을 java 형식으로 변환
    public ResponseEntity<GlobalResponse>deletePost(@PathVariable Long postId){

        postService.deletePost(postId);
        //postService의 deletePost 로직 실행 후 GlobalResponse.onSuccess 통해 Delete 성공 메시지 반환

        return GlobalResponse.onSuccess(SuccessCode.DELETED);
    }


    //게시물 수정 기능 구현
    @Operation(summary = "게시물 정보 수정", description = "DB에 저장되어 있는 사용자에 대한 게시물 정보를 수정합니다.")
    @PutMapping("/{postId}") //Put으로 들어오는 Http 요청을 아래 메서드에 매핑
    public ResponseEntity<GlobalResponse>updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request){
        //@PathVariable, @RequestBody : createPost, deletePost와 동일

        postService.updatePost(postId,request);

        //postService의 deletePost 로직 실행 후 GlobalResponse.onSuccess 통해 Update 성공 메시지 반환
        return GlobalResponse.onSuccess(SuccessCode.UPDATED);
    }
}
