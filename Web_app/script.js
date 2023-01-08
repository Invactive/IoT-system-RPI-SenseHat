const frame = document.getElementById("frame");
const labHeader = document.getElementById("labHeader");
const zad1 = document.getElementById("zad1");
const zad2 = document.getElementById("zad2");
const zad3 = document.getElementById("zad3");
const zad4 = document.getElementById("zad4");
const hlinks = [zad1, zad2, zad3, zad4];

const frameHandler = (source) => {
  frame.src = source;
};

const linkHandler = (el) => {
  frameHandler(el.dataset.link);
  labHeader.textContent = el.textContent;
};

hlinks.forEach((element) => {
  element.addEventListener("click", () => {
    console.log(element.dataset.link);
    linkHandler(element);
  });
});
