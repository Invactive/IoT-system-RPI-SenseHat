import fetch from "isomorphic-fetch";

const putLED = async (x, y ,r=0, g=0 ,b=0, uri="25.78.72.7") => {
    try {
       const response =  await fetch(`http://${uri}/api/put/set_leds.php/`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                'Access-Control-Allow-Methods': 'PUT, POST, GET, DELETE, OPTIONS'
            },
            //mode: "cors",
            body: JSON.stringify({  x: x, y: y, r: r, g: g, b: b })
        })
        console.log(response)
    } catch (e) {
        console.error(e);
    }
}

export {putLED}