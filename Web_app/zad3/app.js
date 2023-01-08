const inputValue = document.getElementById("inputValue");
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

const jednostki = ["rpm", "rps", "radmin", "rads"];

submitBtn.addEventListener("click", function () {
  outputValue.value = "";
  let jsonInput = inputValue.value;
  let jsonData;
  try {
    jsonData = JSON.parse(jsonInput);
  } catch (e) {
    outputValue.value = e;
    return false;
  }
  let value = jsonData.value;
  let inputUnit = jsonData.inputUnit;
  let outputUnit = jsonData.outputUnit;

  if (typeof value !== "number" && value !== undefined) {
    outputValue.value = `${value} is ${typeof value}. Insert a number!`;
  }

  if (typeof inputUnit !== "string" && inputUnit !== undefined) {
    outputValue.value = `${inputUnit} is ${typeof inputUnit}. Insert a string!`;
  } else {
    if (!jednostki.includes(inputUnit)) {
      outputValue.value = `Błędna jednostka wejściowa, proszę podać z wybranych: ${jednostki}`;
      return;
    }
  }

  if (typeof outputUnit !== "string" && outputUnit !== undefined) {
    outputValue.value = `${outputUnit} is ${typeof outputUnit}. Insert a string!`;
  } else {
    if (!jednostki.includes(outputUnit)) {
      outputValue.value = `Błędna jednostka wyjściowa, proszę podać z wybranych: ${jednostki}`;
      return;
    }
  }

  rpmtorps = value / 60;
  rpmtorads = value * 0.10471975511966;
  rpmtoradmin = rpmtorads * 60;
  rpstorpm = value * 60;
  rpstorads = value * 6.2831853071796;
  rpstoradmin = rpstorads * 60;
  radstoradmin = value * 60;
  radstorpm = value * 9.5492965855137;
  radstorps = radstorpm / 60;
  radmintorads = value / 60;
  radmintorpm = value * 0.1591549430919;
  radmintorps = radmintorpm * 60;

  const result = {
    rpm: {
      rpm: value,
      rps: rpmtorps,
      rads: rpmtorads,
      radmin: rpmtoradmin,
    },
    rps: {
      rps: value,
      rpm: rpstorpm,
      rads: rpstorads,
      radmin: rpstoradmin,
    },
    radmin: {
      radmin: value,
      rps: radmintorps,
      rads: radmintorads,
      rpm: radmintorpm,
    },
    rads: {
      rads: value,
      rps: radstorps,
      rpm: radstorpm,
      radmin: radstoradmin,
    },
  };

  let resultOutput = result[inputUnit][outputUnit];
  const resultJSON = {
    outputValue: resultOutput,
    outputUnit: outputUnit,
  };

  outputValue.value = JSON.stringify(resultJSON);
});
