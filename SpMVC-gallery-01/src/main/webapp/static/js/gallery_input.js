document.addEventListener("DOMContentLoaded", () => {
  const fileInput = document.querySelector("input.file");
  const prevImg = document.querySelector("img.gallery");
  const imgBorderBox = document.querySelector("div.image");
  const base64Box = document.querySelector("textarea.base64");

  const encodeImageFileAsBase64 = async (image) => {
    //fileReader 무조건 비동기, Promise : 비동기 코드를 동기식으로 바꿔주는 객체
    return new Promise((resolve, _) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        // resolve :  여기있는 함수가 모두 끝났을때 (reader.result)을 return 한다
        resolve(reader.result);
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
