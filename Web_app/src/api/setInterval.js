import fetch from "isomorphic-fetch";

const putInterval = async (uri="25.78.72.7", myInterval) => {
    try {
       const response =  await fetch(`http://${uri}/api/put/set_interval.php/`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                'Access-Control-Allow-Methods': 'PUT, POST, GET, DELETE, OPTIONS'
            },
            //mode: "cors",
            body: JSON.stringify({ interval: myInterval })  
        })
        console.log(response)
    } catch (e) {
        console.error(e);
    }
}

export {putInterval}