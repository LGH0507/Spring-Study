package LDHD.study.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String name;

    @Column
    private String category;

    @Column(nullable = false)
    private String content;




}
