document.addEventListener("DOMContentLoaded", () => {
  const fileInput = document.querySelector("input.file");
  const prevImg = document.querySelector("img.gallery");
  const imgBorderBox = document.querySelector("div.image");
  const base64Box = document.querySelector("textarea.base64");

  /*
    업로드할 이미지를 base64 방식으로 encoding 하는 함수
    base64 제약사항
    파일 크기가 크다
    jpeg 는 다소 양호하나 png 나 벡터 타입은 이슈가 있다
    파일의 크기 문제로 업로드, 데이터, DB 저장 등에서 문제를 일으킬수 있다
    다만, DB 에 파일을 저장함으로써 별도의 이미지를 보관하는 방식에 비해 유리한 점도 있다.

    base64 로 변환된 파일을 압축하여, jpeg 로 변환하면 용량문제를 다소 해결할 수 있다.
  */
  const encodeImageFileAsBase64 = async (image) => {
    //fileReader 무조건 비동기, Promise : 비동기 코드를 동기식으로 바꿔주는 객체
    return new Promise((resolve, _) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        // resolve :  여기있는 함수가 모두 끝났을때 (reader.result)을 return 한다

        // Image() : 이미지를 그리는 도구
        const myImage = new Image();
        const imageBase64 = reader.result;
        myImage.src = imageBase64;
        myImage.onload = (e) => {
          /*
            Image 객체 (myImage) 가 load 되면 
            화면에 2d 가상 canvas 를 생성
            그 canvas 에 myImage 에 담긴 이미지를 그려라
          */
          //가상의 캔바스태그 만들기
          const myCanvas = document.createElement("canvas");
          // 2d 이미지를 그리겟음
          const context = myCanvas.getContext("2d");
          // 그려줄 이미지 크기만큼 canvas 크기 지정하기
          myCanvas.width = e.target.width;
          myCanvas.height = e.target.height;
          // e.target : reader.result, (0,0) : 박스에 가득 채워 그리기
          context.drawImage(e.target, 0, 0);
          // canvas 에 그려진 이미지를 jpeg 로 변환하고 크기를 0.5 배 만큼 줄여라
          let point = 0.5;
          let reSizeImage = myCanvas.toDataURL("image/jpeg", point);
          const imageSize = 2 * 1024 * 1024; // 2MB
          // 압축한 이미지 크기가 2mb 보다 크면 작아질때까지 일정 비율만큼(0.01) 돌려버리기
          while (reSizeImage.length > imageSize) {
            if (point < 0.01) {
              break;
            }
            point -= 0.001; // 이 만큼씩 줄이기
            reSizeImage = myCanvas.toDataURL("image/jpeg", point);
          }
          if (reSizeImage.length > imageSize) {
            alert("이미지가 너무 커서 업로드 할수 없습니다");
            return false;
          }
          resolve(reSizeImage);
        };
      };
      reader.readAsDataURL(image);
    });
  };

  // 파일을 붙여넣기 했을때
  prevImg.addEventListener("click", () => fileInput.click());
  imgBorderBox.addEventListener("paste", async (e) => {
    const items = e.clipboardData.items;
    // items 객체가 존재하면 그 중 0번째 요소를 getter 하기
    const item = items?.[0];
    // item.type : type 확인하는 방법
    // image/png, text/plain 앞에 있는 친구가 image 면 === 0
    // application/text 중간에 있으면 >= 0

    // 붙여넣기 한 데이터가 image/* 이면~
    if (item.type.indexOf("image" === 0)) {
      // 붙여넣기한 이미지 중에서 file 만 추출하기
      const blob = item.getAsFile();
      // 파일이 추출이 안되면
      if (!blob) {
        return null;
      }
      const base64 = await encodeImageFileAsBase64(blob);
      prevImg.src = base64;
      base64Box.value = base64;
      //   // 파일을 읽기 위한 도구(클래스)
      //   const reader = new FileReader();
      //   // 파일이 모두 load(읽기) 되었으면
      //   reader.onloadend = () => {
      //     // 여기 src 에 붙여라
      //     prevImg.src = reader.result;
      //   };
      //   // blob 파일을 읽어라, 읽어지면 onloadend 가 실행
      //   reader.readAsDataURL(blob);
    } else {
      alert("이미지만 붙여넣기 하세요");
    }
  });

  // 파일을 선택했을때
  fileInput.addEventListener("change", async (e) => {
    const target = e.target;
    const file = target.files[0];
    // alert(file.name);
    const base64 = await encodeImageFileAsBase64(file);
    prevImg.src = base64;
    base64Box.value = base64;
    // const reader = new FileReader();
    // reader.onloadend = () => {
    //   prevImg.src = reader.result;
    // };
    // reader.readAsDataURL(file);
  });
});
