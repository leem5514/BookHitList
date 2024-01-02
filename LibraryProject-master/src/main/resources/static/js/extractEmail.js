const email = document.getElementById("email");
email.value = tokenDecode().sub;
console.log(email.value);

function tokenDecode() {
    // Bearer부분은 제외하고 토큰만 구성하도록 함
    const token = localStorage.getItem("access_token")?.replace('Bearer ', '');
    // token에서 payload부분만 분리해서 관리
    const base64Payload = token.split('.')[1];
    // URL-safe를 위해서 Base64문자열로 변환하는 과정 - 를 +로 대체 _를 /로 대체
    const base64 = base64Payload.replace(/-/g, '+').replace(/_/g, '/');

    // 1. JSON 형식 데이터를 객체로 파싱
    // 2. decodeURIComponent() - URL인코딩된 문자열을 디코딩해서 다시 원래 문자열로 복원하는 과정
    // 3. window.atob()은 Base64 문자열을 디코딩하여 원래의 이진 데이터를 얻는 과정
    // 4. split('')은 디코딩된 이진 데이터를 하나씩 문자로 분리해 배열로 만듬. 이 배열은 URL인코딩된 문자로 변환하기 위해 사용
    // 5. .map(function(c) { ... }) 각 문자에 대해 콜백 함수를 실행해 문자열을 처리
    // 6. URL 인코딩은 % 특수문자와 그 뒤에 따르는 16진수 값으로 표현하는 방식으므로, 맨 앞에 %문자를 붙여서 URL 인코딩된 문자열 형식으로 만듬
    // 7. 뒤의 내용은 16진수 값으로 표현해야 하니까 .toString(16)으로 16진수 문자열로 변환
    // 8. c.charCodeAt(0) 문자의 유키노드 코드 포인트 값을 가져온다. .charCodeAt() 매서드는 문자열 내의 특정 인덱스에 해당하는 문자의
    // 유니 코드값을 반환하는데, 여기서 0을 인덱스로 지정해 첫 번째 문자의 코드 포인트 값을 가져온다
    // 9. ('00' + c.charCodeAt(0).toString(16)).slice(-2) 는 앞에 0을 추가하여 2자리의 16진수 문자열을 만든다
    // 이렇게 하면 각 코드 포인트의 16진수 문자열이 항상 두 자리로 유지된다.
    // 10. join()으로 배열의 모든 요소를 하나의 문자열로 결합

    const decodedJWT = JSON.parse(
        decodeURIComponent(
            window
                .atob(base64)
                .split('')
                .map(function (c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                })
                .join('')
        )
    )
    console.log(decodedJWT);
    return decodedJWT;
}