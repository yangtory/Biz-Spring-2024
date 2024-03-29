document.addEventListener("DOMContentLoaded", () => {
  const product_list = document.querySelector("tbody.product_body");
  const btn_update = document.querySelector("input.btn_update");
  const btn_delete = document.querySelector("input.btn_delete");

  const list_onClick_handler = (e) => {
    const target = e.target;
    if (target.tagName === "TD") {
      const pcode = target.closest("TR").dataset.pcode;
      document.location.href = `${rootPath}/product/detail?p_code=${pcode}`;
    }
  };
  product_list?.addEventListener("click", list_onClick_handler);

  btn_update?.addEventListener("click", () => {
    const pcode = btn_update.dataset.pcode;
    // alert(pcode);
    document.location.href = `${rootPath}/product/update?pcode=${pcode}`;
  });

  btn_delete?.addEventListener("click", () => {
    const pcode = btn_delete.dataset.pcode;
    if (confirm("정말 삭제할까요?")) {
      document.location.replace(
        `${rootPath}/product/delete/${pcode}`
      );
    }
  });
});
