package LDHD.study.domain.post.repository;

import LDHD.study.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    // JpaRepository에 의해 save(), findAll() 같은 메서드 자동 제공
}
