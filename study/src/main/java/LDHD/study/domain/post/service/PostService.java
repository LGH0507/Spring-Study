package LDHD.study.domain.post.service;

import LDHD.study.common.exception.GeneralException;
import LDHD.study.common.response.ErrorCode;
import LDHD.study.domain.post.Comment;
import LDHD.study.domain.post.Post;
import LDHD.study.domain.post.repository.CommentRepository;
import LDHD.study.domain.post.web.controller.dto.*;
import LDHD.study.domain.user.User;
import LDHD.study.domain.post.repository.PostRepository;
import LDHD.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor //
@Service
public class PostService {

    private final UserRepository userRepository; //CreatePostRequest에서 받은 userId를 사용하여 DB에서 User 엔티티를 조회
    private final PostRepository postRepository; //생성된 Post 엔티티를 DB에 저장하기 위해
    private final CommentRepository commentRepository;

    //게시물 생성 기능
    public CreatePostResponse createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new GeneralException(ErrorCode.USER_NOT_FOUND));
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
                LocalDateTime.now(),
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
    public DeletePostResponse deletePost(Long postId, Long currentUserId) {

        //게시물 정보가 없을 때 예외 처리(에러코드에 POST_NOT_FOUND가 없어 임시로 USER_NOT_FOUND사용함) --> 에러코드 추가 완료
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new GeneralException(ErrorCode.POST_NOT_FOUND));

        // 게시물 접근 권한 확인(사용자 id와 게시물 작성자 id가 일치하지 않는 경우)
        if (!post.getUser().getId().equals(currentUserId)) {
            throw new GeneralException(ErrorCode.UNAUTHORIZED);
        }

        //postRepository의 deleteById() 메서드 호출
        postRepository.deleteById(postId);

        //삭제 응답 반환
        return new DeletePostResponse(postId);
    }


    //게시물 수정 기능
    public UpdatePostResponse updatePost(Long postId, UpdatePostRequest request, Long currentUserId) {

        //게시물 정보가 없을 때 예외 처리
        Post post = postRepository.findById(postId).orElseThrow(()->
                new GeneralException(ErrorCode.POST_NOT_FOUND));

        // 게시물 접근 권한 확인(사용자 id와 게시물 작성자 id가 일치하지 않는 경우)
        if (!post.getUser().getId().equals(currentUserId)) {
            throw new GeneralException(ErrorCode.UNAUTHORIZED);
        }


        //Post.java에서 생성한 Getter/Setter 이용
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setCreated_at(LocalDateTime.now());
        post.setUpdated_at(LocalDateTime.now());

        postRepository.save(post);
        //DB에 값 저장

        return new UpdatePostResponse(postId);
    }

    // 댓글 생성 기능
    public CreateCommentResponse createComment(Long postId, Long currentUserId, CreateCommentRequest request){

        // 예외 처리
        // 1. 작성자 확인
        // 원래 request.getUserId()로 userid값을 확인하려 했지만, 불필요했기에 currentUserId로 바꿈
        User user = userRepository.findById(currentUserId).orElseThrow(() ->
                new GeneralException(ErrorCode.USER_NOT_FOUND));

        // 2. 게시물 존재 확인
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new GeneralException(ErrorCode.POST_NOT_FOUND));

        // Post와 USer 엔티티를 외래키로 연결
        Comment comment = new Comment(
                post,
                user,
                request.getContent()
        );

        // DB에 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                savedComment.getId(),
                post.getId(), // 댓글 달린 게시물
                user.getId(), // 댓글 작성자
                savedComment.getContent()
        );
}
        // 게시물 목록 조회
        public GetPostListResponse getPostList(){

            List<Post> posts = postRepository.findAll();
            List<GetPostList> postList = posts.stream()
                    .map(post -> new GetPostList(post))
                    .collect(toList());

            return new GetPostListResponse(postList);
        }
    }
