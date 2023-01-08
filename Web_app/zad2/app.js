const inputValue = document.getElementById("inputValue");
const selectedInputValue = document.getElementById("selectedInputValue");
const selectedOutputValue = document.getElementById("selectedOutputValue");
const outputValue = document.getElementById("outputValue");
const submitBtn = document.getElementById("submitBtn");

let rpmtorps;
let rpmtorads;
let rpmtoradmin;

let rpstorpm;
let rpstorads;
let rpstoradmin;

let radstoradmin;
let radstorpm;
let radstorps;

let radmintorads;
let radmintorpm;
let radmintorps;

submitBtn.addEventListener("click", function () {
  rpmtorps = inputValue.value / 60;
  rpmtorads = inputValue.value * 0.10471975511966;
  rpmtoradmin = rpmtorads * 60;
  rpstorpm = inputValue.value * 60;
  rpstorads = inputValue.value * 6.2831853071796;
  rpstoradmin = rpstorads * 60;
  radstoradmin = inputValue.value * 60;
  radstorpm = inputValue.value * 9.5492965855137;
  radstorps = radstorpm / 60;
  radmintorads = inputValue.value / 60;
  radmintorpm = inputValue.value * 0.1591549430919;
  radmintorps = radmintorpm * 60;

  const result = {
    rpm: {
      rpm: inputValue.value,
      rps: rpmtorps,
      rads: rpmtorads,
      radmin: rpmtoradmin,
    },
    rps: {
      rps: inputValue.value,
      rpm: rpstorpm,
      rads: rpstorads,
      radmin: rpstoradmin,
    },
    radmin: {
      radmin: inputValue.value,
      rps: radmintorps,
      rads: radmintorads,
      rpm: radmintorpm,
    },
    rads: {
      rads: inputValue.value,
      rps: radstorps,
      rpm: radstorpm,
      radmin: radstoradmin,
    },
  };

  let xd = selectedInputValue.value;
  let xd2 = selectedOutputValue.value;
  outputValue.value = result[xd][xd2];
});
