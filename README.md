# BookhitlistProject

# 목차
1. [개요](#개요)<br>
2. [개발 환경](#개발-환경) <br>
3. [실행 방법](#실행-방법) <br>
4. [화면 구성](#화면-구성) <br>
5. [오류 내역](#오류-내역) <br>

# 개요
+ 번개장터, 당근과 같은 중고거래 사이트들은 여러가지가 있다. 하지만 '도서'라는 카테고리만을 가지고 보여주는 도서중고거래사이트들은 존재하지 않는다.<br>
심화적으로 도서만을 위한 중고거래 와 커뮤니티 사이트를 구현해보고 싶었다.
# 개발 환경
![image](https://github.com/leem5514/bookhitlistProject/assets/116091798/9b976c06-7e05-46b3-a5ad-b1db44b27897) ![image](https://github.com/leem5514/bookhitlistProject/assets/116091798/8f40bb68-3414-4cdb-b27f-8a498cebc0bb)
 ![image](https://github.com/leem5514/bookhitlistProject/assets/116091798/d648a52c-597c-4217-8d18-fd8f08dc6a5a)

# 실행 방법
+ springboot 실행으로 intellij에서 실행 권장
+ AWS 을 통한 배포 예정
# 화면 구성
+ 메인화면<br>![1main](https://github.com/leem5514/bookhitlistProject/assets/116091798/116186b7-9d8c-4b0e-948e-d3edf0b4429b)
+ 로그인 및 구글 로그인<br>![2login](https://github.com/leem5514/bookhitlistProject/assets/116091798/4306ac71-f611-4608-b9c2-9e12252715b7) <br>
![3googlelogin](https://github.com/leem5514/bookhitlistProject/assets/116091798/db19fc5e-fcc9-46aa-99a2-632aab295734)
+ 글 작성<br>![4게시글 작성](https://github.com/leem5514/bookhitlistProject/assets/116091798/624995d9-1453-42ee-bb4f-bb2c77c9eedb)
+ 글 리스트<br> ![리스트 정렬](https://github.com/leem5514/bookhitlistProject/assets/116091798/a2525823-7b9b-49af-9207-fcc1797c658c)
+ 상세 페이지 및 채팅<br> ![채팅](https://github.com/leem5514/bookhitlistProject/assets/116091798/58abf8c7-c8d5-4c6b-9df4-bce2e9f6c13d)
+ 마이페이지<br> ![마이페이지의 지도](https://github.com/leem5514/bookhitlistProject/assets/116091798/323c6510-1be5-466c-bba5-cd67b910ab35)

# 오류 내역
+ js 을 통한 채팅 기능 구현 진행
  - 채팅 기록이 db에 저장되지 않는 문제 발생
     - 해결방안 : 채팅 기능을 컨트롤러와 js 만으로 구현하려고 한 이유 / 추가적으로 엔티티와 dto을 거친 뒤에 그 값을 db에 저장하는 방식 사용 필요 추정

+ Failed to connect to service endpoint 라는 예외 발생
   - AWS 개시 시 EC2 환경이 아닌 곳에서 사용시에 발생하는 예외로 추정
    - 해결방안 : application.yml 에서 EC2MetadataUtils: error 을 사용하면 이에 관한 예외를 잡을 수 있다. / 하지만 이외 인스턴스에 다른 에러가 발생하여 미해결  

