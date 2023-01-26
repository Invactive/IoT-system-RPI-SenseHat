const fetchLeds= async (uri="25.78.72.7") => {

    try {
      const db = await fetch(`http://${uri}/api/get/get_leds.php/`, {
        //headers,
        method: "GET",
        mode: "cors",
      });
      if (db.status !== 200) {
        console.log("RETURNED STATUS OTHER THAN 200");
      }
      const data = await db.json();
      return data;
    } catch (e) {
      console.error(e);
    }
  };
  
  export { fetchLeds};
  