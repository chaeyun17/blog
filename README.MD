# CHAEYUN17 블로그 앱

사진과 글을 기록할 수 있는 블로그 형태 게시판입니다.

## Local 실행방법(Docker 사용)

1. docker PostgreSQL DB 세팅
    1. 도커 디렉토리로 이동: `cd docker`
    2. 도커 실행: `docker-compose up`
2. 서버 실행
    1. 프로젝트 상 최상위 디렉토리 위치로 이동
    2. 서버 실행: `./gradlew bootRun --args='--spring.profiles.active=local'`
3. 페이지 접속
    1. [http://localhost:8080](http://localhost:8080)

----

## 작업 기간

22.01.10 ~ 22.01.16(7일)

## 기능 목록

### 1. 포스트(글)

- 포스트 등록: 텍스트 등록
- 사진과 글로 구성된 포스트 목록 조회
    - [x] 정렬: 조회수, 작성일자
    - 조회수
    - [x] 페이지네이션
- [x] 포스트 상세 조회
- [x] 포스트 삭제
- [x] 포스트 수정
- 포스트 검색: 본문, 제목

## 도메인 설계

- POST 글
    - 정보: CONTENT, TITLE
    - 등록한다
    - 삭제한다
    - 수정한다
    - 상세 조회한다
    - 목록 조회한다
