document.addEventListener("DOMContentLoaded", () => {
  const fileInput = document.querySelector("input.file.single");
  const filesInput = document.querySelector("input.file.multi");

  const prevImg = document.querySelector("img.gallery");
  const imgBorderBox = document.querySelector("div.image");
  const base64Box = document.querySelector("textarea.base64");
  const multiImageBox = document.querySelector("div.image.multi");

  const encodeImageFileAsBase64 = async (image) => {
    //fileReader 무조건 비동기, Promise : 비동기 코드를 동기식으로 바꿔주는 객체
    return new Promise((resolve, _) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        // 압축 필요없어서 미리보기는 이거면 됨
        resolve(reader.result);
      };
      reader.readAsDataURL(image);
    });
  };

  // 파일을 붙여넣기 했을때
  prevImg.addEventListener("click", () => filesInput.click());

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
    } else {
      alert("이미지만 붙여넣기 하세요");
    }
  });

  const filePreview = async (file) => {
    const base64 = await encodeImageFileAsBase64(file);
    if (base64) {
      const img = document.createElement("img");
      img.style.width = "100px";
      img.style.height = "100px";
      img.src = base64;
      multiImageBox.appendChild(img);
    }
  };
  // foreach 문 files 를 각 file 로
  filesInput.addEventListener("change", async (e) => {
    const files = e.target.files;
    // 파일을 다시 올릴 때 추가되는 현상 방지
    multiImageBox.innerHTML = "";
    for (let file of files) {
      await filePreview(file);
    }
  });
  // 파일을 선택했을때 이미지 파일 미리보기
  fileInput.addEventListener("change", async (e) => {
    const target = e.target;
    const file = target.files[0];
    await filePreview(file);
  });
});
