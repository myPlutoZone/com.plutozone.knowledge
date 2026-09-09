# Spring + React

## 1. Overview

```text
┌────────────────────────┐
│         Browser        │
│                        │
│   React 19.2 + Vite    │
└──────────┬─────────────┘
           │ HTTP / JSON
           │
           ▼
┌────────────────────────┐
│       Tomcat 11.x      │
│                        │
│  Spring Framework 7.x  │
│  Spring MVC            │
│  Spring Security       │
│                        │
│       Controller       │
│           ↓            │
│        Service         │
│           ↓            │
│    Repository/Mapper   │
│           ↓            │
│    PostgreSQL/MySQL    │
└────────────────────────┘
```

| 영역        | 사용                   |
| :---------  | : ------------------- |
| Java        | Java 21.x LTS         |
| Spring      | Spring Framework 7.x  |
| Spring MVC  | Spring Web MVC        |
| WAS         | Tomcat 11.x           |
| Build       | Maven 3.9+            |
| DB          | PostgreSQL or MySQL   |
| DB Connect  | MyBatis               |
| JSON        | Jackson               |
| Security    | Spring Security 7     |
| API         | REST API              |
| Frontend    | React 19.2            |
| Build       | Vite                  |
| HTTP Client | fetch or Axios        |
| Test        | JUnit 5 + Spring Test |
| API Docu    | OpenAPI/Swagger       |
| Source      | Git                   |

## 2. Installation

### 2-1. Backend

```text
          Tomcat
            ↓
web.xml / ServletInitializer
            ↓
     DispatcherServlet
            ↓
        Spring MVC
```

#### JDK 21

- 다운로드 및 압축 해제
- 환경 변수(%JAVA_HOME%) 및 경로(%JAVA_HOME%\bin) 설정 후 확인(java -version)

#### Maven 3.9+

- 다운로드 및 압축 해제
- 환경 변수(%MAVEN_HOME%) 및 경로(%MAVEN_HOME%\bin) 설정 후 확인(mvn -v)

#### Tomcat 11

- 다운로드 및 압축 해제
- 설정(문자셋 등) 후 확인

#### Git

- 다운로드 및 설치

#### IntelliJ IDEA 또는 Eclipse

- 다운로드 및 설치 또는 압축 해제

#### PostgreSQL / MySQL

- 다운로드 및 설치

## 3. Project

### Backend Board

```text
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/board/
│   │   │       ├── config/
│   │   │       │   ├── WebConfig.java
│   │   │       │   ├── RootConfig.java
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   └── DatabaseConfig.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   └── BoardController.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── BoardService.java
│   │   │       │   └── BoardServiceImpl.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   └── BoardMapper.java
│   │   │       │
│   │   │       ├── domain/
│   │   │       │   └── Board.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── BoardDto.java
│   │   │       │   ├── BoardCreateRequest.java ???
│   │   │       │   ├── BoardUpdateRequest.java ???
│   │   │       │   └── BoardResponse.java ???
│   │   │       │
│   │   │       └── exception/
│   │   │           └── GlobalExceptionHandler.java
│   │   │
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── mapper/
│   │   │   │   └── BoardMapper.xml
│   │   │   └── logback.xml
│   │   │
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── web.xml
│   │
│   └── test/
```

### Frontend Board

```text
├── package.json
├── vite.config.ts
├── index.html
│
└── src/
    ├── api/
    │   └── boardApi.ts
    ├── components/
    │   ├── BoardList.tsx
    │   └── BoardForm.tsx
    ├── pages/
    │   ├── BoardListPage.tsx
    │   ├── BoardDetailPage.tsx
    │   └── BoardWritePage.tsx
    ├── types/
    │   └── board.ts
    ├── App.tsx
    └── main.tsx
```

## 4. Backend Maven

> Spring 7.x에서 사용할 정확한 세부 버전은 프로젝트 생성 시점의 Spring Framework BOM/Maven Central 기준 확인하고 Spring 7은 Jakarta EE 기반이므로 과거의 javax.servlet.*가 아니라 jakarta.servlet.*를 사용

```xml
<dependencies>

    <!-- Spring MVC -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- Spring Context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- Spring JDBC -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>${jackson.version}</version>
    </dependency>

    <!-- MyBatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>${mybatis.version}</version>
    </dependency>

    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>${mybatis-spring.version}</version>
    </dependency>

    <!-- DB Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>${postgresql.version}</version>
    </dependency>

    <!-- Servlet -->
    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>${servlet.version}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## 5. DispatcherServlet

> Spring 7.x + Web Config 프로젝트로 설정하여 web.xml을 최소화

```text
    web.xml
       ↓
DispatcherServlet
       ↓
  WebConfig.java
       ↓
   Controller
```

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
        xmlns="https://jakarta.ee/xml/ns/jakartaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="
        https://jakarta.ee/xml/ns/jakartaee
        https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
        version="6.0">
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                /WEB-INF/dispatcher-servlet.xml
            </param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

## 6. WebConfig

```java
@Configuration
@EnableWebMvc
@ComponentScan("com.example.board")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## 7. Board API

```text
GET    /api/boards or /api/boards?page=0&size=20&keyword=spring
GET    /api/boards/{id}

POST   /api/boards
PUT    /api/boards/{id}
DELETE /api/boards/{id}
```

```json
{
  "content": [
    {
      "id": 1,
      "title": "Spring MVC 게시판",
      "author": "hong",
      "createdAt": "2026-09-05T10:00:00"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 135,
  "totalPages": 7
}
```

## 8. Entity/Domain

```java
public class Board {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // getter / setter
}

public record BoardCreateRequest(
        String title,
        String content
) {}

public record BoardUpdateRequest(
        String title,
        String content
) {}

public record BoardResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
```

## 9. Controller

```java
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public BoardListResponse getBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return boardService.getBoards(page, size);
    }

    @GetMapping("/{id}")
    public BoardResponse getBoard(
            @PathVariable Long id) {

        return boardService.getBoard(id);
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(
            @RequestBody BoardCreateRequest request) {

        BoardResponse response =
                boardService.createBoard(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public BoardResponse updateBoard(
            @PathVariable Long id,
            @RequestBody BoardUpdateRequest request) {

        return boardService.updateBoard(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id) {

        boardService.deleteBoard(id);

        return ResponseEntity.noContent().build();
    }
}
```

## 10. Service

```java
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long id) {

        Board board = boardMapper.findById(id);

        if (board == null) {
            throw new BoardNotFoundException(id);
        }

        return BoardResponse.from(board);
    }

    @Override
    public BoardResponse createBoard(
            BoardCreateRequest request) {

        Board board = new Board();

        board.setTitle(request.title());
        board.setContent(request.content());

        boardMapper.insert(board);

        return BoardResponse.from(board);
    }
}
```

## 11. MyBatis

```java
@Mapper
public interface BoardMapper {

    List<Board> findAll(
            @Param("offset") int offset,
            @Param("size") int size
    );

    Board findById(Long id);

    void insert(Board board);

    void update(Board board);

    void delete(Long id);

    long count();
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.board.repository.BoardMapper">

    <select id="findById"
            resultType="com.example.board.domain.Board">
        SELECT
            id,
            title,
            content,
            author,
            created_at,
            updated_at
        FROM board
        WHERE id = #{id}
    </select>

    <select id="findAll"
            resultType="com.example.board.domain.Board">
        SELECT
            id,
            title,
            content,
            author,
            created_at,
            updated_at
        FROM board
        ORDER BY id DESC
        LIMIT #{size}
        OFFSET #{offset}
    </select>

    <insert id="insert"
            useGeneratedKeys="true"
            keyProperty="id">
        INSERT INTO board (
            title,
            content,
            author
        )
        VALUES (
            #{title},
            #{content},
            #{author}
        )
    </insert>

</mapper>
```

## 12. DB 테이블

PostgreSQL

```sql
CREATE TABLE board (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_board_created_at
ON board(created_at DESC);
```

## 13. Project Frontend(React)

> React 공식 문서에서도 Vite를 이용해 React 애플리케이션을 시작을 권장

> TypeScript(.ts)로 시작을 권장

```cmd
npm create vite@latest board-frontend -- --template react-ts
cd board-frontend
npm install
npm run dev
```

## 14. React API 호출

src/api/boardApi.ts

```js
import type {
  Board,
  BoardCreateRequest,
  BoardListResponse
} from "../types/board";

const API_URL = "http://localhost:8080/api";

export async function getBoards(
  page = 0,
  size = 20
): Promise<BoardListResponse> {

  const response = await fetch(
    `${API_URL}/boards?page=${page}&size=${size}`
  );

  if (!response.ok) {
    throw new Error("게시글 조회 실패");
  }

  return response.json();
}

export async function getBoard(
  id: number
): Promise<Board> {

  const response =
    await fetch(`${API_URL}/boards/${id}`);

  if (!response.ok) {
    throw new Error("게시글 조회 실패");
  }

  return response.json();
}

export async function createBoard(
  request: BoardCreateRequest
): Promise<Board> {

  const response = await fetch(
    `${API_URL}/boards`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(request)
    }
  );

  if (!response.ok) {
    throw new Error("게시글 등록 실패");
  }

  return response.json();
}
```

## 15. React 게시판 목록

```js
import { useEffect, useState } from "react";
import { getBoards } from "../api/boardApi";
import type { Board } from "../types/board";

export default function BoardListPage() {

  const [boards, setBoards] = useState<Board[]>([]);

  useEffect(() => {

    getBoards(0, 20)
      .then(response => {
        setBoards(response.content);
      })
      .catch(console.error);

  }, []);

  return (
    <div>
      <h1>게시판</h1>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          {boards.map(board => (
            <tr key={board.id}>
              <td>{board.id}</td>
              <td>{board.title}</td>
              <td>{board.author}</td>
              <td>{board.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
```

## 16. React 타입

> 타입을 통한 Backend와 Frontend의 API 명확한 관계

```js
export interface Board {
  id: number;
  title: string;
  content: string;
  author: string;
  createdAt: string;
  updatedAt: string;
}

export interface BoardCreateRequest {
  title: string;
  content: string;
}

export interface BoardListResponse {
  content: Board[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}
```

## 17. 개발할 때 가장 중요한 부분

> 단순히 CRUD만 만드는 것보다 아래 순서로 진행하는 것을 권장

### 1단계

```text
  Spring MVC
      +
   Tomcat
      +
REST Controller
```

먼저 이것만 성공하는 것을 권장

```text
GET /api/hello
→ {"message":"hello"}
```

### 2단계

DB 연결

```text
Spring
   ↓
DataSource
   ↓
PostgreSQL
```

### 3단계

MyBatis
```text
Controller
   ↓
Service
   ↓
Mapper
   ↓
MyBatis
   ↓
PostgreSQL
```

### 4단계

게시판 CRUD

```text
GET    /boards
GET    /boards/{id}
POST   /boards
PUT    /boards/{id}
DELETE /boards/{id}
```

### 5단계

React 연결

```text
React
   ↓
fetch()
   ↓
Spring MVC
   ↓
MyBatis
   ↓
PostgreSQL
```

### 6단계

Validation

```text
public record BoardCreateRequest(
        @NotBlank
        @Size(max = 200)
        String title,

        @NotBlank
        String content
) { }

@PostMapping
public ResponseEntity<BoardResponse> create(
        @Valid @RequestBody BoardCreateRequest request) {

    ...
}
```

## 18. 그 다음 반드시 추가할 것

게시판 프로젝트가 어느 정도 완성되면 다음을 추가하는 것을 추천

```text
① 회원 가입
② 로그인
③ Spring Security
④ JWT
⑤ 권한
⑥ 게시글 작성자 검증
⑦ 댓글
⑧ 파일 업로드
⑨ 검색
⑩ Pagination
⑪ Validation
⑫ 예외 처리
⑬ API 문서
⑭ 테스트
⑮ Docker
```

특히 Spring Security는 인증/인가를 담당하는 공식 Spring 프로젝트

## 19. 최종적으로 만들 프로젝트

```text
        React 19
           │
           │ REST / JSON
           ▼
┌─────────────────────┐
│    Spring MVC 7     │
│                     │
│ Controller          │
│      ↓              │
│ Service             │
│      ↓              │
│ MyBatis             │
└─────────┬───────────┘
          │
          ▼
     PostgreSQL
```

기능은

```text
[회원]
	회원가입
	로그인
	로그아웃
	내 정보
[게시판]
	게시글 목록
	게시글 검색
	게시글 상세
	게시글 등록
	게시글 수정
	게시글 삭제
	페이지네이션
[댓글]
	댓글 목록
	댓글 등록
	댓글 수정
	댓글 삭제
[첨부파일]
	파일 업로드
	파일 다운로드
	파일 삭제
[권한]
	USER
	ADMIN
```

그리고 구조는

```text
Spring MVC
 ├── Controller
 ├── Service
 ├── Repository
 ├── Domain
 ├── DTO
 ├── Exception
 ├── Security
 └── Config

React
 ├── pages
 ├── components
 ├── api
 ├── hooks
 ├── types
 └── router
```

이 정도까지 만들면 "Spring Boot를 사용하지 않고 Spring MVC가 실제로 어떻게 동작하는지"를 상당히 제대로 경험할 수 있다.