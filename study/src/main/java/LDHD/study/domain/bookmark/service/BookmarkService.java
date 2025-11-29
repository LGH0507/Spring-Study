package LDHD.study.domain.bookmark.service;


import LDHD.study.common.exception.GeneralException;
import LDHD.study.common.response.ErrorCode;
import LDHD.study.domain.bookmark.Bookmark;
import LDHD.study.domain.bookmark.repository.BookmarkRepository;
import LDHD.study.domain.bookmark.web.controller.dto.CreateBookmarkResponse;
import LDHD.study.domain.bookmark.web.controller.dto.DeleteBookmarkResponse;
import LDHD.study.domain.post.Post;
import LDHD.study.domain.post.repository.PostRepository;
import LDHD.study.domain.user.User;
import LDHD.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 즐겨찾기에 추가 로직
    public CreateBookmarkResponse createBookmark(Long postId, Long currentUserId){

        // 사용자 확인
        User user = userRepository.findById(currentUserId).orElseThrow(
                () -> new GeneralException(ErrorCode.USER_NOT_FOUND));

        // 게시물 존재 확인
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new  GeneralException(ErrorCode.POST_NOT_FOUND));

        // 즐겨찾기 중복 검사(이미 즐겨찾기에 등록되어 있다면 ErrorCode.DUPLICATE_RESOURCE 반환
        if (bookmarkRepository.findByUser_IdAndPost_Id(currentUserId, postId).isPresent()) {
            throw new GeneralException(ErrorCode.DUPLICATE_RESOURCE );
        }

        Bookmark bookmark = new Bookmark(user, post);
        Bookmark savedBookmark = bookmarkRepository.save(bookmark); // DB에 bookmark 저장

        return new CreateBookmarkResponse(
                savedBookmark.getId(), //저장된 bookmark Id
                post.getId(), // 게시물 Id
                user.getId() // 사용자 Id
        );
    }

    // 즐겨찾기에서 삭제 로직
    public DeleteBookmarkResponse deleteBookmark(Long postId, Long currentUserId){

        // 게시물 존재 확인
        postRepository.findById(postId).orElseThrow(
                ()-> new  GeneralException(ErrorCode.POST_NOT_FOUND));

        // 즐겨찾기 안에 게시물 존재 확인
        Bookmark bookmark = bookmarkRepository.findByUser_IdAndPost_Id(currentUserId,postId).orElseThrow(
                ()-> new GeneralException(ErrorCode.POST_NOT_FOUND));

        bookmarkRepository.delete(bookmark); // DB에 저장되어있는 즐겨찾기 삭제

        //응답을 컨트롤러로 반환
        return new DeleteBookmarkResponse(postId, currentUserId);
    }
}

