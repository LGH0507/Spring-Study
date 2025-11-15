# User Create Request Flow

```mermaid
sequenceDiagram
    participant Client as Postman
    participant Controller as DispatcherServlet → UserController
    participant Service as UserService.addUser()
    participant Repo as UserRepository.save()
    participant JPA as Hibernate/JPA Provider
    participant DB as Database
    participant Logger as Application Logger

    Client->>Controller: POST /api/users (JSON body)
    Controller->>Service: Map body to CreateUserRequest
    Service->>Repo: Build User entity & save()
    Repo->>JPA: Translate to persistence call
    JPA->>DB: INSERT users row (transactional)
    DB-->>JPA: Persisted entity + generated ID
    JPA-->>Repo: Managed entity
    Repo-->>Service: Save completed
    Service->>Logger: Log success info
    Service-->>Controller: CreateUserResponse
    Controller-->>Client: HTTP 200 OK + body
```

**설명**
- `UserController.addUser()`는 요청 본문을 `CreateUserRequest`로 역직렬화한 뒤 `UserService`를 호출합니다.
- `UserService`는 `User` 엔티티를 생성하고 `UserRepository.save()`를 통해 JPA(Hibernate)가 실제 DB `INSERT`를 수행하게 합니다.
- DB 반영이 성공하면 트랜잭션 커밋 후 애플리케이션 로거에 성공 로그가 남고, 컨트롤러는 200 OK 응답을 반환합니다.

