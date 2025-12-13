package LDHD.study.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String password;

    @Column
    String address;

    @Column(unique = true)
    String email;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;


    int age;

    @Builder
    public User(String name, String password, String address, int age, String email ) {

        this.name = name;
        this.password = password;
        this.address = address;
        this.age = age;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();

    }


}
