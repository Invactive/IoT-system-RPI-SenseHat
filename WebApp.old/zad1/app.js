const clickBtn = document.getElementById("clickBtn");
const incrSpan = document.getElementById("increment");

let incr = 0;

clickBtn.addEventListener("click", function () {
  clickBtn.textContent = "Hello world";
  incr++;
  incrSpan.textContent = incr;
  if (incr === 69) {
    incrSpan.textContent = "😏";
  }
  console.log(incr);
});
