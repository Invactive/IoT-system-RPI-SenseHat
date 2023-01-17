import React from "react";
import { SideNav } from "../components/SideNav";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { fetchData } from "../api/getData";


const Data = () => {
  const [data, setData] = useState([]);

  const fetchLogs = async () => {
    const dataObj = await fetchData();
    setData(dataObj)
  }

  useEffect(() => {
    fetchLogs()
    const interval = setInterval( async () => {
        fetchLogs();
    }, 5000);
    return () => clearInterval(interval);
  },[]);

  return (
    <StyledWrapper>
      <SideNav />
      <button
        onClick={() => {
          console.log(data.temperature);
        }}
      >
        log
      </button>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
`;

export { Data };
