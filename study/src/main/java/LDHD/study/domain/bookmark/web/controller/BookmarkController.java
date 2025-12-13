package LDHD.study.domain.bookmark.web.controller;


import LDHD.study.common.response.GlobalResponse;
import LDHD.study.common.response.SuccessCode;
import LDHD.study.domain.bookmark.Bookmark;
import LDHD.study.domain.bookmark.repository.BookmarkRepository;
import LDHD.study.domain.bookmark.service.BookmarkService;
import LDHD.study.domain.bookmark.web.controller.dto.CreateBookmarkResponse;
import LDHD.study.domain.bookmark.web.controller.dto.DeleteBookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // final 필드 생성자 생성 및 의존성 주입
@RequestMapping("/api/bookmark") //기본 url 경로 ("/api/bookmark")로 설정
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // 즐겨 찾기 추가
    @PostMapping("/post/{postId}")
    public ResponseEntity<GlobalResponse> createBookmark(
            // 경로에서 {postId}값 지정, Header에 ("X-USER-ID") 지정 -> 현재 요청자 식별 가능
            @PathVariable Long postId, @RequestHeader("X-USER-ID") Long currentUserId) {
        // postId와 currentUserId를 bookmark서비스로 보내 createBookmark 로직 실행
        CreateBookmarkResponse response = bookmarkService.createBookmark(postId,currentUserId);

        // Created 성공 상태와 함께 response 반환
        return  GlobalResponse.onSuccess(SuccessCode.CREATED, response );
    }


    // 즐겨 찾기에서 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<GlobalResponse>deleteBookmark(
            @PathVariable Long postId, @RequestHeader("X-USER-ID") Long currentUserId) {
        // postId와 currentUserId를 bookmark서비스로 보내 deleteBookmark 로직 실행
        DeleteBookmarkResponse response = bookmarkService.deleteBookmark(postId,currentUserId);

        // Deleted 성공 상태와 함께 response 반환
        return GlobalResponse.onSuccess(SuccessCode.DELETED, response);
    }
}
