package LDHD.study.domain.post.web.controller;


import LDHD.study.common.response.GlobalResponse;
import LDHD.study.common.response.SuccessCode;
import LDHD.study.domain.post.service.PostService;
import LDHD.study.domain.post.web.controller.dto.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post") // /api/post로 경로 지정
public class PostController {

    private final PostService postService;

    @PostMapping // 위 /api/post 경로를 통해 들어왔을 때 아래 로직 실행
    public ResponseEntity<GlobalResponse>createPost(@RequestBody CreatePostRequest createPostRequest){
         CreatePostResponse response = postService.createPost(createPostRequest);
        //@RequestBody 를 통해 JSON형식으로 들어온 요청을 java 형식으로 변환 후
        // postService의 createPost 로직 실행 후 response로 반환

         return GlobalResponse.onSuccess(SuccessCode.CREATED, response);
    }

    // 게시물 목록 조회 기능 구현
    @GetMapping
    public ResponseEntity<GlobalResponse>getPostList(){

        GetPostListResponse response = postService.getPostList();

        return GlobalResponse.onSuccess(SuccessCode.OK, response);
    }

    // 게시물 삭제 기능 구현
    @DeleteMapping("/{postId}")  //경로 변수를 "/{postId}"로 지정
    public ResponseEntity<GlobalResponse>deletePost(@PathVariable Long postId, @RequestHeader("X-USER-ID") Long currentUserId) {

        postService.deletePost(postId, currentUserId);
        //postService의 deletePost 로직 실행 후 GlobalResponse.onSuccess 통해 Delete 성공 메시지 반환

        return GlobalResponse.onSuccess(SuccessCode.DELETED);
    }


    //게시물 수정 기능 구현
    @PutMapping("/{postId}") //Put으로 들어오는 Http 요청을 아래 메서드에 매핑 -> Patch로도 사용 가능
    public ResponseEntity<GlobalResponse>updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request,
                                                    @RequestHeader("X-USER-ID") Long currentUserId) {
        //@PathVariable, @RequestBody : createPost, deletePost와 동일

        postService.updatePost(postId,request,currentUserId);

        //postService의 deletePost 로직 실행 후 GlobalResponse.onSuccess 통해 Update 성공 메시지 반환
        return GlobalResponse.onSuccess(SuccessCode.UPDATED);
    }

    //댓글 생성 기능 구현
    @PostMapping("/{postId}/comment")
    public ResponseEntity<GlobalResponse>createComment(@PathVariable Long postId, @RequestBody CreateCommentRequest request,
                                                       @RequestHeader("X-USER-ID") Long currentUserId){
        //@PathVariable, @RequestBody : 경로 변수를 "/{postId}/comment"로 지정 후 @RequestBody 통해 Json 형식을 java 형식으로 변환

        CreateCommentResponse response = postService.createComment(postId,currentUserId,request);

        return GlobalResponse.onSuccess(SuccessCode.CREATED, response);
    }
}
