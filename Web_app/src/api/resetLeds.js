import fetch from "isomorphic-fetch";

const resetLED = async (uri="25.78.72.7") => {
    try {
       const response =  await fetch(`http://${uri}/api/put/reset_leds.php/`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                'Access-Control-Allow-Methods': 'PUT, POST, GET, DELETE, OPTIONS'
            },
        })
        console.log(response)
    } catch (e) {
        console.error(e);
    }
}

export {resetLED}