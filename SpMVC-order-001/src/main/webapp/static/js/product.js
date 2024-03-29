document.addEventListener("DOMContentLoaded", () => {
  const product_list = document.querySelector("tbody.product_body");

  const list_onClick_handler = (e) => {
    const target = e.target;
    if (target.tagName === "TD") {
      const pcode = target.closest("TR").dataset.pcode;
      document.location.href = `${rootPath}/product/detail?p_code=${pcode}`;
    }
  };
  product_list?.addEventListener("click", list_onClick_handler);
});
