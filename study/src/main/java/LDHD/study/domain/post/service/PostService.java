package LDHD.study.domain.post.service;

import LDHD.study.common.exception.GeneralException;
import LDHD.study.common.response.ErrorCode;
import LDHD.study.domain.post.Post;
import LDHD.study.domain.post.web.controller.dto.*;
import LDHD.study.domain.user.User;
import LDHD.study.domain.post.repository.PostRepository;
import LDHD.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@RequiredArgsConstructor //
@Service
public class PostService {

    private final UserRepository userRepository; //CreatePostRequest에서 받은 userId를 사용하여 DB에서 User 엔티티를 조회
    private final PostRepository postRepository; //생성된 Post 엔티티를 DB에 저장하기 위해


    public CreatePostResponse createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        /*첫 시도 : 500 Internal Server Error : findById(request.getUserId()).orElseThrow(null) 까지만 했을 때
        *예외 처리가 잘 되지 않았음
        * 이후 방향성을 찾지 못해 ai 사용함.. =>  .orElseThrow(() -> new IllegalArgumentException("User not found"));
        * 로직을 통해 User를 찾지 못했을 때 "User not found"라는 메시지를 띄워 예외를 지정해 처리하는 로직이 있음을 알게됨.
        */

        //입력 받은 데이터 바탕으로 새 Post 객체 생성
        Post post = new Post(
                request.getTitle(),
                user,
                request.getCategory(),
                request.getContent(),
                LocalDateTime.now()
        );

        //jpaRepository의 save() 메서드를 호출해 DB에 저장 후 savedPost 객체를 반환
        Post savedPost = postRepository.save(post);

        // @Getter -> get()을 통해 응답 반환
        return new CreatePostResponse(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getCategory());
    }

    /*문제점: savedPost.get()이 실행되지 않았음.
    *1. 패키지 구조 오류. /repository/service로 되어있었음
    *2. Post.java에서 @Getter 설정이 되어 있지않아 .get()메서드 호출 불가했음
    */

    // 게시물 삭제 기능
    public DeletePostResponse deletePost(Long postId) {

        //게시물 정보가 없을 때 예외 처리(에러코드에 POST_NOT_FOUND가 없어 임시로 USER_NOT_FOUND사용함)
        if(!postRepository.existsById(postId)){
            throw new GeneralException(ErrorCode.USER_NOT_FOUND);
        }

        //postRepository의 deleteById() 메서드 호출
        postRepository.deleteById(postId);

        //삭제 응답 반환
        return new DeletePostResponse(postId);
    }


    //게시물 수정 기능
    public UpdatePostResponse updatePost(Long postId, UpdatePostRequest request) {

        //게시물 정보가 없을 때 예외 처리
        Post post = postRepository.findById(postId).orElseThrow(()->
                new GeneralException(ErrorCode.USER_NOT_FOUND));

        //Post.java에서 생성한 Getter/Setter 이용
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());

        postRepository.save(post);
        //DB에 값 저장

        return new UpdatePostResponse(postId);
    }
}
