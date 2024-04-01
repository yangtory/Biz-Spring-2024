document.addEventListener("DOMContentLoaded", () => {
  const table = document.querySelector("table.iolist");
  const btn_update = document.querySelector("input.btn_update");
  const btn_delete = document.querySelector("input.btn_delete");

  table?.addEventListener("click", (e) => {
    const tr = e.target.closest("TR");
    const seq = tr.dataset.num;
    // alert(seq);
    document.location.href = `${rootPath}/detail/${seq}`;
  });

  btn_update?.addEventListener("click", () => {
    const seq = btn_update.dataset.num;
    document.location.href = `${rootPath}/update/${seq}`;
  });

  btn_delete?.addEventListener("click", () => {
    const seq = btn_delete.dataset.num;
    if (confirm("정말 삭제할까요?")) {
      document.location.href = `${rootPath}/delete/${seq}`;
    }
  });

  // update 화면에서 시간 받아오기
  const date = document.querySelector("input.date");
  const time = document.querySelector("input.time");

  if (!serverDate) {
    date.value = io_date;
    date.readOnly = true;
  }
  if (!serverTime) {
    time.value = io_time;
    time.readOnly = true;
  }

  //   const seq = document.querySelector("input.seq");
  //   if (!seq.value) {
  //     seq.value = "";
  //   }
});
