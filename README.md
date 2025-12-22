# BookHitList — 중고도서 거래 플랫폼

> 도서 카테고리에 특화된 중고거래 서비스입니다.  
> 판매자 등록부터 1:1 채팅, 거래 성사까지의 사용자 흐름을 간단하게 연결하고  
> 검색/필터로 탐색 편의성을 높였습니다.

## 🧭 개요
번개장터, 당근과 같은 다양한 중고거래 플랫폼이 있지만, '도서'라는 카테고리만을 가지고 보여주는 도서관련 중고거래플랫폼은 존재하지 않습니다.<br>
이러한 도서 판매만을 취급하는 중고거래 사이트를 구상하며 이외에도 새 책이 나오면 그에  정보 계시를 목적으로 하는 부분, `왓차피디아`라는 영화 평점 사이트 처럼 이용자가 책에 관한 리뷰 및 커뮤니케이션을 할 수 있는 도서 통합 플랫폼을 구현하였습니다.

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
  
## 🖥️ 실행 화면
<table>
<tr>
<td align="center" width="50%">
  <sub><b>🏠 메인</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/116186b7-9d8c-4b0e-948e-d3edf0b4429b">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/116186b7-9d8c-4b0e-948e-d3edf0b4429b" width="460" alt="메인" />
  </a>
</td>
<td align="center" width="50%">
  <sub><b>🔐 로그인 · 구글 로그인</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/4306ac71-f611-4608-b9c2-9e12252715b7">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/4306ac71-f611-4608-b9c2-9e12252715b7" width="460" alt="로그인" />
  </a><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/db19fc5e-fcc9-46aa-99a2-632aab295734">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/db19fc5e-fcc9-46aa-99a2-632aab295734" width="460" alt="구글 로그인" />
  </a>
</td>
</tr>

<tr>
<td align="center" width="50%">
  <sub><b>📝 판매글 작성</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/624995d9-1453-42ee-bb4f-bb2c77c9eedb">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/624995d9-1453-42ee-bb4f-bb2c77c9eedb" width="460" alt="판매글 작성" />
  </a>
</td>
<td align="center" width="50%">
  <sub><b>📋 판매글 목록</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/a2525823-7b9b-49af-9207-fcc1797c658c">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/a2525823-7b9b-49af-9207-fcc1797c658c" width="460" alt="판매글 목록" />
  </a>
</td>
</tr>

<tr>
<td align="center" width="50%">
  <sub><b>💬 상세 · 채팅</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/58abf8c7-c8d5-4c6b-9df4-bce2e9f6c13d">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/58abf8c7-c8d5-4c6b-9df4-bce2e9f6c13d" width="460" alt="상세 및 채팅" />
  </a>
</td>
<td align="center" width="50%">
  <sub><b>👤 마이페이지(지도/QR)</b></sub><br>
  <a href="https://github.com/leem5514/bookhitlistProject/assets/116091798/323c6510-1be5-466c-bba5-cd67b910ab35">
    <img src="https://github.com/leem5514/bookhitlistProject/assets/116091798/323c6510-1be5-466c-bba5-cd67b910ab35" width="460" alt="마이페이지 지도" />
  </a>
</td>
</tr>
</table>

## 🐞 트러블 슈팅

### 1) 실시간 채팅 — 메시지 미저장/순서 꼬임
- 🚨 문제  
  WebSocket 1:1 채팅에서 메시지가 간헐적으로 DB에 저장되지 않거나, Room 간 순서가 뒤섞임.
- 🔎 원인(가설→검증)  
  컨트롤러↔JS 레벨 송수신만 처리하여 **영속화 누락** 여지가 있었고, **정렬 키(roomId, seq/createdAt)** 부재로 동시 요청 시 순서 보장 불가.
- 🔧 해결  
  1) `Message(roomId, senderId, payload, createdAt, seq)` **엔티티 도입**  
  2) **저장 → 브로드캐스트** 순서 고정(`@Transactional save()` 성공 시에만 publish)  
  3) 다중 인스턴스 대비 **Redis Pub/Sub**로 브로드캐스트  
  4) (roomId, seq) **UNIQUE 인덱스**로 중복·역전 방지
- ✅ 검증  
  동일 Room에 1,000건 동시 전송 테스트 → **손실 0 / 역전 0** 확인(단위·통합 테스트)

  
### 2) 로컬 실행 시 `Failed to connect to service endpoint` 문제 발생
- 🚨 문제  
  로컬/비 EC2 환경에서 애플리케이션 실행 시 AWS SDK가 **EC2 메타데이터(IMDS)** 접근을 시도하다 실패 로그(`EC2MetadataUtils`) 출력.
- 🔎 원인(가설→검증)  
  자격·리전이 명시되지 않아 SDK 기본 자격 체인이 **IMDS 우선 조회** → 로컬에는 IMDS가 없어서 연결 실패.  
  환경변수/프로파일로 리전·자격을 지정하면 로그가 사라지는 것으로 원인 확인.
- 🔧 해결  
  1) 로컬에서 IMDS 비활성화: `AWS_EC2_METADATA_DISABLED=true`  
  2) 리전/자격 명시(택1+):  
     - 환경변수: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_REGION`  
     - 또는 `application-dev.yml`에서 리전 강제, **dev/prod 프로파일 분리**
- ✅ 검증  
  - 앱 기동 시 `EC2MetadataUtils` 관련 경고 미출력 확인  
  - S3 `ListBuckets` 등 간단 호출 성공(권한 정상)  
  - dev ↔ prod 전환 시 로그에 리전/자격 경로가 명확히 표기

