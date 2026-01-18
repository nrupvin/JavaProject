const link = document.querySelector(".primary");

if (link) {
  link.addEventListener("click", () => {
    link.textContent = "Opening /api/hello...";
  });
}
