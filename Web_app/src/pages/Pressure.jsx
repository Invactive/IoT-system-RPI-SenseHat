import React from "react";
import { useState, useEffect } from "react";
import { fetchPressure } from "../api/getPressure";
import styled from "styled-components";
import { SideNav } from "../components/SideNav";
import {
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend
} from "recharts";

const Pressure = ({ chartInterval, server }) => {
  //PRESSURE IN hPa
  const [tempArrPH, setTempArrPH] = useState([]);
  //PRESSURE IN MMHG
  const [tempArrPM, setTempArrPM] = useState([]);
  //TIME
  const [timeArr, setTimeArr] = useState([]);

  const dataPress = [
    { name: timeArr[0], hPa: tempArrPH[0], MMHG: tempArrPM[0] },
    { name: timeArr[1], hPa: tempArrPH[1], MMHG: tempArrPM[1] },
    { name: timeArr[2], hPa: tempArrPH[2], MMHG: tempArrPM[2] },
    { name: timeArr[3], hPa: tempArrPH[3], MMHG: tempArrPM[3] },
    { name: timeArr[4], hPa: tempArrPH[4], MMHG: tempArrPM[4] },
    { name: timeArr[5], hPa: tempArrPH[5], MMHG: tempArrPM[5] },
    { name: timeArr[6], hPa: tempArrPH[6], MMHG: tempArrPM[6] },
    { name: timeArr[7], hPa: tempArrPH[7], MMHG: tempArrPM[7] },
    { name: timeArr[8], hPa: tempArrPH[8], MMHG: tempArrPM[8] },
    { name: timeArr[9], hPa: tempArrPH[9], MMHG: tempArrPM[9] }
  ];

  useEffect(() => {
    const interval = setInterval(async () => {
      const PressObj = await fetchPressure(server);
      const currPressPH = await PressObj.press_hpa;
      const currPressPM = await PressObj.press_mmhg;
      const currTime = PressObj.timestamp;
      setTempArrPH(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currPressPH)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currPressPH));
        } else {
          return [];
        }
      });
      setTempArrPM(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currPressPM)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currPressPM));
        } else {
          return [];
        }
      });
      setTimeArr(prev => {
        if (prev.length < 10) {
          return [...prev, currTime.slice(11, 19)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currTime.slice(11, 19));
        } else {
          return [];
        }
      });
    }, chartInterval);
    return () => clearInterval(interval);
  });

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <StyledItem>
          <h1>Pressure</h1>
          <StyledChart data={dataPress} height={300} width={800}>
            <Tooltip />
            <Legend />
            <XAxis dataKey="name" />
            <YAxis />
            <Line type="monotone" dataKey="hPa" stroke="#751818" />
            <Line type="monotone" dataKey="MMHG" stroke="#251a88" />
            <CartesianGrid stroke="#ccc" />
          </StyledChart>
        </StyledItem>
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
  height: 100vh;
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const StyledChart = styled(LineChart)`
  @media only screen and (max-width: 1024px) {
    scale: 0.8;
  }
`;

const StyledMain = styled.div`
  margin: auto;
`;

export { Pressure };
