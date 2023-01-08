const ctx = document.getElementById("myChart");
const amplitudehtml = document.getElementById("amplituda");
const fazehtml = document.getElementById("faza");
const tphtml = document.getElementById("okresProbkowania");
const tthtml = document.getElementById("okresSygnalu");
const submitBtn = document.getElementById("submitBtn");

const samples = 200;
let x = 0;
let t = [];
let y = [];

Chart.defaults.font.size = 30;

let myChart = new Chart(ctx, {
  type: "line",
  data: {
    labels: t,
    datasets: [
      {
        label: "Sygnał",
        data: y,
        borderWidth: 1,
      },
    ],
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: "Wartość",
        },
      },
      x: {
        beginAtZero: true,
        title: {
          display: true,
          text: "Czas [s]",
        },
      },
    },
  },
});

function addData(chart, label, data) {
  myChart.data.labels = label;
  myChart.data.datasets[0].data = data;
  chart.update();
}

function removeData(chart) {
  chart.data.labels = [];
  myChart.data.datasets[0].data = [];
  chart.update();
}

function wypelnij(a, w, f, krok, okres) {
  for (i = 0; i < krok; i++) {
    x += okres;
    t.push(x.toFixed(2));

    let sinus = w * t[i] + f;
    let yn = a * Math.sin(sinus);
    y.push(yn);
  }
}

submitBtn.addEventListener("click", function () {
  t = [];
  y = [];
  x = 0;
  let amplitude = Number(amplitudehtml.value);
  let phase = Number(fazehtml.value);
  let tp = Number(tphtml.value);
  let tt = Number(tthtml.value);
  let krok = samples / tp;
  removeData(myChart);
  wypelnij(amplitude, tt, phase, krok, tp);
  addData(myChart, t, y);
});
