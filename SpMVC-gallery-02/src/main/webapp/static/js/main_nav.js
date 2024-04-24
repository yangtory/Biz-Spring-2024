// main-nav 각 tag className 을 키로하는 객체 선언
/*
JSON type 의 객체
형식 = {key : value}
console.log(NAV_INDEX.home) ? {url: "/"}
console.log(NAV_INDEX.home.url) ? "/"
console.log(NAV_INDEX["home"]) ? {url: "/"}
*/
const NAV_INDEX = {
  home: { url: "/" },
  notice: { url: "/bbs/notice" },
  free: { url: "/bbs/free" },
};
document.addEventListener("DOMContentLoaded", () => {
  const mainNav = document.querySelector("nav.main");
  const navItems = mainNav.querySelectorAll("LI");

  mainNav?.addEventListener("click", (e) => {
    const target = e.target;
    if (target.tagName === "LI") {
      const className = target.classList[0];
      const url = NAV_INDEX[className].url;
      document.location.href = `${rootPath}${url}`;

      // nav가 클릭 됐을때 active 한 UI 를 보여주기 위한 설정,
      // 그런데 nav 를 클릭하면 화면이 refresh 되기때문에 의미가 없다.
      // 모든 nav.main li tag 에 active 라는 class 를 제거하기
      //   navItems.forEach((item) => {
      //     item.classList.remove("active");
      //   });
      //   // active 클래스 추가하기
      //   target.classList.add("active");
    }
  });

  // 화면이 새로 refresh 되었을때, 또는 화면이 최초로 보일때 실행할 코드

  // 현재화면의 url 을 가져옴
  const path = document.location.pathname;
  // http://localhost:8080/gallery/bbs/free/write 를 "/" 로 쪼개기
  const pathArr = path.split("/");
  // pathArr 마지막 값이 공백이면, home 문자열을 붙혀라
  // localhost:8080/gallery/ 로 끝나면 gallery/home 으로 만들어
  pathArr[pathArr.length - 1] || pathArr.push("home");

  // pathname 의 마지막 pathname 가져오기
  // URL 중에서 가장 끝에 오는 URL 문자열은?
  // 제일 마지막 문자열이 ""이면 "home" 문자열로 대치
  //   const pathname = pathArr[pathArr.length - 1] || "home";

  // nav의 name 값과 url 끝의 값이 일치한것 찾기
  // nav 가 1단계만 있을 경우 밑의 코드 사용하기
  //   document
  //     .querySelector(`nav.main li.${pathname}`)
  //     ?.classList.add("active");

  // nav 가 2단계 이상일 경우
  // json 타입의 데이터를 배열 형태로 바꿔줌
  // const navs = Object.values(NAV_INDEX);
  // navs.forEach((nav) => {
  //   // 배열을 join 으로 다시 하나의 문자열로 만들고나서 nav.name 과 같은지 확인
  //   // 배열.join("sp") : 배열을 sp 문자열을 중간에 추가하여 하나의 문자열로 바꾸는 함수
  //   if (pathArr.join(" ").includes(nav.name)) {
  //     document
  //       .querySelector(`nav.main li.${nav.name}`)
  //       ?.classList.add("active");
  //   }
  // });

  // key 를 배열로 만들기 home,notice,free
  const navs = Object.keys(NAV_INDEX);
  navs.forEach((key) => {
    if (pathArr.join(" ").includes(key)) {
      document
        .querySelector(`nav.main li.${key}`)
        ?.classList.add("active");
    }
  });
});
