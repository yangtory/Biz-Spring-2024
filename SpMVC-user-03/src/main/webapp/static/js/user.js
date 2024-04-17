document.addEventListener("DOMContentLoaded", () => {
  const join_form = document.querySelector("form.join");
  const username = join_form.querySelector("input[name='username']");
  const password = join_form.querySelector("input[name='password']");
  const re_password = join_form.querySelector(
    "input[name='re_password']"
  );
  const btn_join = join_form.querySelector("button");
  // 선생님 github 정규식 문법 참고하기
  // username 은 영대소문자와 언더바(_) 를 사용할 수 있으며 4~20 자 까지 가능하다
  // 입력된 문자중 소문자,대문자 a-z 까지 숫자 0-9 까지 포함되어 있는 경우
  // {4,20} : 문자 길이가 4~20
  const userNameExp = /^[a-zA-Z0-9_]{4,20}$/;
  // 특수문자 포함시키기
  const passwordExp = /^[1-zA-Z0-9!@#$%^&*()]{8,20}$/;

  const idCheck = async (username) => {
    try {
      const res = await fetch(`${rootPath}/user/idcheck/${username}`);
      // const json = await res.json(); // 서버가 json 타입의 데이터를 보낼때
      const result = await res.text(); // 문자열로 받기
      console.log(result);
      return result === "OK";
    } catch (error) {
      alert("서버와 통신 오류");
    }
  };
  btn_join.addEventListener("click", async () => {
    if (!username.value) {
      alert("USERNAME 은 반드시 입력하세요");
      username.select();
      return false;
    }

    // username 이 정규식에 맞지 않으면
    if (!userNameExp.test(username.value)) {
      alert(
        "USERNAME 은 영문 대소문자, 숫자, _ 로 4~20자 만 사용가능 합니다"
      );
      username.select();
      return false;
    }
    if (!(await idCheck(username.value))) {
      alert("이미 가입된 USERNAME 입니다");
      username.select();
      return false;
    }

    if (!password.value) {
      alert("PASSWORD 는 반드시 입력하세요");
      password.select();
      return false;
    }

    if (!passwordExp.text(password.value)) {
      alert(
        "PASSWORD 는 8 자리 이상 영문, 숫자, 특수문자만 가능합니다"
      );
      password.select();
      return false;
    }

    if (!re_password.value) {
      alert("PASSWORD 확인은 반드시 입력하세요");
      re_password.select();
      return false;
    }
    if (password.value !== re_password.value) {
      alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
      password.select();
      return false;
    }
    // form 의 전송 실행하기
    join_form.submit();
  });
});
