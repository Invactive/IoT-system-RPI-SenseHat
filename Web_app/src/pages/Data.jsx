import React from "react";
import { SideNav } from "../components/SideNav";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { fetchData } from "../api/getData";
import { DataTable } from "../components/DataTable";

const Data = ({ dataInterval, tempUnits, pressUnits, server }) => {
  //TEMPRATURE
  const [tempArr, setTempArr] = useState([]);
  //PRESSURE
  const [pressArr, setPressArr] = useState([]);
  //HUMIDITY
  const [humArr, setHumArr] = useState([]);
  //ACCELEROMETER
  const [accArr, setAccArr] = useState([]);
  //JOYSTICK
  const [joyArr, setJoyArr] = useState([]);
  //TIMPESTAMP
  const [timeArr, setTimeArr] = useState([]);
  //checks for components render
  const [isFetched, setIsFetched] = useState(false);

  // eslint-disable-next-line react-hooks/exhaustive-deps
  const fetching = async server => {
    await fetchData(server).then(data => {
      setTempArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.temperature }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.temperature });
        } else {
          return [];
        }
      });
      setPressArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.pressure }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.pressure });
        } else {
          return [];
        }
      });
      setHumArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.humidity }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.humidity });
        } else {
          return [];
        }
      });
      setAccArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.accelerometer }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.accelerometer });
        } else {
          return [];
        }
      });
      setJoyArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.joystick }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.joystick });
        } else {
          return [];
        }
      });
      setTimeArr(prev => {
        if (prev.length < 1) {
          return [...prev, { ...data.timestamp }];
        } else if (prev.length === 1) {
          return prev.slice(1).concat({ ...data.timestamp });
        } else {
          return [];
        }
      });
    });
    setIsFetched(true);
  };

  useEffect(() => {
    const interval = setInterval(async () => {
      fetching(server);
    }, dataInterval);
    return () => clearInterval(interval);
  }, [tempArr]);

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        {isFetched ? (
          <DataTable
            temp={tempArr}
            press={pressArr}
            hum={humArr}
            acc={accArr}
            time={timeArr}
            joy={joyArr}
            tempUnits={tempUnits}
            pressUnits={pressUnits}
          />
        ) : (
          <StyledHeader>Loading...</StyledHeader>
        )}
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const StyledHeader = styled.h1`
  text-align: center;
`;

const StyledMain = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  margin-inline: 1rem;
  @media only screen and (max-width: 1528px) {
    margin-left: 10rem;
  }
`;

export { Data };
