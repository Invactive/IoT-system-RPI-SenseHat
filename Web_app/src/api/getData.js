//import fetch from "isomorphic-fetch";

const fetchData = async (uri="25.78.72.7") => {
    try {
      const db = await fetch(`http://${uri}/api/get/get_logs.php`, {
        //headers,
        method: "GET",
        mode: "cors",
      });
      //console.log(db);
      const data = await db.json();
      return data;
    } catch (e) {
      console.error(e);
    }
  };
  
  export { fetchData };
  