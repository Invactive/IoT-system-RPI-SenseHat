let gridDiv = document.querySelector('.grid');
let buttons = document.getElementsByClassName('formBtn');
const buttonsArr = []
var clickedDict = {}
var btnColorsArr = []

function getCookies(name){
  var LED_state = decodeURIComponent(document.cookie);
  var LED_stateArr = LED_state.split(/[;;]/);
  LED_stateArr.forEach(el=>{
  if(el.match(`${name}=`)){
    var LEDstr = el.replace(`${name}=`, '');
    var LEDarr = JSON.parse(LEDstr);
    btnColorsArr.push(LEDarr);
  }
})
}

getCookies("LEDstate");
console.log(btnColorsArr);

for(let i = 0; i < 64; i++) {
  let childDiv = document.createElement('button');
  childDiv.className = 'formBtn';
  childDiv.id = "b" + i;
  childDiv.style.backgroundColor = "rgb(" + btnColorsArr[0][i][0] + "," + btnColorsArr[0][i][1] + "," + btnColorsArr[0][i][2] + ")";
  gridDiv.appendChild(childDiv);
}

const evListener = (btn) => {
  msg = prompt("Enter RGB values [0-255] separated by commas");
  clickedDict[btn.id] = msg;
  clickedJSON = JSON.stringify(clickedDict);
  document.cookie = "json=" + clickedJSON;
  console.log(msg);
  RGBvals = msg.split(',');
  console.log(RGBvals);
  if(msg != null){
    btn.style.backgroundColor = "rgb(" + RGBvals[0] + "," + RGBvals[1] + "," + RGBvals[2] + ")";
  }
  console.log(clickedJSON);
}

for(i = 0; i < gridDiv.childElementCount; i++) {
  buttonsArr.push(buttons[i])
}

buttonsArr.forEach(btn=>{
  btn.addEventListener('click', ()=>{evListener(btn)})
})


