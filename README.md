# BookHitList — 중고도서 거래 플랫폼

> 도서 카테고리에 특화된 중고거래 서비스입니다.  
> 판매자 등록부터 1:1 채팅, 거래 성사까지의 사용자 흐름을 간단하게 연결하고  
> 검색/필터로 탐색 편의성을 높였습니다.

## 🧭 개요
번개장터, 당근과 같은 중고거래 사이트들은 여러가지가 있다. 하지만 '도서'라는 카테고리만을 가지고 보여주는 도서중고거래사이트들은 존재하지 않는다.<br>
이러한 도서 판매만을 취급하는 중고거래 사이트를 구상하며 이외에도 새 책이 나오면 그에 대한 광고와 정보 계시를 목적으로 하는 부분, 왓차피디아라는 영화 평점을 해주는 사이트 처럼 이용자가 책에 관한 리뷰를 할 수 있는 도서 통합 플랫폼을 구현하였다.

## 🧰 Tech Stack

### Language
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000)

### Backend
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![JPA/Hibernate](https://img.shields.io/badge/JPA%2FHibernate-59666C?style=for-the-badge)

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000)

### Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### Infra / DevOps
![AWS EC2](https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)

### Testing & Tools
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)



## ▶️ 실행 방법 (로컬)
1. **요구사항**: JDK 17+, MySQL 8.x  
2. **DB 준비**: 스키마 생성 (`bookhitlist` 등)  
3. **설정**: `src/main/resources/application.yml`에 DB 계정/비번 설정
4. **실행**:  
   ```bash
   ./gradlew bootRun
   # 또는 IntelliJ에서 Spring Boot 애플리케이션 실행
  
## 🖥️ 화면 구성
+ 메인화면<br>![1main](https://github.com/leem5514/bookhitlistProject/assets/116091798/116186b7-9d8c-4b0e-948e-d3edf0b4429b)
+ 로그인 및 구글 로그인<br>![2login](https://github.com/leem5514/bookhitlistProject/assets/116091798/4306ac71-f611-4608-b9c2-9e12252715b7) <br>
![3googlelogin](https://github.com/leem5514/bookhitlistProject/assets/116091798/db19fc5e-fcc9-46aa-99a2-632aab295734)
+ 글 작성<br>![4게시글 작성](https://github.com/leem5514/bookhitlistProject/assets/116091798/624995d9-1453-42ee-bb4f-bb2c77c9eedb)
+ 글 리스트<br> ![리스트 정렬](https://github.com/leem5514/bookhitlistProject/assets/116091798/a2525823-7b9b-49af-9207-fcc1797c658c)
+ 상세 페이지 및 채팅<br> ![채팅](https://github.com/leem5514/bookhitlistProject/assets/116091798/58abf8c7-c8d5-4c6b-9df4-bce2e9f6c13d)
+ 마이페이지<br>
  지도 기능, 개인QR, 개인정보 등
![마이페이지의 지도](https://github.com/leem5514/bookhitlistProject/assets/116091798/323c6510-1be5-466c-bba5-cd67b910ab35)

# 트러블 슈팅
+ js 을 통한 채팅 기능 구현 진행
  - 채팅 기록이 db에 저장되지 않는 문제 발생
     - 해결방안 : 채팅 기능을 컨트롤러와 js 만으로 구현하려고 한 이유 / 추가적으로 엔티티와 dto을 거친 뒤에 그 값을 db에 저장하는 방식 사용 필요 추정

+ Failed to connect to service endpoint 라는 예외 발생
   - AWS 개시 시 EC2 환경이 아닌 곳에서 사용시에 발생하는 예외로 추정
    - 해결방안 : application.yml 에서 EC2MetadataUtils: error 을 사용하면 이에 관한 예외를 잡을 수 있다. / 하지만 이외 인스턴스에 다른 에러가 발생하여 미해결  

